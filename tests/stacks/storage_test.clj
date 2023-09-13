(ns stacks.storage-test
  (:import
   [software.amazon.awscdk App]
   [software.amazon.awscdk.assertions Template])
  (:require
   [clojure.test :as t]
   [stacks.storage :as storage]))

(t/deftest storage-stack-test
  (t/testing "stack has 1 bucket"
    (let [stack (storage/stack (App.) "test")
          template (Template/fromStack stack)]

      (t/is (nil?
             (.resourceCountIs template "AWS::S3::Bucket" 1))))))
