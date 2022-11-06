(ns clj-mindustry.example.core
  (:require
   [clj-mindustry.example.content.block :as block]))

(defn -main
  [])

(defn -init
  [])

(defn -loadContent
  []
  (block/example-press)
  (block/example-conveyor))
