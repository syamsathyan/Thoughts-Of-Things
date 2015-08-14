package com.cxe.commons;

import java.util.Random;

/**
 * Created by sathyasy on 8/14/15.
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
     * TestRandomIdGenerator(5, 6);
     * @param count
     * @param length
     */
    public static void TestRandomIdGenerator(int count, int length) {
        System.out.println("-------------BASE 62-------------");
        // create five IDs of six, base 62 characters
        for (int i = 0; i < count; i++) System.out.println(BaseGUIDGenerator.GetBase62(length));
        System.out.println("-------------BASE 36-------------");
        // create five IDs of eight base 36 characters
        for (int i = 0; i < count; i++) System.out.println(BaseGUIDGenerator.GetBase36(length));
    }
}

