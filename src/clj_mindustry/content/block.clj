(ns clj-mindustry.content.block
  (:require
   [clj-mindustry.type.item-stack :as item-stack]
   [clj-mindustry.world.blocks.production :as blocks])
  (:import
   arc.graphics.Color
   (mindustry.content
    Fx
    Items)
   mindustry.type.Category
   (mindustry.world.draw
    DrawBlock
    DrawDefault
    DrawFlame
    DrawMulti)))

(blocks/def-generic-crafter
 wani-press
 {:craft-effect Fx/pulverizeMedium
  :output-item (item-stack/stack Items/graphite 1)
  :craft-time 90
  :size 2
  :has-items true
  ;; :drawer (DrawMulti. (into-array DrawBlock [(DrawDefault.)
  ;;                                            (DrawFlame. (Color/valueOf "ffc099"))]))
  :requirements {:category Category/crafting
                 :stacks (item-stack/with
                          Items/copper 3
                          Items/lead 8)}
  :consume-items {:stack (item-stack/with Items/coal 1 Items/sand 1)}})
  ;; :consume-item {:item items/coal
  ;;                :amount 5}})

(comment (compile 'crescendo.content.block))
