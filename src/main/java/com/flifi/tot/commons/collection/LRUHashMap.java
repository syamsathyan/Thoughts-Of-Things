package com.flifi.tot.commons.collection;

import java.util.HashMap;

class LruMapEntry {
    Object value;
    Object key;
    LruMapEntry left;
    LruMapEntry right;
}

public class LRUHashMap extends HashMap {

    LruMapEntry start, end;
    private static final int DEFAULT_LRU_SIZE = 4;
    int LRU_SIZE = DEFAULT_LRU_SIZE;

    public LRUHashMap(int size) {
        super(size);
        this.LRU_SIZE = size;
    }

    @Override
    public Object get(Object key) {
        if (super.containsKey(key)) // Key Already Exist, just update the
        {
            LruMapEntry entry = (LruMapEntry) super.get(key);
            removeNode(entry);
            addAtTop(entry);
            return entry.value;
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        if (super.containsKey(key)) // Key Already Exist, just update the value and move it to top
        {
            LruMapEntry entry = (LruMapEntry) super.get(key);
            Object oldVal = entry.value;
            entry.value = value;
            removeNode(entry);
            addAtTop(entry);
            return oldVal;
        } else {
            LruMapEntry newnode = new LruMapEntry();
            newnode.left = null;
            newnode.right = null;
            newnode.value = value;
            newnode.key = key;
            if (super.size() > LRU_SIZE) // We have reached maximum size so need to make room for new element.
            {
                super.remove(end.key);
                removeNode(end);
                addAtTop(newnode);

            } else {
                addAtTop(newnode);
            }

            super.put(key, newnode);
            return null;
        }
    }

    public void addAtTop(LruMapEntry node) {
        node.right = start;
        node.left = null;
        if (start != null)
            start.left = node;
        start = node;
        if (end == null)
            end = start;
    }

    public void removeNode(LruMapEntry node) {

        if (node.left != null) {
            node.left.right = node.right;
        } else {
            start = node.right;
        }

        if (node.right != null) {
            node.right.left = node.left;
        } else {
            end = node.left;
        }
    }
}
