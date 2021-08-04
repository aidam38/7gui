(ns sevengui.four.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :four/elapsed-time
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :elapsed-time)))

(reg-sub
 :four/duration
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :duration)))
    
