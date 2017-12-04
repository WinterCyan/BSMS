package UI;

import BasicClass.*;
import DB.DBInit;
import DB.TableInit;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BooksSalesFrame extends Application{
    public static Label msgLabel = new Label();
    private SaleList saleList;
    private BookList bookList;
    private int INIT_ORD = 1;
    private ObservableList<SaleRecord> records;

    @Override
    public void start(Stage primaryStage){
        saleList = new SaleList();
        bookList = new BookList();

        Image image = new Image("cube.jpg");
        BackgroundSize size = new BackgroundSize
                (BackgroundSize.AUTO,BackgroundSize.AUTO,false,false, true,true);
        BackgroundImage backgroundImage = new BackgroundImage
                (image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background bkg = new Background(backgroundImage);

        Button inventoryBtn = new Button("INVENTORY");
        Button figureBtn = new Button("FIGURE");
        Button saveBtn = new Button("EXIT");
        inventoryBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        figureBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        saveBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        inventoryBtn.setOnAction(event -> {
            new BookFrame().start(new Stage());
            msgLabel.setVisible(false);
            primaryStage.close();
        });
//        figureBtn.setOnAction(event -> );

        saveBtn.setOnAction(event -> {
            msgLabel.setVisible(false);
            primaryStage.close();
        });

        FlowPane menuPane = new FlowPane();
        menuPane.setPadding(new Insets(20,0,0,0));
        menuPane.setAlignment(Pos.TOP_CENTER);
        ObservableList listLeft = menuPane.getChildren();
        listLeft.addAll(inventoryBtn, figureBtn, saveBtn);
        menuPane.setOrientation(Orientation.HORIZONTAL);
        menuPane.setMargin(inventoryBtn, new Insets(5,10,5,5));
        menuPane.setMargin(figureBtn, new Insets(5,10,5,5));
        menuPane.setMargin(saveBtn, new Insets(5,10,5,5));

        TableView table = new TableView();
        table.setEditable(false);
        table.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        records = FXCollections.observableArrayList();
        table.setItems(records);
        TableColumn saleOrdColumn = new TableColumn("Sale Order");
        TableColumn bookNameColumn = new TableColumn("Book Name");
        TableColumn sellerNameColumn = new TableColumn("Seller");
        TableColumn bookNumColumn = new TableColumn("Number");
        TableColumn bookPriceColumn = new TableColumn("Price");
        TableColumn totalPriceColumn = new TableColumn("Total");
        saleOrdColumn.setCellValueFactory(new PropertyValueFactory<>("ord"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellerNameColumn.setCellValueFactory(new PropertyValueFactory<>("seller"));
        bookNumColumn.setCellValueFactory(new PropertyValueFactory<>("num"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        table.getColumns().addAll(saleOrdColumn, bookNameColumn, sellerNameColumn,
                bookNumColumn, bookPriceColumn, totalPriceColumn);
        Label label = new Label("Sale Record:");
        label.setPadding(new Insets(0,0,0,20));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setTextFill(Color.WHITE);
        VBox chartBox = new VBox();
        chartBox.setPadding(new Insets(30,0,0,0));
        chartBox.setMargin(table, new Insets(0,20,20,20));
        chartBox.getChildren().addAll(label, table);

        TextField num = new TextField();
        TextField id = new TextField();
        TextField seller = new TextField();
        Text num_text = new Text("NUMBER: ");
        num_text.setFont(Font.font(null, FontWeight.BOLD, 15));
        num_text.setFill(Color.WHITE);
        Text id_text = new Text("ID: ");
        id_text.setFont(Font.font(null, FontWeight.BOLD, 15));
        id_text.setFill(Color.WHITE);
        Text seller_text = new Text("SELLER: ");
        seller_text.setFont(Font.font(null, FontWeight.BOLD, 15));
        seller_text.setFill(Color.WHITE);
        Button submitBtn = new Button("SUBMIT");
        submitBtn.setFont(Font.font(null, FontWeight.BOLD, 13));
        Button clearBtn = new Button("CLEAR");
        clearBtn.setFont(Font.font(null, FontWeight.BOLD, 13));

        GridPane salePane = new GridPane();
        salePane.setHgap(10);
        salePane.setVgap(10);
        salePane.setPadding(new Insets(10,10,10,10));
        salePane.setMargin(submitBtn, new Insets(0,0,0,30));
        salePane.setMargin(clearBtn, new Insets(0,0,0,30));
        salePane.setAlignment(Pos.BOTTOM_CENTER);
        salePane.add(id_text,0,0);
        salePane.add(id, 1,0);
        salePane.add(num_text, 0,1);
        salePane.add(num, 1,1);
        salePane.add(seller_text, 0,2);
        salePane.add(seller, 1,2);
        salePane.add(submitBtn, 3,0);
        salePane.add(clearBtn, 3,2);

        msgLabel.setPadding(new Insets(0,0,0,20));
        msgLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        msgLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        msgLabel.setTextFill(Color.SILVER);
        msgLabel.setVisible(false);

        HBox labelBox = new HBox();
        labelBox.getChildren().add(msgLabel);
        labelBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuPane, chartBox, salePane, labelBox);
        vBox.setBackground(bkg);
        Scene scene = new Scene(vBox,800,550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sale Window");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        submitBtn.setOnAction(event -> {
            if((id.getText().length() == Book.BOOD_ID_LENGTH) &&
                    convertToInteger(num.getText())&&
                    (seller.getText().length() <= Book.SELLER_LENGTH)){
                if(!bookList.isEmpty()){
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
                                records.add(new SaleRecord(book, sale, INIT_ORD++));
                            }
                        }
                    }
                }else{
                    msgLabel.setVisible(true);
                    msgLabel.setText("NOT FOUND");
                }
            }else {
                msgLabel.setVisible(true);
                msgLabel.setText("WRONG INPUT SYNTAX");
            }
            // seller and num(mostly 1) stays:
            id.clear();
        });

        clearBtn.setOnAction(event -> {
            num.clear();
            id.clear();
            seller.clear();
        });
    }

    public boolean convertToInteger(String i){
        try {
            Integer.parseInt(i);
            return true;
        }catch (NumberFormatException e){
            msgLabel.setVisible(true);
            msgLabel.setText("WRONG NUM FORMAT");
            return false;
        }
    }
}
