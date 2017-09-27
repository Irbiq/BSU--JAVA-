package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ----------- toDO ---------------------
 * encrypt
 * send
 * decrypt
 * */
public class Main extends Application {

    Socket client;

    public void setClient(Socket client) {
        this.client = client;
    }

    @Override
    @PostConstruct
    public void init() throws Exception {
        super.init();
        try {
            client = new Socket(InetAddress.getLocalHost(), 8081);
        } catch (Exception e) {
            System.out.println("server is not available");
            System.exit(-1);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/view/gui.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setClient(client);
        controller.setApplication(this);
        primaryStage.setTitle("EchoEncrypt");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    @PreDestroy
    public void stop() throws Exception {
        super.stop();
        client.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
