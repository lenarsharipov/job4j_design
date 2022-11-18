package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        var url = properties.getProperty("hibernate.connection.url");
        var login = properties.getProperty("hibernate.connection.username");
        var password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws Exception {
        var config = new Properties();
        try (var in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (var tableEditor = new TableEditor(config)) {
                var tableName = "test_table_1";
                var columnName = "id";
                var type = "serial primary key";
                tableEditor.dropTable(tableName);
                tableEditor.createTable(tableName);
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.addColumn(tableName, columnName, type);
                System.out.println(tableEditor.getTableScheme(tableName));
                columnName = "first_name";
                type = "varchar(10)";
                tableEditor.addColumn(tableName, columnName, type);
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.dropColumn(tableName, columnName);
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.addColumn(tableName, columnName, type);
                var newColumnName = "full_name";
                tableEditor.renameColumn(tableName, columnName, newColumnName);
                System.out.println(tableEditor.getTableScheme(tableName));
            }
        }
    }

    private void executeUpdate(String sql) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        var sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        executeUpdate(sql);
    }

    public void dropTable(String tableName) throws Exception {
        var sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        executeUpdate(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        var sql = String.format(
                "alter table if exists %s add column if not exists %s %s;",
                tableName,
                columnName,
                type
        );
        executeUpdate(sql);

    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        var sql = String.format(
                "alter table if exists %s drop column if exists %s",
                tableName,
                columnName
        );
        executeUpdate(sql);
    }

    public void renameColumn(String tableName,
                             String columnName,
                             String newColumnName) throws Exception {
        var sql = String.format(
                "alter table if exists %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName
        );
        executeUpdate(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}