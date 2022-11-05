(ns clj-mindustry.constant.fx
  (:refer-clojure :exclude [rand])
  (:require
    [camel-snake-kebab.core :as csk])
  (:import
    java.lang.reflect.Modifier
    mindustry.content.Fx))

(def ^:private static-names
  (->> (.getDeclaredFields Fx)
       (filter #(Modifier/isStatic (.getModifiers %)))
       (map #(.getName %))))

(defmacro def-fields
  []
  (cons 'do
        (map (fn [name]
               (let [kebab-name (symbol (csk/->kebab-case name))
                     fx-name (symbol (format "Fx/%s" name))]
                 `(def ~kebab-name ~fx-name)))
             static-names)))

(def-fields)
