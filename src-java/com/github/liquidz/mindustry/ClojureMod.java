package com.github.liquidz.mindustry;

import clojure.java.api.Clojure;
import mindustry.mod.Mod;

// https://sparkofreason.github.io/jvm-clojure-google-cloud-function/
public class ClojureMod extends Mod {
    public static final String core = "clj-mindustry.core";
    static {
        Thread.currentThread().setContextClassLoader(ClojureMod.class.getClassLoader());
        Clojure.var("clojure.core", "require").invoke(Clojure.read(core));
    }

    public ClojureMod() {
        Clojure.var(core, "-main").invoke();
    }

    @Override
    public void init() {
        Clojure.var(core, "-init").invoke();
    }

    @Override
    public void loadContent() {
        Clojure.var(core, "-loadContent").invoke();
    }
}
// vim:et
