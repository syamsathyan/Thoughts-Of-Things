package com.cxe.commons.collection;

import com.cxe.commons.Strings;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Fast and Fixed Set Collection implementation
 * Wrote as a basic necessity for implementing other Collections
 * Created by sathyasy on 9/4/15.
 */
public class FastFixedSet<V> implements Set<V> {

    int count;
    Object[] values;

    public FastFixedSet(int size) {
        if (size > 0) {
            values = new Object[size];
        } else {
            throw new NullPointerException("Size cannot be less than 1");
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int counter = 0;

            @Override
            public boolean hasNext() {
                return (FastFixedSet.this.count < counter);
            }

            @Override
            public V next() {
                int t = counter;
                counter++;
                return (V) values[t];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * @param v
     * @return true upon acceptance of value into the collection / else false
     */
    @Override
    public boolean add(V v) {
        if (values.length == count) {
            return false;
        } else {
            //See if current counter pointing slot is empty
            if (values[count] == null) {
                values[count] = v;
                count++;
                return true;
            } else {
                //Full iteration
                for (int i = 0; i < values.length; i++) {
                    if (values[i] == null) {
                        values[i] = v;
                        count++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < values.length; i++) {
            if (values[i]!=null && values[i].equals(o)) {
                values[i] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
        count = 0;
    }

    @Override
    public String toString() {
        return Strings.joinFast("FastFixedSet[Size:", String.valueOf(count), ", Capacity:", String.valueOf(values.length), "]");
    }
}
