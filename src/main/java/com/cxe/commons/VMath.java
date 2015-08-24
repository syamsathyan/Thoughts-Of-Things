package com.cxe.commons;

import java.math.BigDecimal;

/**
 *
 * @author Syam Sathyan
 */
public class VMath {

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
