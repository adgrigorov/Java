package com.company.database;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Database extends Column {
    private List<Table> database;
    //Map<String, List<Table>> mapDb;

    public Database() {
        this.database = new ArrayList<>();
    }

    public void createTable(String name, String columnsToAdd) {
        Table table = new Table(name);
        String[] columns = columnsToAdd.split(",");

        if (database.size() > 0) {
            for (Table t : database) {
                if (t.getName().equals(name)) {
                    System.out.println("TABLE WITH NAME " + name + " ALREADY EXISTS.");
                    return;
                }
            }
        }

        for (String column : columns) {
            int index = column.indexOf(":");

            if (column.substring(index + 1).equals("Int")) {
                String columnName = column.substring(0, index);
                Column<Integer> c = new Column<>(columnName, "Int");
                table.addColumn(c);
            } //end if
            else if (column.substring(index + 1).equals("String")) {
                String columnName = column.substring(0, index);
                Column<String> tableColumn = new Column<>(columnName, "String");
                table.addColumn(tableColumn);
            } //end else if
            else {
                System.out.println("INVALID COLUMN TYPE");
                return;
            }
        } //end foreach
        database.add(table);
        System.out.println("TABLE " + name + " CREATED.");
    }

    public void dropTable(String table) throws Exception {
        if (database.size() == 0) {
            System.out.println("DATABASE IS EMPTY");
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("CONFIRM DROP TABLE ".concat(table));
            System.out.println("YES\t/\tNO");
            String confirmation = reader.readLine();
            if (confirmation.equals("YES")) {
                int tableIndex = -1;
                for (int i = 0; i < database.size(); i++) {
                    if (database.get(i).getName().equals(table)) {
                        tableIndex = i;
                        break;
                    }
                } //end for

                if (tableIndex != -1) {
                    database.remove(tableIndex);
                    System.out.println("TABLE " + table + " DROPPED.");
                } else {
                    System.out.println("NO SUCH TABLE EXISTS.");
                }
            } else if (confirmation.equals("NO")) {
                System.out.println("DROP OPERATION ABORTED.");
            }
        }
    }

    public void listTables() {
        if (database.isEmpty()) {
            System.out.println("DATABASE IS EMPTY.");
            return;
        }

        for (Table table : database) {
            System.out.println("--" + table.getName());
        }
    }

    public void showTableInfo(String table) {
        boolean foundTable = false;

        for (Table t : database) {
            if (t.getName().equals(table)) {
                t.tableInfo(t);
                foundTable = true;
            }
        }

        if (!foundTable) {
            System.out.println("TABLE DOES NOT EXIST");
        }
    }

    public void insertInto(String table, String[] columns, String[] values) {
        int tableIndex = getTableIndex(table);

        if (tableIndex != -1) {

            Table t = database.get(tableIndex);

            for (String v : values) {
                String[] tokens = v.split("\\s?,\\s?");
                int valueIndex = 0;
                for (int colIndex = 0; colIndex < columns.length; colIndex++) {
                    Column<?> column = t.getColumns().get(colIndex);
                    System.out.printf("index: %d, name: %s\n", colIndex, column.getName());
                    if (column.getType().equals("String")) {
                        //System.out.println(tokens[valueIndex]);
                        //System.out.println("index: " + valueIndex);
                        String value = tokens[valueIndex];
                        column.addValue(value);
                        valueIndex++;
                    } else if (column.getType().equals("Int")) {
                        //System.out.println(tokens[valueIndex]);
                        //System.out.println("index: " + valueIndex);
                        int value = Integer.parseInt(tokens[valueIndex]);
                        column.addValue(value);
                        valueIndex++;
                    }
                }
            }
        }
    }

    public void selectFrom(String table, String[] values) {

    }

    public void selectFromWhere(String table, String[] columns, String[]... values) {

    }

    public void removeFromWhere(String table, String[] columns, String[]... values) {

    }

    private int getTableIndex(String table) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getName().equals(table)) return i;
        } return -1;
    }
}




