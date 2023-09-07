(ns core
  (:require
   [system :as system]))

(defn synth
  []
  (system/init))

(defn -main
  [& args]
  (println "CDK Example with Clojure")
  (synth))
