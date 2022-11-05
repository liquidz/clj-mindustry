(ns clj-mindustry.content.block
  (:require
    [camel-snake-kebab.core :as csk]
    [clj-mindustry.constant.fx :as const.fx]
    [clj-mindustry.type.item-stack :as type.item-stack])
  (:import
    (mindustry.content
      Items)
    (mindustry.type
      Category
      ItemStack)))

(defmacro def-generic-crafter
  [{package :package crafter-name :name}]
  (let [prefix (gensym crafter-name)
        class-name (symbol (format "%s.%s" package (csk/->PascalCase prefix)))
        post-init (symbol (str prefix "post-init"))]
    `(do (defn ~post-init
           [this# _#]
           (set! (.-craftEffect this#) const.fx/pulverize-medium)
           (set! (.-outputItem this#) (ItemStack. Items/graphite 1))
           (set! (.-craftTime this#) 90)
           (set! (.-size this#) 2)
           (set! (.-hasItems this#) true)
           (.requirements this#
                          Category/crafting
                          (type.item-stack/with Items/copper 4 Items/lead 9))
           (.consumeItem this# Items/coal (int 2)))
         (gen-class
           :name ~(str class-name)
           :extends mindustry.world.blocks.production.GenericCrafter
           :post-init "post-init"
           :prefix ~(str prefix)
           :main false)

         (defn ~(symbol (str "new-" crafter-name))
           []
           (new ~class-name ~(str crafter-name))))))

(def-generic-crafter
  {:package clj-mindustry.content.block
   :name wani-press5})

(comment (compile 'crescendo.content.block))
