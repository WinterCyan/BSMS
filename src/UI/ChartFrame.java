package UI;

import BasicClass.Book;
import BasicClass.BookList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChartFrame extends Application{
    private BookList bookList = new BookList();
    private ArrayList<String> nameList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        nameList = new ArrayList<>();
        for(Book book : bookList){
            String bookName = book.getBookName();
            nameList.add(bookName);
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(nameList));
        xAxis.setLabel("Books");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Sales");

        BarChart<String, Number> saleChart = new BarChart<>(xAxis, yAxis);
        saleChart.setTitle("Sales Chart");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Books");
        for(Book book : bookList){
            series.getData().add(new XYChart.Data<>(book.getBookName(), book.getSale()));
        }

        saleChart.getData().add(series);
        Group root = new Group(saleChart);
        Scene scene = new Scene(root, 550,400);
        primaryStage.setTitle("Sale Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
