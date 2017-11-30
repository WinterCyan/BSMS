package DB;

import java.sql.*;

public class TableInit {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BOOKSTORE?useSSL=true";
    static final String USER = "root";
    static final String PASS = "winter";

    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "CREATE TABLE BOOKS(" +
                    "ID CHAR(6) NOT NULL PRIMARY KEY," +
                    "NAME CHAR(50) NOT NULL," +
                    "PRICE FLOAT NOT NULL," +
                    "NUM INT NOT NULL DEFAULT '100');" +
                    "CREATE TABLE SALES(" +
                    "SALE_ID CHAR(6) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "BOOK_ID CHAR(6) NOT NULL," +
                    "SELLER CHAR(10) NOT NULL," +
                    "SALE_NUM INT NOT NULL DEFAULT '1');";
            statement.executeUpdate(sql);
            System.out.println("create tables successfully.");
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
        System.out.println("Bye!");
    }
}
