(ns system
  (:require
   [integrant.core :as ig]
   [stacks.topic :as topic])
  (:import
   [software.amazon.awscdk App]))

(def config
  {:app/instance {}
   :topic/stack  {:app (ig/ref :app/instance)
                  :id  "TopicStack"}
   :app/synth    {:app    (ig/ref :app/instance)
                  :stacks [(ig/ref :topic/stack)]}})

(defmethod ig/init-key :app/instance
  [_ _]
  (App.))

(defmethod ig/init-key :app/synth
  [_ {:keys [app]}]
  (.synth app))

(defmethod ig/init-key :topic/stack
  [_ {:keys [app stack-id]}]
  (topic/stack app stack-id))

(defn init
  "Initialise system"
  []
  (println "Initialising system")
  (ig/init config))
