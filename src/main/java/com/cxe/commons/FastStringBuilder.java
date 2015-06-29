/*
 * Copyright (c) 2003, 2012, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package com.cxe.commons;

/**
 * A mutable sequence of characters. This class provides an API compatible with
 * {@code StringBuffer}, but with no guarantee of synchronization. This class is
 * designed for use as a drop-in replacement for {@code StringBuffer} in places
 * where the string buffer was being used by a single thread (as is generally
 * the case). Where possible, it is recommended that this class be used in
 * preference to {@code StringBuffer} as it will be faster under most
 * implementations.
 *
 * <p>
 * The principal operations on a {@code StringBuilder} are the {@code append}
 * and {@code insert} methods, which are overloaded so as to accept data of any
 * type. Each effectively converts a given datum to a string and then appends or
 * inserts the characters of that string to the string builder. The
 * {@code append} method always adds these characters at the end of the builder;
 * the {@code insert} method adds the characters at a specified point.
 * <p>
 * For example, if {@code z} refers to a string builder object whose current
 * contents are "{@code start}", then the method call {@code z.append("le")}
 * would cause the string builder to contain "{@code startle}", whereas
 * {@code z.insert(4, "le")} would alter the string builder to contain
 * "{@code starlet}".
 * <p>
 * In general, if sb refers to an instance of a {@code StringBuilder}, then
 * {@code sb.append(x)} has the same effect as
 * {@code sb.insert(sb.length(), x)}.
 * <p>
 * Every string builder has a capacity. As long as the length of the character
 * sequence contained in the string builder does not exceed the capacity, it is
 * not necessary to allocate a new internal buffer. If the internal buffer
 * overflows, it is automatically made larger.
 *
 * <p>
 * Instances of {@code StringBuilder} are not safe for use by multiple threads.
 * If such synchronization is required then it is recommended that
 * {@link java.lang.StringBuffer} be used.
 *
 * <p>
 * Unless otherwise noted, passing a {@code null} argument to a constructor or
 * method in this class will cause a {@link NullPointerException} to be thrown.
 *
 * @author Michael McCloskey
 * @see java.lang.StringBuffer
 * @see java.lang.String
 * @since 1.5
 */
public final class FastStringBuilder
        extends FastAbstractStringBuilder
        implements java.io.Serializable, CharSequence {

    /**
     * use serialVersionUID for interoperability
     */
    static final long serialVersionUID = 4383685877147921099L;

    /**
     * Constructs a string builder with no characters in it and an initial
     * capacity of 16 characters.
     */
    public FastStringBuilder()
    {
        super(1024);
    }

    /**
     * Constructs a string builder with no characters in it and an initial
     * capacity specified by the {@code capacity} argument.
     *
     * @param capacity the initial capacity.
     * @throws NegativeArraySizeException if the {@code capacity} argument is
     * less than {@code 0}.
     */
    public FastStringBuilder(int capacity)
    {
        super(capacity);
    }

    /**
     * Constructs a string builder initialized to the contents of the specified
     * string. The initial capacity of the string builder is {@code 16} plus the
     * length of the string argument.
     *
     * @param str the initial contents of the buffer.
     */
    public FastStringBuilder(String str)
    {
        super(str.length() + 16);
        append(str);
    }

    /**
     * Constructs a string builder that contains the same characters as the
     * specified {@code CharSequence}. The initial capacity of the string
     * builder is {@code 16} plus the length of the {@code CharSequence}
     * argument.
     *
     * @param seq the sequence to copy.
     */
    public FastStringBuilder(CharSequence seq)
    {
        this(seq.length() + 16);
        append(seq);
    }

    public final void reset(int newCapacity)
    {
        value = null;
        value = new char[newCapacity];
        count = 0;
    }

    @Override
    public FastStringBuilder append(Object obj)
    {
        return append(String.valueOf(obj));
    }
    
    public FastStringBuilder append(Long obj)
    {
        return append(obj.toString());
    }

    @Override
    public FastStringBuilder append(String str)
    {
        super.append(str);
        return this;
    }

    @Override
    public FastStringBuilder append(String str, int length)
    {
        super.append(str, length);
        return this;
    }

    /**
     * Appends the specified {@code StringBuffer} to this sequence.
     * <p>
     * The characters of the {@code StringBuffer} argument are appended, in
     * order, to this sequence, increasing the length of this sequence by the
     * length of the argument. If {@code sb} is {@code null}, then the four
     * characters {@code "null"} are appended to this sequence.
     * <p>
     * Let <i>n</i> be the length of this character sequence just prior to
     * execution of the {@code append} method. Then the character at index
     * <i>k</i> in the new character sequence is equal to the character at index
     * <i>k</i> in the old character sequence, if <i>k</i> is less than
     * <i>n</i>; otherwise, it is equal to the character at index <i>k-n</i>
     * in the argument {@code sb}.
     *
     * @param sb the {@code StringBuffer} to append.
     * @return a reference to this object.
     */
    public FastStringBuilder append(StringBuffer sb)
    {
        super.append(sb);
        return this;
    }

    @Override
    public FastStringBuilder append(CharSequence s)
    {
        super.append(s);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder append(CharSequence s, int start, int end)
    {
        super.append(s, start, end);
        return this;
    }

    @Override
    public FastStringBuilder append(char[] str)
    {
        super.append(str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder append(char[] str, int offset, int len)
    {
        super.append(str, offset, len);
        return this;
    }

    @Override
    public FastStringBuilder append(boolean b)
    {
        super.append(b);
        return this;
    }

    @Override
    public FastStringBuilder append(char c)
    {
        super.append(c);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder delete(int start, int end)
    {
        super.delete(start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder deleteCharAt(int index)
    {
        super.deleteCharAt(index);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int index, char[] str, int offset,
            int len)
    {
        super.insert(index, str, offset, len);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, Object obj)
    {
        super.insert(offset, obj);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, String str)
    {
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, char[] str)
    {
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int dstOffset, CharSequence s)
    {
        super.insert(dstOffset, s);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int dstOffset, CharSequence s,
            int start, int end)
    {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, boolean b)
    {
        super.insert(offset, b);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, char c)
    {
        super.insert(offset, c);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, int i)
    {
        super.insert(offset, i);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, long l)
    {
        super.insert(offset, l);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, float f)
    {
        super.insert(offset, f);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public FastStringBuilder insert(int offset, double d)
    {
        super.insert(offset, d);
        return this;
    }

    @Override
    public FastStringBuilder reverse()
    {
        super.reverse();
        return this;
    }

    @Override
    public String toString()
    {
        // Create a copy, don't share the array
        return new String(value, 0, count);
    }
    
    public final String toString(int reduceFromEnd)
    {
        // Create a copy, don't share the array
        return new String(value, 0, count - reduceFromEnd);
    }

    public byte[] fastByteArray()
    {
        byte[] b = new byte[count];
        for (int i = 0; i < count; i++)
        {
            b[i] = (byte) value[i];
        }
        return b;
    }

    /**
     * Save the state of the {@code StringBuilder} instance to a stream (that
     * is, serialize it).
     *
     * @serialData the number of characters currently stored in the string
     * builder ({@code int}), followed by the characters in the string builder
     * ({@code char[]}). The length of the {@code char} array may be greater
     * than the number of characters currently stored in the string builder, in
     * which case extra characters are ignored.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException
    {
        s.defaultWriteObject();
        s.writeInt(count);
        s.writeObject(value);
    }

    /**
     * readObject is called to restore the state of the StringBuffer from a
     * stream.
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        count = s.readInt();
        value = (char[]) s.readObject();
    }
    
    public final void reduce(int reduceCount)
    {
        count = count - reduceCount;
    }
}
