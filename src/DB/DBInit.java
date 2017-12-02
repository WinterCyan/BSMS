package DB;

import java.sql.*;

public class DBInit {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/?useSSL=true";
    static final String USER = "root";
    static final String PASS = "winter";

    public DBInit(){
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql1 = "CREATE DATABASE IF NOT EXISTS BOOKSTORE;";
            String sql2 = "USE BOOKSTORE;";
            String sql3 = "CREATE TABLE IF NOT EXISTS BOOKS(" +
                    "ID CHAR(6) NOT NULL PRIMARY KEY," +
                    "NAME CHAR(50) NOT NULL," +
                    "PRICE FLOAT NOT NULL," +
                    "NUM INT NOT NULL DEFAULT '100');";
            String  sql4 = "CREATE TABLE IF NOT EXISTS SALES(" +
                    "SALE_ID CHAR(6) NOT NULL PRIMARY KEY," +
                    "BOOK_ID CHAR(6) NOT NULL," +
                    "SELLER CHAR(10) NOT NULL," +
                    "SALE_NUM INT NOT NULL DEFAULT '1');";
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
            statement.executeUpdate(sql4);
            System.out.println("create database successfully.");
        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(statement != null) statement.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
            try{
                if(connection != null) connection.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
    }
}
