import BasicClass.Sale;
import BasicClass.SaleList;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.sql.*;

public class Test {
    private static SaleList saleList;
    private static FileOutputStream fos;
    private static BufferedOutputStream bos;
    private static DataOutputStream dos;

    private static Connection connection;
    private static Statement statement;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/BOOKSTORE?useSSL=true";
    private static final String USER = "root";
    private static final String PASS = "winter";
    public static void main(String[] args) throws Exception{
        fos = new FileOutputStream("Sales.txt");
        bos = new BufferedOutputStream(fos);
        dos = new DataOutputStream(bos);

        dos.writeBytes("Sale ID     Book ID     Book Name       Seller      Number\n");
//        for(Sale sale : saleList){
//            String saleId = sale.getSaleId();
//            String bookId = sale.getBookId();
//            String seller = sale.getSeller();
//            int saleNum = sale.getSaleNum();
//            dos.writeBytes(saleId + "   " + bookId + "     " + seller  + "     " + String.valueOf(saleNum) + "\n");
//            System.out.println("Outputting...");
//        }
//        dos.close();
//        System.out.println("Output succeed.");

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "SELECT SALES.SALE_ID, SALES.BOOK_ID, BOOKS.NAME AS BOOK_NAME, SALES.SELLER, SALES.SALE_NUM, BOOKS.PRICE, " +
                    "FORMAT(SALES.SALE_NUM*BOOKS.PRICE, 2) AS TOTAL_PRICE FROM SALES " +
                    "JOIN BOOKS ON SALES.BOOK_ID = BOOKS.ID;";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String saleId = resultSet.getString("SALES.SALE_ID");
                String bookId = resultSet.getString("SALES.BOOK_ID");
                String bookName = resultSet.getString("BOOK_NAME");
                String seller = resultSet.getString("SALES.SELLER");
                int saleNum = resultSet.getInt("SALES.SALE_NUM");
                String bookPrice = resultSet.getString("BOOKS.PRICE");
                String  totalPrice = resultSet.getString("TOTAL_PRICE");
                dos.writeBytes(saleId + "   " + bookId + "     " + bookName + "     " + seller  + "     "
                        + String.valueOf(saleNum) + "      " + String.valueOf(bookPrice)
                        + "    " + String.valueOf(totalPrice) + "\n");
                System.out.println("Done.");
            }
            resultSet.close();
            dos.close();
            bos.close();
            fos.close();
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