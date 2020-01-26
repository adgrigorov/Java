package com.company.database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {

    private String name;
    private List<Column<?>> columns;
    private static int columnsCount;
   // private Map<String, List<Column<?>>> columnsMap;

    public Table() {
        this.name = null;
        this.columns = null;
        columnsCount = 0;
    }

    public Table(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
        columnsCount = this.columns.size();
    }

    public String getName() {
        return name;
    }

    public List<Column<?>> getColumns() { return columns; }

    public void addColumn(Column<?> column) {
        this.columns.add(column);
        columnsCount++;
    }

    public void addColumns(List<Column<?>> columns) {
        this.columns.addAll(columns);
    }

    public void removeColumn(Column<?> column) {
        this.columns.remove(column);
    }

    public void tableInfo(Table table) {
        System.out.printf("Table %s (", table.getName());
        for (int i = 0; i < this.columns.size(); i++) {
            if (i != this.columns.size() - 1) {
                System.out.printf("%s:%s, ", this.columns.get(i).getName(),
                        this.columns.get(i).getType());
            } else {
                System.out.printf("%s:%s", this.columns.get(i).getName(),
                        this.columns.get(i).getType());
            }
        }
        System.out.print(")\n");
        if (this.columns.get(0).getRows() != null) {
            System.out.println(String.format("Total %d rows", this.columns.get(0).getRows().size()));
        }
        else System.out.println("Total 0 rows");
    }

    public void printColumns() {
        for (Column<?> column : this.columns) {
            System.out.print("COLUMN: ");
            System.out.println(column.getName());
            //int columnDataSize = column.getValues().size();
            List<?> columnValues = column.getRows();
            System.out.println("VALUES:");
            for (Object columnValue : columnValues) {
                System.out.println("---" + columnValue);
            }
        }
    }

    public void writeColumnsToFile() {
        String dir = System.getProperty("user.dir");
        String path = dir + "\\Database\\Helpers\\" + this.name + "_columns";

        try {
            for (Column c : this.columns)
            {
                FileOutputStream f = new FileOutputStream(path);
                ObjectOutputStream o = new ObjectOutputStream(f);

                o.writeObject(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public void writeTableToFile() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Database\\" + this.name + ".txt", true);
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(writer)) {

            out.print();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
