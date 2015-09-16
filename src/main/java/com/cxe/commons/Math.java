package com.cxe.commons;

/**
 * @author Syam Sathyan
 */
public class Math {

    final static String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};

    public static final String ordinal(int i) {

        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    public static final double truncateNoRound_6(double location) {
        return java.lang.Math.floor(location * 1000000) / 1000000;
    }

    public static final double truncateNoRound(double location, int round) {
        return java.lang.Math.floor(location * (10 * round)) / (10 * round);
    }
}
