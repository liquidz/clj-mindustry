.PHONY: build-java
build-java:
	clojure -T:build java-compile :class-dir '"classes"'

.PHONY: clean
clean:
	rm -rf target .cpcache
