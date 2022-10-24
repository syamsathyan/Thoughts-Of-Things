package com.flifi.tot.commons.collection;

import com.flifi.tot.commons.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Fast and Fixed List Collection implementation
 * Wrote as a basic necessity for implementing other Collections
 * Created by sathyasy on 9/4/15.
 */
public class FastFixedList<V> implements Collection<V> {

    int count;
    Object[] values;
    int [] hashlist;

    public FastFixedList(int size) {
        if (size > 0) {
            values = new Object[size];
            hashlist = new int[size];
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
                return (FastFixedList.this.count < counter);
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
        return Arrays.copyOf(values, count);
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
            //current counter slot is always empty
            values[count] = v;
            count++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < values.length; i++) {
            if (o.equals(values[i])) {
                //reduce size (now count points to a non empty slot for swapping into the newly emptied slot)
                count--;
                //Swap last item to emptied location
                values[i] = values[count];
                values[count] = null;
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
        return StringUtils.joinFast("FastFixedSet[Size:", String.valueOf(count), ", Capacity:", String.valueOf(values.length), "]");
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
