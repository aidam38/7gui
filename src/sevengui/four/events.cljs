(ns sevengui.four.events
  (:require [re-frame.core :refer [reg-event-db]]
            [sevengui.db :refer [cur-chal-in]]
            [sevengui.four.db :refer [default-db]]))

(reg-event-db
 :four/initialize
 cur-chal-in
 (constantly default-db))

(reg-event-db
 :four/reset
 cur-chal-in
 (fn [dbc _]
   (assoc dbc :elapsed-time 0)))

(defn round [x p]
  (let [pex (js/Math.pow 10 p)]
    (-> x
        (+ js/Number.EPSILON)
        (* pex)
        (js/Math.round)
        (/ pex))))

(reg-event-db
 :four/update-time
 cur-chal-in
 (fn [dbc [_ dt]]
   (let [et (:elapsed-time dbc)
         d (:duration dbc)]
     (if (< et d)
       (assoc dbc :elapsed-time (round (+ et dt) 2))
       dbc))))

(reg-event-db
 :four/change-duration
 cur-chal-in
 (fn [dbc [_ n]]
   (assoc dbc :duration n)))
