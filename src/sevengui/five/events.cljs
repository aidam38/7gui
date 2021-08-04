(ns sevengui.five.events
  (:require [re-frame.core :refer [reg-event-db]]
            [sevengui.db :refer [cur-chal-in]]
            [sevengui.five.db :refer [default-db]]))

(reg-event-db
 :five/initialize
 cur-chal-in
 (constantly default-db))

(reg-event-db
 :five/update-filter-phrase
 cur-chal-in
 (fn [dbc [_ filter-phrase]]
   (assoc dbc :filter-phrase filter-phrase)))

(reg-event-db
 :five/create
 cur-chal-in
 (fn [dbc _]
   (update dbc :data conj (:currently-editing dbc))))

(reg-event-db
 :five/update
 cur-chal-in
 (fn [dbc [_]]
   (update dbc :data assoc (:currently-selected dbc) (:currently-editing dbc))))

(defn vec-remove-at [coll n]
  (vec (concat (take n coll) (drop (inc n) coll))))

(reg-event-db
 :five/delete
 cur-chal-in
 (fn [dbc [_]]
   (update dbc :data vec-remove-at (:currently-selected dbc))))

(reg-event-db
 :five/update-currently-editing
 cur-chal-in
 (fn [dbc [_ type value]]
   (assoc-in dbc [:currently-editing type] value)))

(reg-event-db
 :five/update-currently-selected
 cur-chal-in
 (fn [dbc [_ n]]
   (assoc dbc :currently-selected n :currently-editing (-> dbc :data (get n)))))