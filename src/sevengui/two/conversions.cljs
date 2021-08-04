(ns sevengui.two.conversions)

(defn round [x p]
  (let [pex (js/Math.pow 10 p)]
    (js/Math.round (/ (* (+ x js/Number.EPSILON) pex) pex))))

(def symbol->function
  {'+ +
   '- -
   '* *
   '/ /})

(def operation-inverses-uni
  [['+ '-]
   ['* '/]])

(def operation-inverses
  (into {} (mapcat (fn [p] [p (-> p reverse vec)]) operation-inverses-uni)))

(defn invert-operation [o]
  (get operation-inverses o))

(defn invert-pair [p]
  (update p 0 invert-operation))

(defn invert [f]
  (->> f
       reverse
       (map invert-pair)
       vec))

(defn functionalize [l]
  (map (fn [[o x]] #((get symbol->function o) % x)) l))

(defn apply-operations [l]
  (fn [n]
    (reduce #(%2 %1) n (functionalize l))))

(def conversion-operations-uni
  {[:kelvin :celsius] [['- 273.15]]
   [:kelvin :fahrenheit]    [['* (/ 9 5)]
                             ['- 459.67]]})

(def conversion-operations
  (into {}
        (mapcat
         (fn [[d o]]
           [[d o] [(-> d reverse vec) (invert o)]]) conversion-operations-uni)))

(def conversion-functions
  (into {} (for [[d o] conversion-operations] [d (apply-operations o)])))

(defn get-conversion-function [f t]
  (if (= f t)
    identity
    (get conversion-functions [f t])))

(def anchor :kelvin)

(defn convert-temp
  ([x f t] (convert-temp x f t 6))
  ([x f t p]
   (let [f1 (get-conversion-function f anchor)
         f2 (get-conversion-function anchor t)]
     (-> x f1 f2 (round p)))))

(defn parse-temp [ts f t]
  (case ts
    nil ""
    "" ""
    (convert-temp (js/parseFloat ts) f t)))
