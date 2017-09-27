package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



class Test1 extends Application {


    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    void myPaint( ) {
        Rectangle r = new Rectangle(25, 25, 100, 150);
        r.setFill(Color.GREEN);
        rootLayout.getChildren().add(r);
    }

    void mySetRootLayout(BorderPane bp) {
        this.rootLayout = bp;

    }


    void myInit(Stage primaryStage) {

        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("try.fxml"));
            rootLayout = (BorderPane) loader.load();


            System.out.println("--------");
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);


            MenuController controller = loader.getController();
            controller.setRootLayout(rootLayout);
            controller.setPrimaryStage(primaryStage);

            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}


class Test2 extends Application {


    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    void myPaint() {
        Rectangle r = new Rectangle(25, 25, 25, 25);
        r.setFill(Color.BLUE);
        rootLayout.getChildren().add(r);
    }

    void mySetRootLayout(BorderPane bp) {
        this.rootLayout = bp;
    }


    void myInit(Stage primaryStage) {

        try {

            this.primaryStage = primaryStage;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("try.fxml"));
            rootLayout = (BorderPane) loader.load();

            System.out.println("--------");
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);



            MenuController controller = loader.getController();
            controller.setRootLayout(rootLayout);
            controller.setPrimaryStage(primaryStage);


            primaryStage.setMinWidth(650);
            primaryStage.setMinHeight(400);
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}

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
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("try.fxml"));
        rootLayout = (BorderPane) loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        MenuController controller = loader.getController();
        controller.setRootLayout(rootLayout);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(400);
        primaryStage.show();

    }


    void myPaint() {
        Rectangle r = new Rectangle(25, 25, 250, 250);
        r.setFill(Color.BLUE);
        rootLayout.getChildren().add(r);
    }


    void myInit(Stage primaryStage) {

        try {

            this.primaryStage = primaryStage;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("try.fxml"));
            rootLayout = (BorderPane) loader.load();


            System.out.println("--------");
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            MenuController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setMinWidth(650);
            primaryStage.setMinHeight(400);
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Error");
        }

    }


    public static void main(String[] args) {
        launch(args);
    }


}