(ns sevengui.two.events
  (:require [re-frame.core :refer [reg-event-db]]
            [sevengui.two.db :refer [default-dbc]]
            [sevengui.db :refer [cur-chal-in]]
            [sevengui.two.conversions :refer [parse-temp]]))

(reg-event-db
 :two/initialize
 cur-chal-in
 (constantly default-dbc))

(reg-event-db
 :two/update-celsius
 cur-chal-in
 (fn [_ [_ cs]]
   (when (not= cs "")
     {:celsius cs
      :fahrenheit (pr-str (parse-temp cs :celsius :fahrenheit))})))

(reg-event-db
 :two/update-fahrenheit
 cur-chal-in
 (fn [_ [_ fs]]
   {:celsius (pr-str (parse-temp fs :fahrenheit :celsius))
    :fahrenheit fs}))
