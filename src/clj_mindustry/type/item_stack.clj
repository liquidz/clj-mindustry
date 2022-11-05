(ns clj-mindustry.type.item-stack
  (:import
    mindustry.type.ItemStack))

(defn stack
  [item amount]
  (ItemStack. item amount))

(defn with
  [& coll]
  (ItemStack/with (object-array coll)))
