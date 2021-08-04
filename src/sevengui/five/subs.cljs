(ns sevengui.five.subs
  (:require [re-frame.core :refer [reg-sub]]
            [clojure.string :as s]))

(reg-sub
 :five/filter-phrase
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :filter-phrase)))

(reg-sub
 :five/currently-editing
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :currently-editing)))

(reg-sub
 :five/currently-selected
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :currently-selected)))

(reg-sub
 :five/data
 :<- [:current-challenge]
 (fn [dbc _]
   (-> dbc :data)))

(reg-sub
 :five/data-strings
 :<- [:five/data]
 :<- [:five/filter-phrase]
 (fn [[data filter-phrase] _]
   (->> data
        (map (fn [{:keys [first second]}] (str second ", " first)))
        (filter #(s/starts-with? % filter-phrase)))))

