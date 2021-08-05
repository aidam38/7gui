(ns sevengui.five.views
  (:require [re-frame.core :refer [dispatch dispatch-sync subscribe]]
            [sevengui.components :refer [button]]))

(defn filter-input []
  (let [filter-phrase @(subscribe [:five/filter-phrase])]
    [:input.p-1
     {:type "text"
      :value filter-phrase
      :on-change #(dispatch-sync [:five/update-filter-phrase (-> % .-target .-value)])}]))

(defn filter-container []
  [:div.flex.items-center.mb-2
   [:span.mr-2 "Filter: "]
   [filter-input]])

(defn data-view-option [id name]
  ^{:key id} [:option {:value id} name])

(defn data-view []
  (let [data-strings @(subscribe [:five/data-strings])]
    [:select.w-full.p-1
     {:multiple true
      :size 8
      :on-change #(dispatch [:five/update-currently-selected (-> % .-target .-value js/Number)])}
     (map-indexed data-view-option data-strings)]))

(def pretty-type
  {:first "First name"
   :second "Surname"})

(defn data-editor-line [type]
  (let [currently-editing @(subscribe [:five/currently-editing])]
    [:div.flex.items-center.my-1
     [:span.w-36.mr-2.text-sm.whitespace-nowrap (str (get pretty-type type) ":")]
     [:input.w-full.p-1
      {:type "text"
       :value (get currently-editing type)
       :on-change #(dispatch-sync [:five/update-currently-editing type (-> % .-target .-value)])}]]))

(defn data-editor []
  [:div.p-1
   [data-editor-line :first]
   [data-editor-line :second]])

(defn panel []
  [:div.flex.mb-1
   [:div.mr-1 {:class "w-1/2"}
    [data-view]]
   [:div.ml-1 {:class "w-1/2"}
    [data-editor]]])

(defn buttons []
  [:div
   [button #(dispatch [:five/create]) "Create"]
   [button #(dispatch [:five/update]) "Update"]
   [button #(dispatch [:five/delete]) "Delete"]])

(defn main []
  [:div.w-96
   [filter-container]
   [panel]
   [buttons]])
