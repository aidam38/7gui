(ns sevengui.one.events
  (:require [re-frame.core :refer [reg-event-db]]
            [sevengui.one.db :refer [default-dbc]]
            [sevengui.db :refer [cur-chal-in]]))

(reg-event-db
 :one/initialize
 cur-chal-in
 (constantly default-dbc))

(reg-event-db
 :one/increment
 cur-chal-in
 (fn [dbc _]
   (update dbc :counter inc)))