package com.flifi.tot.commons.util;

import java.util.Random;

/**
 * @author SSG
 */
public class ByteUtils {

    /**
     * Performs a byte array comparison with a constant time
     *
     * @param a A byte array
     * @param b Another byte array
     * @return True if the byte array have equal contents
     * <p/>
     * Suitable for strong full length passcode byte comparison
     */
    public static boolean fullByteCompare(byte[] a, byte[] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        } else {
            int result = 0;
            for (int i = 0; i < a.length; i++) {
                result |= a[i] ^ b[i];
            }
            return result == 0;
        }
    }

    private static final Random rand = new Random();

    public static int randomRange(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16) {
                    str = str + "0"
                            + java.lang.Integer.toHexString(data[i] & 0xFF);
                } else {
                    str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
                }

            }
            return str;
        }
    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2,
                        i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    /**
     * Encodes a byte array (into what i forgot)
     *
     * @param base
     * @return
     */
    //TODO compare the outcome of the above methods with this and eliminate the worst
    private static final char[] TOHEX = "0123456789abcdef".toCharArray();

    public static String byteToHex(byte[] base) {
        char[] c = new char[base.length * 2];
        int i = 0;

        for (byte b : base) {
            int j = b;
            j = j + 128;
            c[i++] = TOHEX[j / 0x10];
            c[i++] = TOHEX[j % 0x10];
        }
        return new String(c);
    }
}
