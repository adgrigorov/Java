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
        } //end else
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


    //PROBLEM
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void insertInto(String table, String[] values) {
        int tableIndex = getTableIndex(table);

        if (tableIndex != -1) {

            Table t = database.get(tableIndex);

            int rowsInserted = 0;

            for (String v : values) {

                String[] tokens = v.split("\\s?,\\s?");

                if (tokens.length != t.getColumns().size()) {
                    System.out.println("INVALID VALUES AT INDEX " + (rowsInserted + 1) +
                            "\nINSERTED VALUES UP TO INDEX " + rowsInserted);
                    return;
                }

                int valueIndex = 0;
                for (int colIndex = 0; colIndex < t.getColumns().size(); colIndex++) {
                    Column column = t.getColumns().get(colIndex);
                    //System.out.printf("index: %d, name: %s\n", colIndex, column.getName());
                    if (column.getType().equals("String")) {
                        //System.out.println(tokens[valueIndex]);
                        //System.out.println("index: " + valueIndex);
                        String value = tokens[valueIndex];
                        if (value.charAt(0) != '"' && value.charAt(value.length() - 1) != '"') {
                            System.out.println("Incompatible value type into String type column.");
                            return;
                        } else {
                            value = value.substring(1, value.length() - 1);
                            column.addValue(value);
                            valueIndex++;
                        }
                    } //end if string

                    else if (column.getType().equals("Int")) {
                        //System.out.println(tokens[valueIndex]);
                        //System.out.println("index: " + valueIndex);
                        if (!isNumber(tokens[valueIndex].charAt(0))) {
                            System.out.println("Incompatible value type into Int type column.");
                            return;
                        }

                        else {
                            try {
                                int value = Integer.parseInt(tokens[valueIndex]);
                                column.addValue(value);
                                valueIndex++;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number.");
                                e.printStackTrace();
                                return;
                            }
                        } //end else try-catch

                    } //end else if int

                    else {
                        System.out.println("INVALID VALUES.");
                        return;
                    }

                } //end for columns
                rowsInserted++;
            } //end for values
            System.out.println(rowsInserted + " rows inserted.");
        } //end if -1

        else {
            System.out.println("TABLE " + table + " DOES NOT EXIST.");
        }
    }

    public void selectFrom(String rows, String table) {
        int tableIndex = getTableIndex(table);

        if (tableIndex != -1) {
            if (rows.equals("*")) {
                for (Column<?> column : database.get(tableIndex).getColumns()) {
                    System.out.printf("| %s |", column.getName());
                    for (Object value : column.getValues()) {
                        System.out.printf("\n| %s |", value);
                    }
                }
            }

            else if (columnExists(rows)) {
                for (Table t : database) {
                    for (Column c : t.getColumns()) {
                        if (c.getName().equals(rows)) {
                            for (Object row : c.getValues()) {
                                System.out.println(row);
                            }
                        }
                    }
                }
            }

            else {
                System.out.println("COLUMN " + rows + " DOES NOT EXISTS IN TABLE " + table + ".");
            }
        }

        else {
            System.out.println("TABLE " + table + " DOES NOT EXIST.");
        }
    }

    public void selectFromWhere(String table, String[] columns, String[]... values) {

    }

    public void removeAllFrom(String table) {
        int tableIndex = getTableIndex(table);
        int rowsRemoved = 0;

        if (tableIndex != -1) {
            for (Column<?> column : database.get(tableIndex).getColumns()) {
                int rowsBeforeRemove = column.getValues().size();
                column.clearValues();
                rowsRemoved += rowsBeforeRemove;
            }
            rowsRemoved /= database.get(tableIndex).getColumns().size();
        }

        else {
            System.out.println("TABLE " + table + " DOES NOT EXIST.");
        }

        System.out.println(rowsRemoved + " rows removed.");
    }

    public void removeFromWhere(String table, String whereRowIs, String operator, String thanValue) {
        int tableIndex = getTableIndex(table);
        int rowsRemoved = 0;

        if (tableIndex != -1) {



        }
    }

    private int getTableIndex(String table) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getName().equals(table)) return i;
        } return -1;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private void draw(int rows, int columns) {
        for (int i = 0; i < columns; i++) {
            System.out.print("|       |");
        }
        System.out.println();
        for (int i = 0; i < columns; i++) {
            System.out.print("---------");
        }

        for (int i = 0; i < rows; i++) {
            System.out.println("|        |");
            System.out.println("---------");
        }
    }

    private boolean columnExists(String column) {
        for (Table table : database) {
            for (Column<?> tableColumn : table.getColumns()) {
                if (tableColumn.getName().equals(column)) {
                    return true;
                }
            }
        }
        return false;
    }
}
