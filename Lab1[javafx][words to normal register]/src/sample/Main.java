package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    @FXML
    TextArea ta1;
    @FXML
    TextArea ta2;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/sample/views/analyze.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            MenuController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.setTitle("TextAnalyzer");
            primaryStage.setMinWidth(650);
            primaryStage.setMinHeight(400);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}