package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.entity.Train;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Train> mainList = FXCollections.observableArrayList();
    private ObservableList<Train> rangeList = FXCollections.observableArrayList();


    public ObservableList<Train> getMainList() {
        return mainList;
    }

    public ObservableList<Train> getRangeList() {
        return rangeList;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/views/sample.fxml"));
        rootLayout = (BorderPane) loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        this.primaryStage=primaryStage;

        Controller controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setTitle("TrainsList");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
