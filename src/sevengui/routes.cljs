(ns sevengui.routes
  (:require [reitit.frontend :as rtf]
            [reitit.frontend.easy :as rtfe]
            [reitit.coercion.schema :as rtcs]
            [re-frame.core :refer [dispatch]]))

(defn chal-route [chal]
  [chal {:name (keyword chal)
         :controllers [{:start #(dispatch [(keyword (str chal "/initialize"))])}]}])

(def routes
  ["/"
   ["" {:name :home}]
   (chal-route "one")
   (chal-route "two")
   (chal-route "three")
   (chal-route "four")
   (chal-route "five")])

(def router
  (rtf/router
   routes
   {:data {:coercion rtcs/coercion}}))

(defn init-routes! []
  (rtfe/start!
   router
   #(dispatch [:navigated %])
   {:use-fragment true}))