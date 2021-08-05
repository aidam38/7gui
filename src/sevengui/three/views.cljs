(ns sevengui.three.views
  (:require [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as r]
            [sevengui.components :refer [button]]))

(defn chooser []
  [:select.m-2.p-1 {:on-change #(dispatch [:three/change-type (-> % .-target .-value)])}
   [:option {:value "one-way"} "One-way flight"]
   [:option {:value "return"} "Return flight"]])

(defn date-input []
  (let [val (r/atom "")]
    (fn [type]
      [:input.m-2.p-1 {:type "date"
                       :val @val
                       :on-change #(reset! val (-> % .-target .-value))
                       :on-blur #(dispatch [:three/update-date type (-> % .-target .-value)])}])))

(defn book-button []
  [button #(dispatch [:three/book]) "Book"])

(defn last-booked []
  (let [lbm @(subscribe [:three/last-booked-message])]
    [:div.text-center lbm]))

(defn main []
  [:div.flex.flex-col.justify-center.items-center.w-96
   [chooser]
   [date-input :start]
   (when (= @(subscribe [:three/type]) :return) [date-input :return])
   [book-button]
   (when @(subscribe [:three/last-booked]) [last-booked])])
