(ns clojure-async.core
  (:require [clojure.core.async :refer :all]))

(defn longRun
  [response]
  (Thread/sleep 10000)
  (go (>! response "done")))

(defn simple
  []
  (let [response (chan)]
    (println "Running")
    (go
      (longRun response))
    (println (<!! (go (<! response))))))



(defn athread
  [r]
  (Thread/sleep 10000)
  (go (>! r 1)))

(defn threads
  []
  (let [r (chan)
        sum (atom 0)]
    (doseq [_ (range 1000)]
      (go (athread r)))
    (println "waiting to recv")

    (doseq [_ (range 1000)]
      (swap! sum (fn [x] (+ x (<!! (go (<! r)))))))
    (println @sum)))
      

(defn -main
  []
  ;(simple)
  (threads))
