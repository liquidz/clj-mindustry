package com.github.liquidz.mindustry;

import java.io.*;
import java.util.Properties;
import clojure.java.api.Clojure;
import mindustry.mod.Mod;

// https://sparkofreason.github.io/jvm-clojure-google-cloud-function/
public class ClojureMod extends Mod {
    public static String core = null;
    public static final Properties properties = new Properties();

    static {
        InputStream is = ClojureMod.class.getResourceAsStream("/com/github/liquidz/mindustry/config.properties");
        try {
            properties.load(is);
            core = properties.getProperty("core");
            Thread.currentThread().setContextClassLoader(ClojureMod.class.getClassLoader());

            if (core != null) {
                Clojure.var("clojure.core", "require").invoke(Clojure.read(core));
            }
        } catch (IOException ex) { }
    }

    public ClojureMod() {
        if (core != null) {
            Clojure.var(core, "-main").invoke();
        }
    }

    @Override
    public void init() {
        if (core != null) {
            Clojure.var(core, "-init").invoke();
        }
    }

    @Override
    public void loadContent() {
        if (core != null) {
            Clojure.var(core, "-loadContent").invoke();
        }
    }
}
// vim:et
