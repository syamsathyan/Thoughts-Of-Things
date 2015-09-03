package com.cxe.commons;

import java.io.Serializable;

/**
 *
 * @author Syam Sathyan
 *
 * The Simplest StringBuilder based on a fixed char array
 * The backing Array is of fixed length and cannot Grow like a typical StringBuilderImplementation - hence the name FixedFastStringBuilder
 * Always Pre-initialize the size for the maximum needed character length.
 */
public final class FixedFastStringBuilder implements Serializable, CharSequence{

    int count;
    char value[];

    /**
     * Pre-initialize the size the maximum needed character length.
     * @param capacity
     */
    public FixedFastStringBuilder(int capacity)
    {
        value = new char[capacity];
    }

    /**
     * Reduce the Pre-initialized size.
     * @param capacity
     */
    public final void reduce(int reduceCount)
    {
        count = count - reduceCount;
    }

    /**
     * Clear the data and re-initialized size.
     * Good for re-use strategy
     * @param capacity
     */
    public final void reset(int newCapacity)
    {
        value = null;
        value = new char[newCapacity];
        count = 0;
    }

    public final void append(FixedFastStringBuilder builder)
    {
        int len = builder.count;
        System.arraycopy(builder.value, 0, value, count, len);
        count += len;
    }
    /**
     * The length makes it easy to chop unwanted character contained in the incoming builder
     * @param builder
     * @param length 
     */
    public final void append(FixedFastStringBuilder builder, int length)
    {
        System.arraycopy(builder.value, 0, value, count, length);
        count += length;
    }

    public final void append(String str)
    {
        int len = str.length();
        str.getChars(0, len, value, count);
        count += len;
    }

    public final void append(char c)
    {
        value[count] = c;
        count += 1;
    }

    /**
     * The fastest append method with pre-calculated length
     *
     * @param str - string to be appended to this builder
     * @param len - length of the incoming string
     */
    public final void append(String str, int len)
    {
        str.getChars(0, len, value, count);
        count += len;
    }

    /**
     * Returns the length (character count).
     *
     * @return the length of the sequence of characters currently represented by
     * this object
     */
    public final int length()
    {
        return count;
    }

    @Override
    public char charAt(int index) {
        return value[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new String(value, start, end);
    }

    @Override
    public final String toString()
    {
        // Create a copy, don't share the array
        return new String(value, 0, count);
    }

    public final String toString(int reduceFromEnd)
    {
        // Create a copy, don't share the array
        return new String(value, 0, count - reduceFromEnd);
    }

    public final byte[] fastByteArray()
    {
        byte[] b = new byte[count];
        for (int i = 0; i < count; i++)
        {
            b[i] = (byte) value[i];
        }
        return b;
    }

    public final FixedFastStringBuilder reverse()
    {
        boolean hasSurrogates = false;
        int n = count - 1;
        for (int j = (n - 1) >> 1; j >= 0; j--)
        {
            int k = n - j;
            char cj = value[j];
            char ck = value[k];
            value[j] = ck;
            value[k] = cj;
            if (Character.isSurrogate(cj)
                    || Character.isSurrogate(ck))
            {
                hasSurrogates = true;
            }
        }
        if (hasSurrogates)
        {
            reverseAllValidSurrogatePairs();
        }
        return this;
    }

    /**
     * Outlined helper method for reverse()
     */
    private final void reverseAllValidSurrogatePairs()
    {
        for (int i = 0; i < count - 1; i++)
        {
            char c2 = value[i];
            if (Character.isLowSurrogate(c2))
            {
                char c1 = value[i + 1];
                if (Character.isHighSurrogate(c1))
                {
                    value[i++] = c1;
                    value[i] = c2;
                }
            }
        }
    }
}
