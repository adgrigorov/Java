package com.company.database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//May be of type String or Integer
public class Column<T> implements Serializable {
    private String name;
    private String type;
    private List<T> rows;
    //private Map<String, List<T>> mapValues;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Column() {
        this.name = null;
        this.rows = null;
    }

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
        this.rows = new ArrayList<T>();
    }

    public Column(String name) {
        this.name = name;
        this.rows = new ArrayList<T>();
    }

    public Column(String name, T value) {
        this.name = name;
        this.rows = new ArrayList<T>();
        this.rows.add(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void addValue(T value) {
        this.rows.add(value);
    }

    public void addValues(List<T> values) {
        this.rows.addAll(values);
    }

    public void addValueAtPosition(int position, T value) {
        this.rows.add(position, value);
    }

    public T getValueFromPosition(int position) {
        return this.rows.get(position);
    }

    protected void clearValues() {
        this.rows.clear();
    }

    public void writeValueToFile(String tableName, String value) {
        String dir = System.getProperty("user.dir");
        String path = dir + "\\Database\\Helpers\\" + tableName + "_" + this.name + "_values";

        try {
            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(value);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
