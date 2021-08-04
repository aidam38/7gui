(ns sevengui.four.views
  (:require [re-frame.core :refer [dispatch-sync subscribe]]
            [reagent.core :as r]))

(defn progress-bar []
  (let [progress @(subscribe [:four/elapsed-time])
        duration @(subscribe [:four/duration])]
    [:div.w-full.flex
     [:progress.w-full.mx-2 {:value progress
                             :max duration}]
     [:span.whitespace-nowrap.w-16 (str progress " s")]]))

(defn progress-bar-container []
  [:div.flex.items-center
   [:span.mr-2.whitespace-nowrap "Elapsed time: "]
   [progress-bar]])

(defn slider []
  (let [duration @(subscribe [:four/duration])]
    [:div.w-full.flex
     [:input.mx-2 {:type "range"
                   :min 0
                   :max 100
                   :step 0.1
                   :value duration
                   :on-change #(dispatch-sync [:four/change-duration (-> % .-target .-value)])}]
     [:span (str duration " s")]]))

(defn slider-container []
  [:div.flex.items-center
   [:span.mr-2 "Duration "]
   [slider]])

(defn reset-button []
  [:button {:on-click #(dispatch-sync [:four/reset])} "Reset"])

(defn main []
  [:div.flex.flex-col.justify-center.items.center.w-96
   [progress-bar-container]
   [slider-container]
   [reset-button]])

