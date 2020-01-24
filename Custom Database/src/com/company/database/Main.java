package com.company.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Database database = new Database();

        final Pattern CREATE_QUERY = Pattern.compile("^CREATE TABLE (?<tableName>\\S+) \\((?<columns>\\S+)\\)$");
        final Pattern DROP_QUERY = Pattern.compile("^DROP TABLE (?<tableName>\\w+)$");
        final String LIST_TABLES = "LIST TABLES";
        final Pattern TABLE_INFO = Pattern.compile("^TABLE INFO (?<tableName>\\w+)$");
        final Pattern INSERT_QUERY = Pattern.compile("INSERT INTO (?<tableName>\\S+) VALUES (?<values>[\\S, ]+\\))");
        final Pattern SELECT_QUERY = Pattern.compile("^SELECT (?<rows>\\S+) FROM (?<tableName>\\S+)(( WHERE (?<rowIs>\\S+) (?<condition>[<>!=]{0,2}) (?<thanValue>[\\w\\d]+))?(( ?(?<andOr>AND?|OR)? (?<sRowIs>\\S+) (?<sCondition>[<>!=]{0,2}) (?<sThanValue>[\\w\\d]+)))?( (?<distinct>DISTINCT))?)?$");
        final Pattern REMOVE_QUERY = Pattern.compile("^REMOVE FROM (?<tableName>\\S+)( WHERE (?<rowIs>[\\w\\d]+) (?<operator>[<>!=]+) (?<thanValue>\\S+))?( (?<andOr>AND?|OR?) (?<sRowIs>\\S+) (?<sCondition>[<>!=]{0,2}) (?<sThanValue>[\\w\\d]+))?$");

        /*
         * Queries are read from console input - several types:
         * --CREATE TABLE - creates a table in the database
         *     example: CREATE TABLE People (ID:Int,Name:String)
         *              CREATE TABLE People (ID:Int, Name:String) is not a valid query -> space between columns
         *
         * --DROP TABLE - removes a table from the database
         *     example: DROP TABLE People
         *
         * --LIST TABLES - shows the names of all tables present in the database
         *
         * --TABLE INFO - gives information about a table and its columns (name and type)
         *     example: TABLE INFO People
         *     expected output: People (ID:int, Name:String)
         *             0 rows total
         *
         * --INSERT INTO - inserts rows into the given table
         *     example: INSERT INTO People VALUES (1, "Ivan")
         *              INSERT INTO People VALUES (2, "Peter"), (3, "Georgi")
         *              INSERT INTO People VALUES ("Tomas", 4) is not a valid query -> column types do not match
         *              INSERT INTO People VALUES (6) is not a valid query -> column count is different from value count
         *              INSERT INTO People (ID,Name) VALUES (1, Ivan) is not a valid query -> string type values must be
         *                                                            inside quotes: "Ivan" f.e.
         *              INSERT INTO People (ID, Name) VALUES (4, "Toni") is not a valid query -> space between columns
         *                                                               similar to CREATE TABLE query
         *
         *              expected output from INSERT INTO People VALUES (1, "Ivan"):
         *                      1 row inserted
         *              expected output from INSERT INTO People VALUES (2, "Peter") , (3, "Georgi")
         *                      2 rows inserted
         *              expected output from INSERT INTO People VALUES ("Tomas", 4)
         *                      Incompatible value type to Int column. -> cancels INSERT query
         *
         * --REMOVE FROM - removes rows from the given table
         *      example: REMOVE FROM People     -> removes all rows from TABLE People, but DOES NOT delete the table itself
         *               REMOVE FROM People WHERE ID = 5 AND Name = "Ivan"
         *               REMOVE FROM People WHERE ID = 5 OR Name = "Ivan"
         *               REMOVE * FROM People is not a valid query
         *               REMOVE FROM People DISTINCT is not a valid query
         *               REMOVE FROM People WHERE ID = 5 DISTINCT is not a valid query
         *
         *      expected output from REMOVE FROM People
         *          (considering there are 15 rows) 15 rows removed.
         *
         *
         * --Others to be implemented
         */

        DBMS_interface();
        String query = reader.readLine();

        while (!query.equals("QUIT")) {

            if (query.contains("CREATE")) {
                Matcher matcher = CREATE_QUERY.matcher(query);
                if (matcher.find()) {
                    String table = matcher.group("tableName");
                    String columns = matcher.group("columns");
                    database.createTable(table, columns);
                } else {
                    System.out.println("INVALID QUERY");
                }
            }

            else if (query.contains("DROP")) {
                Matcher matcher = DROP_QUERY.matcher(query);
                if (matcher.find()) {
                    String table = matcher.group("tableName");
                    database.dropTable(table);
                } else {
                    System.out.println("INVALID QUERY");
                }
            }

            else if (query.equals(LIST_TABLES)) {
                database.listTables();
            }

            else if (query.contains("TABLE INFO")) {
                Matcher matcher = TABLE_INFO.matcher(query);
                if (matcher.find()) {
                    String table = matcher.group("tableName");
                    database.showTableInfo(table);
                }
            }

            else if (query.contains("INSERT")) {
                Matcher matcher = INSERT_QUERY.matcher(query);
                if (matcher.find()) {
                    String table = matcher.group("tableName");
                    String[] values = matcher.group("values")
                            .substring(1, matcher.group("values").length() - 1)
                            .split("\\)\\s?,\\s?\\(");
                    //for (String v : values) System.out.println(v);
                    //System.out.println(values.length);
                    database.insertInto(table, values);
                }
            }

            else if (query.contains("SELECT")) {
                Matcher matcher = SELECT_QUERY.matcher(query);

                if (matcher.find()) {
                    String[] selectQuery = query.split("\\s+");
                    int queryLength = selectQuery.length;

                    switch (queryLength) {
                        case 4: {
                            String rows = matcher.group("rows");
                            String table = matcher.group("tableName");
                            database.selectFrom(rows, table);
                            //System.out.printf("%s\n%s", rows, table);
                        }
                    }
                }
            }

            else if (query.contains("REMOVE")) {
                Matcher matcher = REMOVE_QUERY.matcher(query);

                if (matcher.find()) {
                    String[] removeQuery = query.split("\\s+");
                    int queryLength = removeQuery.length;

                    switch (queryLength) {
                        case 3: {
                            String table = matcher.group("tableName");
                            database.removeAllFrom(table);
                        }

                        case 7: {
                            String table = matcher.group("tableName");
                            String whereRowIs = matcher.group("rowIs");
                            String operator = matcher.group("operator");
                            String thanValue = matcher.group("thanValue");

                            System.out.println(table);
                            System.out.println(whereRowIs);
                            System.out.println(operator);
                            System.out.println(thanValue);

                            database.removeFromWhere(table, whereRowIs, operator, thanValue);
                        }
                    }
                }

            }

            else {
                System.out.println("INVALID QUERY");
            }

            DBMS_interface();
            query = reader.readLine();
        }
    }

    public static void DBMS_interface() {
        System.out.print("SSql> ");
    }
}
