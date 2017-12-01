package UI;

import BasicClass.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BooksSalesFrame extends Application{
    private SaleList saleList;
    private BookList bookList;
    private int INIT_ORD = 1;
    private int ordNum = INIT_ORD;
    private ObservableList<SaleRecord> records;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        saleList = new SaleList();
        bookList = new BookList();

        Button stockBtn = new Button("INVENTORY");
        Button figureBtn = new Button("FIGURE");
        Button saveBtn = new Button("SAVE & EXIT");
        stockBtn.setFont(new Font(15));
        figureBtn.setFont(new Font(15));
        saveBtn.setFont(new Font(15));

        FlowPane menuPane = new FlowPane();
        menuPane.setPadding(new Insets(20,0,0,0));
        menuPane.setAlignment(Pos.TOP_CENTER);
        ObservableList listLeft = menuPane.getChildren();
        listLeft.addAll(stockBtn, figureBtn, saveBtn);
        menuPane.setOrientation(Orientation.HORIZONTAL);
        menuPane.setMargin(stockBtn, new Insets(5,10,5,5));
        menuPane.setMargin(figureBtn, new Insets(5,10,5,5));
        menuPane.setMargin(saveBtn, new Insets(5,10,5,5));

        TableView table = new TableView();
        table.setEditable(false);
        records = FXCollections.observableArrayList();
        table.setItems(records);
        TableColumn saleOrdColumn = new TableColumn("Sale Order");
//        TableColumn saleIdColumn = new TableColumn("Sale ID");
        TableColumn bookNameColumn = new TableColumn("Book Name");
        TableColumn sellerNameColumn = new TableColumn("Seller");
        TableColumn bookNumColumn = new TableColumn("Number");
        TableColumn bookPriceColumn = new TableColumn("Price");
        TableColumn totalPriceColumn = new TableColumn("Total");
        saleOrdColumn.setCellValueFactory(new PropertyValueFactory<>("ord"));
//        saleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellerNameColumn.setCellValueFactory(new PropertyValueFactory<>("seller"));
        bookNumColumn.setCellValueFactory(new PropertyValueFactory<>("num"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        table.getColumns().addAll(saleOrdColumn, bookNameColumn, sellerNameColumn,
                bookNumColumn, bookPriceColumn, totalPriceColumn);
        Label label = new Label("Sale Record:");
        label.setPadding(new Insets(0,0,0,20));
        label.setFont(new Font("Arial", 20));
        VBox chartBox = new VBox();
        chartBox.setPadding(new Insets(30,0,0,0));
        chartBox.setMargin(table, new Insets(0,20,20,20));
        chartBox.getChildren().addAll(label, table);

        TextField num = new TextField();
        TextField id = new TextField();
        TextField seller = new TextField();
        Text num_text = new Text("NUMBER: ");
        Text id_text = new Text("ID: ");
        Text seller_text = new Text("SELLER: ");
        Button submitBtn = new Button("SUBMIT");
        Button clearBtn = new Button("CLEAR");

        GridPane salePane = new GridPane();
        salePane.setHgap(10);
        salePane.setVgap(10);
        salePane.setPadding(new Insets(10,10,10,10));
        salePane.setMargin(submitBtn, new Insets(0,0,0,30));
        salePane.setMargin(clearBtn, new Insets(0,0,0,30));
        salePane.setAlignment(Pos.BOTTOM_CENTER);
        salePane.add(num_text,0,0);
        salePane.add(num, 1,0);
        salePane.add(id_text, 0,1);
        salePane.add(id, 1,1);
        salePane.add(seller_text, 0,2);
        salePane.add(seller, 1,2);
        salePane.add(submitBtn, 3,0);
        salePane.add(clearBtn, 3,2);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuPane, chartBox, salePane);
        vBox.setVgrow(salePane, Priority.ALWAYS);
        Scene scene = new Scene(vBox,800,550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FlowPane");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        submitBtn.setOnAction(event -> {
            // set conditions:!(id.getText().isEmpty()) &&
            //                    !(num.getText().isEmpty()) &&
            //                    !(id.getText().isEmpty())
            if((id.getText().length() == Book.BOOD_ID_LENGTH) &&
                    !(num.getText().isEmpty()) &&
                    (seller.getText().length() <= Book.SELLER_LENGTH)){
                for (Book book : bookList){
                    if (book.getBookId().equals(id.getText())){
                        int saleNum = Integer.parseInt(num.getText());
                        String bookId = id.getText();
                        String sellerName = seller.getText();
                        if(bookList.updateBook(book.getBookId(), -1, saleNum)){
                            Sale sale = new Sale(bookId, sellerName, saleNum);
                            // submit to db:
                            saleList.addSale(bookId, sellerName, saleNum);
                            // submit to table:
                            records.add(new SaleRecord(book, sale, ordNum++));
                        }
                    }else{
                        System.out.println("WRONG BOOK ID");
                    }
                }
            }else{
                System.out.println("WRONG NUM OR SELLER");
            }
            // seller and num(mostly 1) stays:
            //id.clear();
        });

        clearBtn.setOnAction(event -> {
            num.clear();
            id.clear();
            seller.clear();
        });
    }
}
