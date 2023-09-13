(ns system
  (:require
   [integrant.core :as ig]
   [stacks.storage :as storage]
   [stacks.topic :as topic])
  (:import
   [software.amazon.awscdk App]))

(def config
  {:app/instance   {}
   :stacks/topic   {:app      (ig/ref :app/instance)
                    :stack-id "TopicStack"}
   :stacks/storage {:app      (ig/ref :app/instance)
                    :stack-id "StorageStack"}
   :app/synth      {:app    (ig/ref :app/instance)
                    :stacks [(ig/ref :stacks/topic)
                             (ig/ref :stacks/storage)]}})

(defmethod ig/init-key :app/instance
  [_ _]
  (App.))

(defmethod ig/init-key :app/synth
  [_ {:keys [app]}]
  (.synth app))

(defmethod ig/init-key :stacks/topic
  [_ {:keys [app stack-id]}]
  (topic/stack app stack-id))

(defmethod ig/init-key :stacks/storage
  [_ {:keys [app stack-id]}]
  (storage/stack app stack-id))

(defn init
  "Initialise system"
  []
  (println "Initialising system")
  (ig/init config))
