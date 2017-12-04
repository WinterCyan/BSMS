package UI;

import BasicClass.Book;
import BasicClass.BookList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddBookFrame extends Application{
    public static Label msgLabel = new Label();
    private BookList bookList;

    @Override
    public void start(Stage primaryStage){
        Image image = new Image("cube.jpg");
        BackgroundSize size = new BackgroundSize
                (BackgroundSize.AUTO,BackgroundSize.AUTO,false,false, true,true);
        BackgroundImage backgroundImage = new BackgroundImage
                (image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background bkg = new Background(backgroundImage);

        Text addId = new Text("Book ID: ");
        addId.setFill(Color.WHITE);
        addId.setFont(Font.font(null, FontWeight.BOLD,15));
        TextField addIdField = new TextField();
        Text addName = new Text("Book Name: ");
        addName.setFill(Color.WHITE);
        addName.setFont(Font.font(null, FontWeight.BOLD,15));
        TextField addNameField = new TextField();
        Text addPrice = new Text("Book Price: ");
        addPrice.setFill(Color.WHITE);
        addPrice.setFont(Font.font(null, FontWeight.BOLD,15));
        TextField addPriceField = new TextField();
        Text addNum = new Text("Number: ");
        addNum.setFill(Color.WHITE);
        addNum.setFont(Font.font(null, FontWeight.BOLD,15));
        TextField addNumField = new TextField();

        Button addBtn = new Button("ADD");
        addBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        addBtn.setOnAction(event -> {
            bookList = new BookList();

            if(addIdField.getText().length() == Book.BOOD_ID_LENGTH &&
                    !addNameField.getText().isEmpty() &&
                    convertToFloat(addPriceField.getText())&&
                    convertToInteger(addNumField.getText())){
                String id = addIdField.getText();
                String name = addNameField.getText();
                float price = Float.parseFloat(addPriceField.getText());
                int num = Integer.parseInt(addNumField.getText());

                if(bookList.addBook(id, name, price, num)){
                    msgLabel.setVisible(true);
                    msgLabel.setText("New books added.");
                }
                else{
                    msgLabel.setVisible(true);
                    msgLabel.setText("Updated existed books.");
                }
            }else{
                msgLabel.setVisible(true);
                msgLabel.setText("NO BLANK!");
            }

            addIdField.clear();
            addNameField.clear();
            addPriceField.clear();
            addNumField.clear();
        });

        Button cancelBtn = new Button("EXIT");
        cancelBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        cancelBtn.setOnAction(event -> {
            addIdField.clear();
            addNameField.clear();
            addPriceField.clear();
            addNumField.clear();
            msgLabel.setText(null);
            msgLabel.setVisible(false);

            new BookFrame().start(new Stage());
            primaryStage.close();
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.add(addId, 0,0);
        gridPane.add(addIdField, 1,0);
        gridPane.add(addName, 0,1);
        gridPane.add(addNameField, 1,1);
        gridPane.add(addPrice, 0,2);
        gridPane.add(addPriceField, 1,2);
        gridPane.add(addNum, 0,3);
        gridPane.add(addNumField, 1,3);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.getChildren().addAll(cancelBtn, addBtn);
        hBox.setMargin(cancelBtn, new Insets(10,20,15,0));
        hBox.setMargin(addBtn, new Insets(10,0,15,20));

        msgLabel.setPadding(new Insets(0,0,0,20));
        msgLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        msgLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        msgLabel.setTextFill(Color.SILVER);
        msgLabel.setVisible(false);

        HBox labelBox = new HBox();
        labelBox.getChildren().add(msgLabel);
        labelBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setBackground(bkg);
        vBox.getChildren().addAll(gridPane, hBox, labelBox);
        vBox.setMargin(gridPane, new Insets(20,0,0,0));
        vBox.setMargin(hBox, new Insets(20,0,0,0));
        Scene scene = new Scene(vBox,400,300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Books");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

     public boolean convertToFloat(String f){
         try {
             Float.parseFloat(f);
             return true;
         }catch (NumberFormatException e){
             msgLabel.setVisible(true);
             msgLabel.setText("WRONG PRICE FORMAT");
             return false;
         }
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
