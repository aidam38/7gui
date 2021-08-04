(ns sevengui.four.core
  (:require [sevengui.four.db]
            [sevengui.four.subs]
            [sevengui.four.events]
            [re-frame.core :refer [dispatch-sync]]))

(def dt 0.1)

(js/setInterval
 #(dispatch-sync [:four/update-time dt])
 (* dt 1000))

