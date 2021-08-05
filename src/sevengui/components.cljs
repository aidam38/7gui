(ns sevengui.components)

(defn button [on-click text]
  [:button.w-24.rounded.bg-yellow-200.hover:bg-yellow-300.m-1.p-1 {:on-click on-click} text])
