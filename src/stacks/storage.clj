(ns stacks.storage
  (:import
   [software.amazon.awscdk Stack]
   [software.amazon.awscdk.services.s3 Bucket]))

(defn stack
  [parent id]
  (let [stack (Stack. parent id)

        _ (Bucket. stack "sample-bucket")]

    stack))
