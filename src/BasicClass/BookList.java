package BasicClass;

import DB.DBInfo;
import UI.BooksSalesFrame;

import java.sql.*;
import java.util.ArrayList;

public class BookList extends ArrayList<Book>{
    private Connection connection;
    private Statement statement;

    private static final String JDBC_DRIVER = DBInfo.JDBC_DRIVER;
    private static final String DB_URL = DBInfo.DB_URL;
    private static final String USER = DBInfo.USER;
    private static final String PASS = DBInfo.PASS;

    public BookList(){
        connection = null;
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM BOOKS";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String bookId = resultSet.getString("ID");
                String bookName = resultSet.getString("NAME");
                float price = resultSet.getFloat("PRICE");
                int bookNum = resultSet.getInt("NUM");
                int sale = resultSet.getInt("SALE");
                Book book = new Book(bookId, bookName, price, bookNum, sale);
                this.add(book);
//                System.out.println(bookId + bookName + price +"\n");
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

    public boolean addBook(String bookId, String bookName, float bookPrice, int newNum){
        // change the db:
        connection = null;
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String queryBookSQL = "SELECT ID FROM BOOKS;";
            ResultSet resultSet = statement.executeQuery(queryBookSQL);
            while(resultSet.next()){
                if(resultSet.getString("ID").equals(bookId)){
                    String addExistedBookSQL = "UPDATE BOOKS SET NUM = NUM + " + newNum + ";";
                    statement.executeUpdate(addExistedBookSQL);
                    return false;
                }
            }

            String sql = "INSERT INTO BOOKS VALUES( '" + bookId + "'," +
                    "'" + bookName + "'," +
                    "'" + bookPrice + "'," +
                    newNum +
                    ", 0);";
            statement.execute(sql);
            return true;
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
        for(Book book : this)
            if(book.getBookId().equals(bookId)){
                book.setBookNum(book.getBookNum() + newNum);
                return false;
            }
        Book book = new Book(bookId, bookName, bookPrice, newNum, 0);
        this.add(book);
        return true;
    }

    public boolean updateBook(String bookId, float bookPrice, int saleNum){
        // change the db:
        connection = null;
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String getNum = "SELECT NUM FROM BOOKS WHERE ID = '" + bookId + "';";
            ResultSet resultSet = statement.executeQuery(getNum);
            resultSet.next();
            int nowNum = resultSet.getInt("NUM");
            String sql = "UPDATE BOOKS SET NUM = NUM - " + saleNum + ";";
            String sql2 = "UPDATE BOOKS SET SALE = " +
                    "SALE + " + saleNum + " " +
                    "WHERE ID = '" + bookId + "';";
            statement.execute(sql2);
            if(nowNum > saleNum){
                BooksSalesFrame.msgLabel.setVisible(false);
                statement.execute(sql);
                statement.execute(sql2);
            }
            else if(nowNum == saleNum){
                String soldOutSQL = "DELETE FROM BOOKS WHERE ID = " + bookId + ";";
                statement.execute(soldOutSQL);
                BooksSalesFrame.msgLabel.setVisible(true);
                BooksSalesFrame.msgLabel.setText("SOLD OUT JUST NOW");
            }
            else {
                BooksSalesFrame.msgLabel.setVisible(true);
                BooksSalesFrame.msgLabel.setText("INVENTORY SHORTAGE");
                return false;
            }
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
        for(Book book : this){
            if(book.getBookId().equals(bookId)){
                if(bookPrice != -1) book.setBookPrice(bookPrice); // change price when the bookPrice is not -1
                book.setBookNum(book.getBookNum() - saleNum);
                book.setSale(book.getSale() + saleNum);
            }
            return true;
        }
        return false;
    }
}
