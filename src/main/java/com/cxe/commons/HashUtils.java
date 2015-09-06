package com.cxe.commons;

import java.util.Random;

/**
 * @author Syam Sathyan
 */
public class HashUtils {

    public static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
