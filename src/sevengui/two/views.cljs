(ns sevengui.two.views
  (:require [re-frame.core :refer [dispatch-sync subscribe]]))

(defn main []
  (let [celsius @(subscribe [:two/celsius])
        fahrenheit @(subscribe [:two/fahrenheit])]
    [:div.w-96.flex.justify-between.items-center
     [:input.p-1.w-24 {:type "number"
                       :value celsius
                       :on-change #(dispatch-sync [:two/update-celsius (-> % .-target .-value)])}]
     [:div "Celsius = "]
     [:input.p-1.w-24 {:type "number"
                       :value fahrenheit
                       :on-change #(dispatch-sync [:two/update-fahrenheit (-> % .-target .-value)])}]
     [:div "Fahrenheit"]]))