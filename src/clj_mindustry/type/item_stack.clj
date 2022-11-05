(ns clj-mindustry.type.item-stack
  (:import
    mindustry.type.ItemStack))

(defn with
  [& coll]
  (ItemStack/with (object-array coll)))
