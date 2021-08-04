(ns sevengui.three.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :three/type
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :type)))

(reg-sub
 :three/last-booked
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :last-booked)))

(reg-sub
 :three/last-booked-message
 :<- [:three/last-booked]
 (fn [{:keys [type start return wrong]} _]
   (if wrong
     "Return date needs to be after start date."
     (str "You have booked a "
          (name type)
          " flight on "
          start
          (case type
            :one-way "."
            :return (str " and " return "."))))))