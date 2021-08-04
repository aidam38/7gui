(ns sevengui.core
  (:require [reagent.dom :as rd]
            [re-frame.core :as rf]
            [sevengui.db]
            [sevengui.subs]
            [sevengui.effects]
            [sevengui.events]
            [sevengui.routes :as routes]
            [sevengui.views :refer [main]]
            [sevengui.one.core]
            [sevengui.two.core]
            [sevengui.three.core]
            [sevengui.four.core]
            [sevengui.five.core]))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (rd/render [main] (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (rf/dispatch-sync [:initialize-db])
  (routes/init-routes!)
  (mount-root))
