package com.flifi.tot.commons.graph;

public class Neuron implements Vertex {

    private Object value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
