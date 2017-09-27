package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmi.RMIClient;
import entity.Train;
import sample.Main;

import java.sql.Time;
import java.util.List;


public class RangeDialogController {

    @FXML
    TextField destField;
    @FXML
    TextField atfromField;
    @FXML
    TextField attoField;

    private Stage dialogStage;
    private Main mainApp ;

    boolean isOkClicked = false;
    private List<Train> trains;
    private RMIClient rmiClient;
    public List<Train> getTrains() {
        return trains;
    }

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }


    public void onOk(ActionEvent actionEvent) {

        try {
            isOkClicked = true;
            String where = destField.getText();
            Time from = Time.valueOf(atfromField.getText());
            Time to = Time.valueOf(attoField.getText());
            //this.trains = TrainWrapper.select(trains, where, from, to);
            this.trains = rmiClient.getRemoteObject().range( where, from, to);
            dialogStage.close();
        }catch ( Exception e){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid Data");
            a.showAndWait();
        }

    }

    public void onCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setRmiClient(RMIClient RMICLient) {
        this.rmiClient = RMICLient;
    }
}
