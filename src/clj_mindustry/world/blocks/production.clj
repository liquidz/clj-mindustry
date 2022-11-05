(ns clj-mindustry.world.blocks.production
  (:require
   [camel-snake-kebab.core :as csk]
   [clj-mindustry.content.fx :as content.fx]
   [clj-mindustry.type.item-stack :as type.item-stack])
  (:import
   (mindustry.content
    Items)
   (mindustry.type
    Category
    ItemStack)))

(defmacro def-generic-crafter
  [crafter-name
   {:keys [craft-effect output-item craft-time size item-capacity
           drawer
           has-items has-liquids has-power
           requirements consume-item consume-items consume-power consume-liquid]}]
  (let [this (gensym 'this)
        prefix (gensym crafter-name)
        class-name (symbol (format "clj-mindustry.world.blocks.production.%s"
                                   (csk/->PascalCase prefix)))
        post-init (symbol (str prefix "post-init"))]
    `(do (defn ~post-init
           [~this _#]
           ~(when craft-effect
              `(set! (.-craftEffect ~this) ~craft-effect))
           ~(when output-item
              `(set! (.-outputItem ~this) ~output-item))
           ~(when craft-time
              `(set! (.-craftTime ~this) ~craft-time))
           ~(when drawer
              `(set! (.-drawer ~this) ~drawer))
           ~(when size
              `(set! (.-size ~this) ~size))
           ~(when item-capacity
              `(set! (.-itemCapacity ~this) ~item-capacity))
           ~(when has-items
              `(set! (.-hasItems ~this) ~has-items))
           ~(when has-liquids
              `(set! (.-hasLiquids ~this) ~has-liquids))
           ~(when has-power
              `(set! (.-hasPower ~this) ~has-power))
           ~(when-let [{:keys [category stacks]} requirements]
              `(.requirements ~this ~category ~stacks))
           ~(when-let [{:keys [item amount]} consume-item]
              `(.consumeItem ~this ~item ~amount))
           ~(when-let [{:keys [stack]} consume-items]
              `(.consumeItems ~this ~stack))
           ~(when-let [{:keys [per-tick]} consume-power]
              `(.consumePower ~this ~per-tick))
           ~(when-let [{:keys [liquid amount]} consume-liquid]
              `(.consumeLiquid ~this ~liquid ~amount)))
         (gen-class
          :name ~(str class-name)
          :extends mindustry.world.blocks.production.GenericCrafter
          :post-init "post-init"
          :prefix ~(str prefix)
          :main false)

         (defn ~crafter-name
           []
           (new ~class-name ~(str crafter-name))))))
