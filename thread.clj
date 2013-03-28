(def num-threads 10)

(defn thread [r]
    (Thread/sleep 10000)
    (dosync (alter r + 1)))

(defn print-watch
    [key id old new]
    (prn key new))

(defn wait [state]
    (if (= @state num-threads)
        (prn "Done")
        (recur state)))

(defn main [num]
    (let [state (agent 0)
      x 0]
      (add-watch state :prn print-watch)
      (doseq [_ (range num)]
        (send state inc))
      (wait state)))

(main num-threads)
