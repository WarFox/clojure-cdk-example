(ns stacks.topic
  (:import
   [software.amazon.awscdk Stack]
   [software.amazon.awscdk Duration]
   [software.amazon.awscdk.services.sns Topic$Builder]
   [software.amazon.awscdk.services.sns.subscriptions SqsSubscription]
   [software.amazon.awscdk.services.sqs Queue$Builder]))

(defn stack
  [parent id]
  (let [stack (Stack. parent id)

        queue (-> (Queue$Builder/create stack "SampleAppQueue")
                  (.visibilityTimeout (Duration/seconds 300))
                  (.build))

        topic (-> (Topic$Builder/create stack "SampleAppTopic")
                  (.displayName "Very Important Topic")
                  (.build))]

    (.addSubscription topic (SqsSubscription. queue))

    stack))
