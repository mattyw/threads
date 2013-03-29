(def num-threads 1000000)

(defn thread [r]
    (Thread/sleep 10000)
    (dosync (alter r + 1)))

(defn print-watch
    [key id old new]
    (prn key new))

(defn main [num]
    (let [state (agent 0)
      x 0]
      (add-watch state :prn print-watch) ;Big speed increase if we take this out
      (doseq [_ (range num)]
        (send state inc))
      (await state)
      (shutdown-agents)))
(main num-threads)
