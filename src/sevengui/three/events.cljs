(ns sevengui.three.events
  (:require [re-frame.core :refer [reg-event-db]]
            [sevengui.db :refer [cur-chal-in]]
            [sevengui.three.db :refer [default-db]]))

(reg-event-db
 :three/initialize
 cur-chal-in
 (constantly default-db))

(reg-event-db
 :three/change-type
 cur-chal-in
 (fn [dbc [_ t]]
   (assoc dbc :type (keyword t))))

(reg-event-db
 :three/update-date
 cur-chal-in
 (fn [dbc [_ t v]]
   (assoc dbc t v)))

(defn get-time [tm]
  (-> tm js/Date. .getTime))

(reg-event-db
 :three/book
 cur-chal-in
 (fn [dbc _]
   (if (or
        (= (:type dbc) :one-way)
        (> (get-time (:return dbc)) (get-time (:start dbc))))
     (assoc dbc :last-booked dbc)
     (assoc dbc :last-booked {:wrong true}))))