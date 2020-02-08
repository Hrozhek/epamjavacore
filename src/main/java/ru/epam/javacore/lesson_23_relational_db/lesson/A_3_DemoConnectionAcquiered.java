package ru.epam.javacore.lesson_23_relational_db.lesson;

import java.sql.*;

public class A_3_DemoConnectionAcquiered {

    public static void main(String[] args) {
        createTable();
      //  a_1_saveUser(new User(1, "Ivan"));

        a_3_saveUser(new User(33, "Petr "  + System.currentTimeMillis()));
        a_2_getUsers();
        //  String s = "name=ivan".split("=")[1];
        // System.out.println();
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

    private static void a_1_saveUser(User user) {
        try (Connection connection = A_2_H2_SimpleConnection
                .getInstance().getConnection();
             Statement statement = connection.createStatement();) {

            String sql = "INSERT INTO USERS (ID, NAME) VALUES (" +
                    user.id + ",'" + user.name + "')";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a_1_getUsers() {
        try (Connection connection = A_2_H2_SimpleConnection
                .getInstance().getConnection();
             Statement statement = connection.createStatement();) {

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

    private static void a_3_saveUser(User user) {
        PreparedStatement ps = null;
        try (Connection connection = A_2_H2_SimpleConnection
                .getInstance().getConnection();) {

            String sql = "INSERT INTO USERS (ID, NAME) VALUES (?, ?)";
            ps = connection.prepareStatement(sql);
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
