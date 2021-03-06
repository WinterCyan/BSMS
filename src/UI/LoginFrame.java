package UI;

import DB.DBInit;
import DB.TableInit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginFrame extends Application{
    public static Label msgLabel = new Label();

    @Override
    public void start(Stage primaryStage){
        Image image = new Image("cube.jpg");
        BackgroundSize size = new BackgroundSize
                (BackgroundSize.AUTO,BackgroundSize.AUTO,false,false, true,true);
        BackgroundImage backgroundImage = new BackgroundImage
                (image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background bkg = new Background(backgroundImage);

        Text sellerNameText = new Text("User Name: ");
        sellerNameText.setFill(Color.WHITE);
        sellerNameText.setFont(Font.font(null, FontWeight.BOLD,15));
        TextField sellerNameField = new TextField();
        sellerNameField.setPromptText("root");
        Text pwdText= new Text("Password: ");
        pwdText.setFill(Color.WHITE);
        pwdText.setFont(Font.font(null, FontWeight.BOLD,15));
        PasswordField pwdField = new PasswordField();
        pwdField.setPromptText("winter");

        Button loginBtn = new Button("LOGIN");
        loginBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        loginBtn.setOnAction(event -> {
            String usr = sellerNameField.getText();
            String pwd = pwdField.getText();
            if(usr.equals("root") && pwd.equals("winter")){
                new DBInit();
                new TableInit();
                new BooksSalesFrame().start(new Stage());
                primaryStage.close();
            }else {
                pwdField.clear();
                msgLabel.setVisible(true);
                msgLabel.setText("INVALID USER NAME OR PASSWORD");
            }
        });

        Button exitBtn = new Button("EXIT");
        exitBtn.setFont(Font.font(null, FontWeight.BOLD, 15));
        exitBtn.setOnAction(event -> primaryStage.close());

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.add(sellerNameText, 0,0);
        gridPane.add(sellerNameField , 1,0);
        gridPane.add(pwdText, 0,1);
        gridPane.add(pwdField, 1,1);

        msgLabel.setPadding(new Insets(0,0,0,20));
        msgLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        msgLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        msgLabel.setTextFill(Color.SILVER);
        msgLabel.setVisible(false);

        HBox labelBox = new HBox();
        labelBox.getChildren().add(msgLabel);
        labelBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.getChildren().addAll(exitBtn, loginBtn);
        hBox.setMargin(exitBtn, new Insets(10,20,15,0));
        hBox.setMargin(loginBtn, new Insets(10,0,15,20));


        VBox vBox = new VBox();
        vBox.setBackground(bkg);
        vBox.getChildren().addAll(gridPane, hBox, labelBox);
        vBox.setMargin(gridPane, new Insets(70,0,0,0));
        vBox.setMargin(hBox, new Insets(20,0,0,0));
        Scene scene = new Scene(vBox,400,250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
