package com.ve.cxe.commons.collection;

import com.ve.cxe.commons.Strings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Fast and Fixed List Collection implementation with Pump features added for fixed element cycling, the pumped elements are never wasted / lost -  rather cycled in a circular loop
 * Wrote as a basic necessity for implementing  Server generated Random UI Sweep for Ad Banners
 * <p/>
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access an <tt>ArrayList</tt> instance concurrently,
 * and at least one of the threads modifies the list structurally, it
 * <i>must</i> be synchronized externally.  (A structural modification is
 * any operation that adds or deletes one or more elements, or explicitly
 * resizes the backing array; merely setting the value of an element is not
 * a structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the list.
 * <p/>
 * Created by sathyasy on 9/13/15.
 * <p/>
 */
public class CyclicFixedPump<V> implements Collection<V> {

    int current;
    int cycleMaker;
    Object[] values;
    int[] hashlist;
    final int pumpingVolume;
    Object[] valve;
    private static final Object[] EMPTY_CONTAINER = {};

    public CyclicFixedPump(int size, int pumpingVolume) {
        if (size > 0) {
            values = new Object[size];
            hashlist = new int[size];
            this.pumpingVolume = pumpingVolume;
            valve = new Object[pumpingVolume];
        } else {
            throw new NullPointerException("Size cannot be less than 1");
        }
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return current == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int currenter = 0;

            @Override
            public boolean hasNext() {
                return (CyclicFixedPump.this.current < currenter);
            }

            @Override
            public V next() {
                int t = currenter;
                currenter++;
                return (V) values[t];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(values, current);
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
        if (values.length == current) {
            evictElement(0);
            addAtEnd(v);
        } else {
            addAtEnd(v);
        }
        return true;
    }

    @Override
    public final boolean remove(Object o) {
        for (int i = 0; i < values.length; i++) {
            if (o.equals(values[i])) {
                evictElement(i);
                return true;
            }
        }
        return false;
    }

    private final void addAtEnd(V v){
        //current slot is always empty, point new item there
        values[current] = v;
        current++;
    }

    private final void evictElement(int i) {
        //reduce size (now current points to a non empty slot for swapping into the newly emptied slot)
        current--;
        //Swap last item to emptied location
        values[i] = values[current];
        values[current] = null;
    }

    /**
     * @return Empty when pump runs dry, else pumping volume quantity is pumped out
     * Returned Array is not a new Object, rather the valve with the last pumped volume is returned
     */
    public Object[] pump() {
        if (current == 0) {
            //Pump is running dry, and returns empty (never null)
            return EMPTY_CONTAINER;
        }
        int valveCounter = 0;
        while (valveCounter < pumpingVolume) {
            cycleMaker = cycleMaker == 0 ? current : cycleMaker;
            //Shrink the circular pointer
            cycleMaker--;
            //Cycle from Marker to valve
            valve[valveCounter] = values[cycleMaker];
            valveCounter++;
        }
        return valve;
    }

    /**
     * @param Object[] container to pump into, the length of the container is expected to be equal to that of Pumping Volume
     * @return Empty when pump runs dry, else pumping volume quantity is pumped out to the Container parameter
     */
    public Object[] pump(Object[] container) {
        if (current == 0 || container == null) {
            //Pump is running dry, and returns empty (never null)
            return EMPTY_CONTAINER;
        }
        //If a container is smaller than pumping volume then Container Overflow (IOOBExp) for sure, so reduce the pumping volume just for this case
        //Because - We do not entertain data loss due to overflow!!
        int tempPumpingVolume = container.length < pumpingVolume ? container.length : pumpingVolume;
        int valveCounter = 0;
        while (valveCounter < tempPumpingVolume) {
            cycleMaker = cycleMaker == 0 ? current : cycleMaker;
            //Shrink the circular pointer
            cycleMaker--;
            //Cycle from Marker to valve
            valve[valveCounter] = values[cycleMaker];
            //Copy the same into the Container ( valve has the same data, but user has placed a container to collect)
            container[valveCounter] = values[cycleMaker];
            valveCounter++;
        }
        return container;
    }

    /**
     * @return Last pumped volume (retained in valve) is given out
     * Safe residue, for status UI, High volume Web traffic etc
     */
    public Object[] lastDrop() {
        return valve;
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
        current = 0;
    }

    @Override
    public String toString() {
        return Strings.joinFast("FastFixedPump[Size:", String.valueOf(current), ", Capacity:", String.valueOf(values.length), "]");
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
