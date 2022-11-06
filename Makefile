.PHONY: build-java
build-java:
	clojure -T:build java-compile :class-dir '"classes"'

.PHONY: example-install
example-install:
	clojure -T:build uberjar
	cp -pf target/clj-mindustry-example.jar $(HOME)/Library/Application\ Support/Mindustry/mods

.PHONY: clean
clean:
	rm -rf target .cpcache
