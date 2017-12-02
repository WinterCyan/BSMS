package DB;

import java.sql.*;

public class TableInit {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BOOKSTORE?useSSL=true";
    static final String USER = "root";
    static final String PASS = "winter";

    public TableInit(){
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String createBooksSQL = "CREATE TABLE IF NOT EXISTS BOOKS(" +
                    "ID CHAR(6) NOT NULL PRIMARY KEY," +
                    "NAME CHAR(50) NOT NULL," +
                    "PRICE FLOAT NOT NULL," +
                    "NUM SMALLINT NOT NULL DEFAULT '100');";
            String createSalesSQL = "CREATE TABLE IF NOT EXISTS SALES(" +
                    "SALE_ID INT NOT NULL PRIMARY KEY," +
                    "BOOK_ID CHAR(6) NOT NULL," +
                    "SELLER CHAR(10) NOT NULL," +
                    "SALE_NUM SMALLINT NOT NULL DEFAULT '1');";
            String setINCSQL = "ALTER TABLE SALES MODIFY SALE_ID INTEGER AUTO_INCREMENT;";
            statement.executeUpdate(createBooksSQL);
            statement.executeUpdate(createSalesSQL);
            statement.executeUpdate(setINCSQL);
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
