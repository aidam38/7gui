(ns sevengui.one.views
  (:require [re-frame.core :refer [dispatch subscribe]]
            [sevengui.components :refer [button]]))

(defn main []
  (let [counter @(subscribe [:one/counter])]
    [:div.w-28.flex.justify-between.items-center
     [:div.w-8.p-2 (pr-str counter)]
     [button #(dispatch [:one/increment]) "Count"]]))
