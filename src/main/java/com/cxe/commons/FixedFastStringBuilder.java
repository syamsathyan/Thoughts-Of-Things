package com.cxe.commons;

/**
 *
 * @author Syam Sathyan
 */
public final class FixedFastStringBuilder {

    int count;
    char value[];

    public FixedFastStringBuilder(int capacity)
    {
        value = new char[capacity];
    }

    public final void reduce(int reduceCount)
    {
        count = count - reduceCount;
    }
    
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
