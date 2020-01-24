package com.company.database;

import java.util.ArrayList;
import java.util.List;

//May be of type String or Integer
public class Column<T> {
    private String name;
    private String type;
    private List<T> values;
    //private Map<String, List<T>> mapValues;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Column() {
        this.name = null;
        this.values = null;
    }

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
        this.values = new ArrayList<T>();
    }

    public Column(String name) {
        this.name = name;
        this.values = new ArrayList<T>();
    }

    public Column(String name, T value) {
        this.name = name;
        this.values = new ArrayList<T>();
        this.values.add(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public void addValue(T value) {
        this.values.add(value);
    }

    public void addValues(List<T> values) {
        this.values.addAll(values);
    }

    public void addValueAtPosition(int position, T value) {
        this.values.add(position, value);
    }

    public T getValueFromPosition(int position) {
        return this.values.get(position);
    }

    protected void clearValues() {
        this.values.clear();
    }
}
