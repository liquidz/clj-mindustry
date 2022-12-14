(ns clj-mindustry.example.content.block
  (:require
   [clj-mindustry.type.item-stack :as item-stack]
   [clj-mindustry.world.blocks.distribution :as distribution]
   [clj-mindustry.world.blocks.production :as production])
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

(production/def-generic-crafter
 example-press
 {:craft-effect Fx/pulverizeMedium
  :output-item (item-stack/stack Items/graphite 1)
  :craft-time 90
  :size 2
  :has-items true
  :requirements {:stacks (item-stack/with
                          Items/copper 3
                          Items/lead 8)}
  :consume-items {:stack (item-stack/with Items/coal 1 Items/sand 1)}})

(distribution/def-conveyor
 example-conveyor
 {:speed 0.5
  :displayed-speed 11
  :requirements {:stacks (item-stack/with Items/copper 1)}})
