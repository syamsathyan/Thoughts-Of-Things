package com.cxe.commons;

import java.math.BigDecimal;

/**
 *
 * @author Syam Sathyan
 */
public class VMath {

    /**
     * Encode a byte array.
     *
     * @param base
     * @return
     */
    private static final char[] TOHEX = "0123456789abcdef".toCharArray();
    public static String byteToHex(byte[] base)
    {
        char[] c = new char[base.length * 2];
        int i = 0;

        for (byte b : base)
        {
            int j = b;
            j = j + 128;
            c[i++] = TOHEX[j / 0x10];
            c[i++] = TOHEX[j % 0x10];
        }
        return new String(c);
    }
    
    public static final String ordinal(int i)
    {
        String[] sufixes = new String[]
        {
            "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"
        };
        switch (i % 100)
        {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    public static final double truncateNoRound_6(double location)
    {
        return Math.floor(location * 1000000) / 1000000;
    }

    public static final double truncateNoRound(double location, int round)
    {
        return Math.floor(location * (10 * round)) / (10 * round);
    }
}
