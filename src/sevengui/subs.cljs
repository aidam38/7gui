(ns sevengui.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :current-route
 (fn [db]
   (-> db :current-route)))

(reg-sub
 :current-route/name
 :<- [:current-route]
 (fn [current-route _]
   (-> current-route :data :name)))

(reg-sub
 :current-challenge
 (fn [db _]
   (-> db :current-challenge)))