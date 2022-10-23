package com.siot.commons.util;

/**
 * @author SSG
 */
public class HashUtils {

    public static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
