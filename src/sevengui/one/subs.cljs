(ns sevengui.one.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :one/counter
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :counter)))
