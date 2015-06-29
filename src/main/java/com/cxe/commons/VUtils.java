package com.cxe.commons;

import java.util.Random;

/**
 *
 * @author Syam Sathyan
 */
public class VUtils {

    /**
     * Performs a byte array comparison with a constant time
     *
     * @param a A byte array
     * @param b Another byte array
     * @return True if the byte array have equal contents
     */
    public static boolean fullByteCompare(byte[] a, byte[] b) {
        if (a == null || b == null || a.length != b.length)
        {
            return false;
        } else
        {
            int result = 0;
            for (int i = 0; i < a.length; i++)
            {
                result |= a[i] ^ b[i];
            }
            return result == 0;
        }
    }

    private static final Random rand = new Random();

    public static int randInt(int min, int max) {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
