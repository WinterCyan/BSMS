import UI.LoginFrame;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestProg extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginFrame().start(new Stage());
    }
}