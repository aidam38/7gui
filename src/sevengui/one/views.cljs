(ns sevengui.one.views
  (:require [re-frame.core :refer [dispatch subscribe]]))

(defn main []
  (let [counter @(subscribe [:one/counter])]
    [:div.w-28.flex.justify-between.items-center
     [:div.p-2 (pr-str counter)]
     [:button.bg-yellow-200.hover:bg-yellow-300.rounded.p-2 {:on-click #(dispatch [:one/increment])} "Count"]]))
