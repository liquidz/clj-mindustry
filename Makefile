.PHONY: build-java
build-java:
	clojure -T:build java-compile

.PHONY: build
build: clean
	clojure -T:build pipe :fns '[java-compile uberjar]'

.PHONY: install
install: clean build
	cp -pf target/foobar.jar /Users/iizukamasashi/Library/Application\ Support/Mindustry/mods

.PHONY: clean
clean:
	rm -rf target .cpcache
