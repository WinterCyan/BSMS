package UI;

import BasicClass.BookList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

 public class AddBookFrame extends Application{
    private BookList bookList;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Text addId = new Text("Book ID: ");
        TextField addIdField = new TextField();
        Text addName = new Text("Book Name: ");
        TextField addNameField = new TextField();
        Text addPrice = new Text("Book Price: ");
        TextField addPriceField = new TextField();
        Text addNum = new Text("Number: ");
        TextField addNumField = new TextField();

        Button addBtn = new Button("ADD");
        addBtn.setFont(new Font(15));
        addBtn.setOnAction(event -> {
            bookList = new BookList();

            if(!addIdField.getText().isEmpty() &&
                    !addNameField.getText().isEmpty() &&
                    !addPriceField.getText().isEmpty() &&
                    !addNameField.getText().isEmpty()){
                String id = addIdField.getText();
                String name = addNameField.getText();
                float price = Float.parseFloat(addPriceField.getText());
                int num = Integer.parseInt(addNumField.getText());

                if(bookList.addBook(id, name, price, num)) System.out.println("New books added.");
                else System.out.println("Updated existed books.");
            }else System.out.println("NO BLANK!");
        });

        Button cancelBtn = new Button("CANCEL");
        cancelBtn.setFont(new Font(15));
        cancelBtn.setOnAction(event -> {
            addIdField.clear();
            addNameField.clear();
            addPriceField.clear();
            addNumField.clear();

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

        VBox vBox = new VBox();
        vBox.getChildren().addAll(gridPane, hBox);
        vBox.setMargin(gridPane, new Insets(20,0,0,0));
        vBox.setMargin(hBox, new Insets(20,0,0,0));
        Scene scene = new Scene(vBox,400,250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Books");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
