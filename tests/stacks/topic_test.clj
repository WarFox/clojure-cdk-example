(ns stacks.topic-test
  (:import
   [software.amazon.awscdk App]
   [software.amazon.awscdk.assertions Template])
  (:require
   [clojure.test :as t]
   [stacks.topic :as topic]))

(t/deftest stack-test
  (t/testing "stack has 1 queue"
    (let [stack (topic/stack (App.) "test")
          template (Template/fromStack stack)]

      (t/is (nil?
             (.resourceCountIs template "AWS::SQS::Queue" 1)))

      (t/is (nil?
             (.hasResourceProperties template
                                     "AWS::SQS::Queue"
                                     {"VisibilityTimeout" 300})))))

  (t/testing "stack has 1 topic"
    (let [stack    (topic/stack (App.) "test")
          template (Template/fromStack stack)]

      (t/is (nil?
             (.resourceCountIs template "AWS::SNS::Topic" 1)))

      (t/is (nil?
             (.hasResourceProperties template
                                     "AWS::SNS::Topic"
                                     {"DisplayName" "Very Important Topic"})))))

  (t/testing "stack has 1 subscription"
    (let [stack    (topic/stack (App.) "test")
          template (Template/fromStack stack)]

      (t/is (nil?
             (.resourceCountIs template "AWS::SNS::Subscription" 1)))

      ;; TODO Figure out a way to add test for AWS::SNS::Subscription
      )))
