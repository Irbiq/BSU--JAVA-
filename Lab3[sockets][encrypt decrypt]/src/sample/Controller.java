package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {

    @FXML
    TextArea ta1;
    @FXML
    TextField aField;
    @FXML
    TextField bField;

    Main application;

    private Socket client;
    InputStream sin;
    OutputStream sout;
    DataInputStream in;
    DataOutputStream out;
    Encoder encoder = new Encoder();

    public void setClient(Socket client) {
        this.client = client;
        try {
            sin = client.getInputStream();
            sout = client.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
        } catch (IOException e) {
        }
    }

    public void setApplication(Main application) {
        this.application = application;
    }


    public void onSend(ActionEvent actionEvent) {
        String message = ta1.getText();
        System.out.println(client.isConnected());
        String smessage;
        try {
            System.out.println("__________________________");
            out.writeUTF(encoder.getA() + " " + encoder.getB());
            out.writeUTF(encoder.encrypt(message));
            out.flush();
            smessage = in.readUTF();
            ta1.setText(encoder.decrypt(smessage));
        } catch (Exception e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("THE SERVER WAS SHUTDOWN");
            dialog.showAndWait();

        }
    }

    public void onCansel() {
        aField.setText("");
        bField.setText("");
    }

    public void onOk() {
        try {
            int a = Integer.parseInt(aField.getText());
            int b = Integer.parseInt(bField.getText());
            encoder.setA(a);
            encoder.setB(b);
        } catch (Exception e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("Incorrect input");
            dialog.showAndWait();
        }
    }

    public void onRefresh() {

        try {
            this.client.close();
            application.setClient(null);
            this.client = null;
        } catch (IOException e) {

        }
        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 8081);
            application.setClient(s);
            setClient(s);
        } catch (Exception e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("THE SERVER WAS SHUTDOWN");
            dialog.showAndWait();
        }
    }
}
