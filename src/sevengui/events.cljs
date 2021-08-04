(ns sevengui.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [reitit.frontend.controllers :as rfc]
            [sevengui.db :refer [default-db]]))

(reg-event-db :initialize-db (constantly default-db))

(reg-event-db :navigated
              (fn [db [_ new-match]]
                (let [old-match   (:current-route db)
                      controllers (rfc/apply-controllers (:controllers old-match) new-match)]
                  (assoc db :current-route (assoc new-match :controllers controllers)))))

(reg-event-fx :navigate
              (fn [_ [_ route]]
                {:navigate! route}))