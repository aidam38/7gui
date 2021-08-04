(ns sevengui.db
  (:require [re-frame.core :refer [path]]))

(def default-db {:current-route nil})

(def cur-chal-in (path [:current-challenge]))
