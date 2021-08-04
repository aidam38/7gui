(ns sevengui.two.subs
  (:require [re-frame.core :refer [reg-sub]]
            [sevengui.db :refer [cur-chal-in]]))

(reg-sub
 :two/celsius
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :celsius)))

(reg-sub
 :two/fahrenheit
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :fahrenheit)))

