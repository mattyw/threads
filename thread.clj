(defn thread []
    (Thread/sleep 10000)
    1)


(defn main [num]
    (let [futs (for [f (range num)] (future f))
      x 0]
      (reduce + (map (fn [f] @f) futs))))

(main 10000)
