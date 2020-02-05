package ru.epam.javacore.lesson_23_relational_db.lesson;

import java.sql.*;

public class A_4_Transactions {
    public static void main(String[] args) throws Exception {
        createTable();
       // demoBeforeTransaction();
        demoTransaction();
       // demoTransactionRollback();
    }

    private static void demoTransaction() throws Exception {
        Connection connection = null;
        try {
            connection = A_2_H2_SimpleConnection
                    .getInstance().getConnection();

            connection.setAutoCommit(false);
            a_3_saveUser(connection, new User(33, "WWW AAAA " + System.currentTimeMillis()));
            a_3_saveUser(connection, new User(33, "SSS 2 " + System.currentTimeMillis()));

            //connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                System.out.println("Revert ");
                connection.rollback();
            }
        } finally {
            a_2_getUsers();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private static void demoTransactionRollback() throws Exception {
        Connection connection = null;
        try {
            connection = A_2_H2_SimpleConnection
                    .getInstance().getConnection();
            connection.setAutoCommit(false);

            a_3_saveUser(connection, new User(33, "Semen AAAA " + System.currentTimeMillis()));
            if (true) {
                System.out.println("OOPS , something went wrong!");
                throw new RuntimeException("AAA");
            }
            a_3_saveUser(connection, new User(33, "Semen 2 " + System.currentTimeMillis()));
            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                System.out.println("Revert ");
                connection.rollback();
            }
        } finally {
            a_2_getUsers();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private static void demoBeforeTransaction() throws Exception {
        Connection connection = null;
        try {
            connection = A_2_H2_SimpleConnection
                    .getInstance().getConnection();
            a_3_saveUser(connection, new User(33, "Petr AAAA " + System.currentTimeMillis()));
            if (true) {
                throw new RuntimeException("AAA");
            }
            a_3_saveUser(connection, new User(33, "Petr 2 " + System.currentTimeMillis()));

        } finally {
            a_2_getUsers();
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static void createTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS USERS (\n" +
                "   ID           BIGINT      ,\n" +
                "   NAME         VARCHAR(50)\n" +
                ");";

        try (Connection connection = A_2_H2_SimpleConnection
                .getInstance().getConnection();
             Statement statement = connection.createStatement();) {


            statement.executeUpdate(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a_2_getUsers() {
        try (Connection connection = A_2_H2_SimpleConnection
                .getInstance().getConnection();
             Statement statement = connection.createStatement();) {

            String s = "name=Ivan".split("=")[1];

            String sql = "SELECT * FROM USERS";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");

                User user = new User(id, name);
                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a_3_saveUser(Connection con, User user) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO USERS (ID, NAME) VALUES (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setLong(1, user.id);
            ps.setString(2, user.name);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class User {
        private long id;
        private String name;

        public User(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
