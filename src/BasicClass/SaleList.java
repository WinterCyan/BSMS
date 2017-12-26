package BasicClass;

import DB.DBInfo;

import java.sql.*;
import java.util.ArrayList;

public class SaleList extends ArrayList<Sale>{
    private Connection connection;
    private Statement statement;

    private static final String JDBC_DRIVER = DBInfo.JDBC_DRIVER;
    private static final String DB_URL = DBInfo.DB_URL;
    private static final String USER = DBInfo.USER;
    private static final String PASS = DBInfo.PASS;

    public SaleList(){
        connection = null;
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
//            String sql = "SELECT SALE_ID, BOOK_ID, BOOKS.NAME, SELLER, SALE_NUM, BOOKS.PRICE, " +
//                    "SALE_NUM*BOOK.PRICE AS TOTAL_PRICE FROM SALES" +
//                    "JOIN BOOKS ON SALES.BOOK_ID = BOOKS.ID";
            String sql = "SELECT SALE_ID, BOOK_ID, SELLER, SALE_NUM FROM SALES";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
//                String saleId = resultSet.getString("SALE_ID");
                String bookId = resultSet.getString("BOOK_ID");
                String seller = resultSet.getString("SELLER");
                int saleNum = resultSet.getInt("SALE_NUM");
//                float bookPrice = resultSet.getFloat("BOOKS.PRICE");
//                float totalPrice = resultSet.getFloat("TOTAL_PRICE");
                Sale sale = new Sale(bookId, seller, saleNum);
                this.add(sale);
//                System.out.println(saleId + bookId + seller + saleNum + "\n");
            }
            resultSet.close();
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

    public void addSale(String bookId, String seller, int saleNum){
        // change the db:

        connection = null;
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "INSERT INTO SALES VALUES(NULL," +
                    "'" + bookId + "'," +
                    "'" + seller + "'," +
                    saleNum + ");";
            statement.execute(sql);
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

        // change the list:
        Sale sale = new Sale(bookId, seller, saleNum);
        this.add(sale);
    }
}