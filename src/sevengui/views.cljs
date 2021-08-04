(ns sevengui.views
  (:require [re-frame.core :refer [subscribe dispatch]]
            [sevengui.one.views :as one]
            [sevengui.two.views :as two]
            [sevengui.three.views :as three]
            [sevengui.four.views :as four]
            [sevengui.five.views :as five]))

(defn home []
  [:div "This is my 7GUI challenge solution."])

(defn navbar-item [route-name text accent]
  [:div.px-2.mx-1.rounded.border-b-2.cursor-pointer.hover:bg-blue-100
   {:href "#"
    :on-click #(dispatch [:navigate route-name])
    :class (when accent "font-bold")}
   [:span text]])

(def navbar-items
  [[:home "Home"] [:one "1"] [:two "2"] [:three "3"] [:four "4"] [:five "5"]])

(defn navbar [current-route-name]
  [:div.flex.items-center.justify-center.p-2
   (map
    (fn [[route-name text]]
      ^{:key route-name} [navbar-item route-name text (= current-route-name route-name)])
    navbar-items)])

(defn match-page [route-name]
  [(case route-name
     :home home
     :one one/main
     :two two/main
     :three three/main
     :four four/main
     :five five/main
     home)])

(defn main []
  (let [current-route-name @(subscribe [:current-route/name])]
    [:div
     [navbar current-route-name]
     [:div.mx-auto.px-4.max-w-screen-md.flex.justify-center
      [:div.bg-yellow-100.rounded.p-4
       (match-page current-route-name)]]]))