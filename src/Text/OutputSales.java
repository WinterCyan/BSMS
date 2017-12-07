package Text;

import BasicClass.SaleList;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.sql.*;

public class OutputSales {
//    private Connection connection;
//    private Statement statement;
    private static Connection connection;
    private static Statement statement;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/BOOKSTORE?useSSL=true";
    private static final String USER = "root";
    private static final String PASS = "winter";
    private static SaleList saleList;
    private static FileOutputStream fos;
    private static BufferedOutputStream bos;
    private static DataOutputStream dos;

//    public OutputSales() throws Exception{
    public static void main(String[] args) throws Exception{
        saleList = new SaleList();


        fos = new FileOutputStream("Sales.txt");
        bos = new BufferedOutputStream(fos);
        dos = new DataOutputStream(bos);

        dos.writeBytes("Sale ID     Book ID     Seller      Number\n");
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
            String sql = "SELECT SALE_ID, BOOK_ID, BOOKS.NAME, SELLER, SALE_NUM, BOOKS.PRICE, " +
                    "SALE_NUM*BOOK.PRICE AS TOTAL_PRICE FROM SALES" +
                    "JOIN BOOKS ON SALES.BOOK_ID = BOOKS.ID";
//            String sql = "SELECT SALE_ID, BOOK_ID, SELLER, SALE_NUM FROM SALES";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String saleId = resultSet.getString("SALE_ID");
                String bookId = resultSet.getString("BOOK_ID");
                String seller = resultSet.getString("SELLER");
                int saleNum = resultSet.getInt("SALE_NUM");
                float bookPrice = resultSet.getFloat("BOOKS.PRICE");
                float totalPrice = resultSet.getFloat("TOTAL_PRICE");
//                Sale sale = new Sale(bookId, seller, saleNum);
//                this.add(sale);
                dos.writeBytes(saleId + "   " + bookId + "     " + seller  + "     "
                        + String.valueOf(saleNum) + "      " + String.valueOf(bookPrice)
                        + "    " + String.valueOf(totalPrice) + "\n");
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
}
