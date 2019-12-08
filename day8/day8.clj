; (def input (slurp "./day8test.txt"))
(def input (slurp "./input.txt"))

(def layersize (* 25 6))
(def arr (into-array input))

(def layers (partition layersize arr))

(defn makeint [raw]
    (int (apply str [raw]))
)

(defn countzeros [layer] 
    { :l layer :c (count (filter zero? (map read-string (map str layer))))}
)

(def counts (map countzeros layers))

(def leastzeros (apply min-key :c counts))
(println leastzeros)

(def thelayer (map read-string (map str (get leastzeros :l))))
; (def numones (count ))

(def numones (count (filter (fn [x] (= x 1)) thelayer)))
(println numones)

(def numtwos (count (filter (fn [x] (= x 2)) thelayer)))
(println numtwos)

(println (* numones numtwos))

