package com.flifi.tot.commons.util;

import java.util.Random;

/**
 * Created by SSG on 8/14/15.
 * <p>
 * Goo.gl uses 6 char Base62 for shortening urls -syam
 * tiny-url and bit.ly uses Base 62 for the abbreviated URLs.
 * It's a well-understood method for creating "unique", human-readable IDs.
 * Of course you will have to store the created IDs and check for duplicates on creation to ensure uniqueness. (See code at bottom of answer)
 * <p>
 * Base 62 uniqueness metrics
 * -------------------------
 * 5 chars in base 62 will give you 62^5 unique IDs = 916,132,832 (~1 billion) At 10k IDs per day you will be ok for 91k+ days
 * <p>
 * 6 chars in base 62 will give you 62^6 unique IDs = 56,800,235,584 (56+ billion) At 10k IDs per day you will be ok for 5+ million days
 * <p>
 * Base 36 uniqueness metrics
 * --------------------------
 * 6 chars will give you 36^6 unique IDs = 2,176,782,336 (2+ billion)
 * <p/>
 * 7 chars will give you 36^7 unique IDs = 78,364,164,096 (78+ billion)
 */
public class BaseGUIDGenerator {

    private static char[] _base62chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static Random _random = new Random();

    public static String GetBase62(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(_base62chars[_random.nextInt(62)]);

        return sb.toString();
    }

    /**
     * Sample to create a SLUG (safe url friendly unique names) slug = Strings.toSlug( Strings.joinFast( menuDto.name, BaseGuidGenerator.GetBase36( 6 ) ) );
     *
     * @param length
     * @return
     */
    public static String GetBase36(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(_base62chars[_random.nextInt(36)]);

        return sb.toString();
    }


    /**
     * demo(5, 6);
     *
     * @param count
     * @param length
     */
    public static void test(int count, int length) {
        System.out.println("-------------BASE 62-------------");
        // create five IDs of six, base 62 characters
        for (int i = 0; i < count; i++) System.out.println(BaseGUIDGenerator.GetBase62(length));
        System.out.println("-------------BASE 36-------------");
        // create five IDs of eight base 36 characters
        for (int i = 0; i < count; i++) System.out.println(BaseGUIDGenerator.GetBase36(length));
    }
}

