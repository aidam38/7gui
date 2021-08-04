(ns sevengui.effects
  (:require [re-frame.core :refer [reg-fx]]
            [reitit.frontend.easy :as rfe]))

(reg-fx :navigate!
        (fn [route-name]
          (apply rfe/push-state [route-name])))