package UI;

import BasicClass.BookInventory;
import Text.OutputBooks;
import Text.OutputSales;
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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    @Override
    public void start(Stage primaryStage){
        Image image = new Image("cube.jpg");
        BackgroundSize size = new BackgroundSize
                (BackgroundSize.AUTO,BackgroundSize.AUTO,false,false, true,true);
        BackgroundImage backgroundImage = new BackgroundImage
                (image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background bkg = new Background(backgroundImage);
        TableView table = new TableView();
        table.setEditable(false);
        table.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
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
        okBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        okBtn.setPadding(new Insets(5,10,5,10));
        okBtn.setOnAction(event -> {
            new BooksSalesFrame().start(new Stage());
            primaryStage.close();
        });

        Button addBtn = new Button("ADD BOOK");
        addBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        addBtn.setPadding(new Insets(5,10,5,10));
        addBtn.setOnAction(event -> {
            new AddBookFrame().start(new Stage());
            primaryStage.close();
        });

        Button bookBtn = new Button("Print Books");
        bookBtn.setOnAction(event -> {
            try{
                new OutputBooks();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Button saleBtn = new Button("Print Sales");
        saleBtn.setOnAction(event -> {
            try{
                new OutputSales();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        HBox hBoxTop = new HBox();
        hBoxTop.getChildren().addAll(bookBtn, saleBtn);
        hBoxTop.setAlignment(Pos.CENTER);
        hBoxTop.setMargin(bookBtn, new Insets(10,10,10,30));
        hBoxTop.setMargin(saleBtn, new Insets(10,10,10,30));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(addBtn, okBtn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMargin(okBtn, new Insets(10,10,10,30));
        hBox.setMargin(addBtn, new Insets(10,30,10,10));


        VBox vBox = new VBox();
        vBox.setBackground(bkg);
        vBox.getChildren().addAll(hBoxTop, table, hBox);
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
