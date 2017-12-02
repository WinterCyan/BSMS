package UI;

import BasicClass.BookInventory;
import BasicClass.BookList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

public class BookFrame extends Application{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BOOKSTORE?useSSL=true";
    static final String USER = "root";
    static final String PASS = "winter";
    private Connection connection;
    private Statement statement;
    private ObservableList<BookInventory> inventories;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        TableView table = new TableView();
        table.setEditable(false);
        inventories = FXCollections.observableArrayList();
        table.setItems(inventories);
        TableColumn bookIdColumn = new TableColumn("Book ID");
        TableColumn bookNameColumn = new TableColumn("Book Name");
        TableColumn inventoryColumn = new TableColumn("Inventory");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("num"));
        table.getColumns().addAll(bookIdColumn, bookNameColumn, inventoryColumn);

        Button okBtn = new Button("OK");
        okBtn.setFont(new Font(15));
        okBtn.setPadding(new Insets(5,10,5,10));
        okBtn.setOnAction(event -> {
            primaryStage.close();
        });

        Button addBtn = new Button("ADD BOOK");
        addBtn.setFont(new Font(15));
        addBtn.setPadding(new Insets(5,10,5,10));
        addBtn.setOnAction(event -> {

        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(addBtn, okBtn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMargin(okBtn, new Insets(10,10,10,30));
        hBox.setMargin(addBtn, new Insets(10,30,10,10));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);
        vBox.setMargin(table, new Insets(10,15,5,15));

        Scene scene = new Scene(vBox, 800,450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Inventory");
        primaryStage.setResizable(false);
        primaryStage.show();

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String queryBooksSQL = "SELECT ID, NAME, NUM FROM BOOKS;";
            ResultSet resultSet = statement.executeQuery(queryBooksSQL);
            while (resultSet.next()){
                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");
                int num = resultSet.getInt("NUM");
                inventories.add(new BookInventory(id, name, num));
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
    }
}
