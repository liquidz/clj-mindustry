(ns clj-mindustry.world.blocks.distribution
  (:require
   [camel-snake-kebab.core :as csk]))

(defmacro def-conveyor
  [conveyor-name
   {:keys [requirements speed displayed-speed]}]
  (let [this (gensym 'this)
        prefix (gensym conveyor-name)
        class-name (symbol (format "clj-mindustry.world.blocks.distribution.%s"
                                   (csk/->PascalCase prefix)))
        post-init (symbol (str prefix "post-init"))]
    `(do (defn ~post-init
           [~this _#]
           ~(when speed
              `(set! (.-speed ~this) ~speed))
           ~(when displayed-speed
              `(set! (.-displayedSpeed ~this) ~displayed-speed))
           ~(when-let [{:keys [stacks]} requirements]
              `(.requirements ~this Category/distribution ~stacks)))
         (gen-class
          :name ~(str class-name)
          :extends mindustry.world.blocks.distribution.Conveyor
          :post-init "post-init"
          :prefix ~(str prefix)
          :main false)

         (defn ~conveyor-name
           []
           (new ~class-name ~(str conveyor-name))))))
