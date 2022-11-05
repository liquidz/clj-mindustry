(ns clj-mindustry.util.static
  (:import
    java.lang.reflect.Modifier))

(defn static-names
  [klass]
  (->> (.getDeclaredFields klass)
       (filter #(Modifier/isStatic (.getModifiers %)))
       (map #(.getName %))))
