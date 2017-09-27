package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.Train;

import java.sql.Timestamp;

/**
 * Created by Maxim on 18.09.2016.
 */
public class EditDialogController {

    private Stage dialogStage;
    private Train t;

    public Train getT() {
        return t;
    }

    @FXML
    TextField numberField;
    @FXML
    TextField atField;
    @FXML
    TextField dtField;
    @FXML
    TextField destinationField;

    private boolean okClicked = false;

    public void onOk(ActionEvent actionEvent) {

        try {
            t.setNumb(Integer.parseInt(numberField.getText()));
            t.setDestination(destinationField.getText());
            t.setDepartureTime(Timestamp.valueOf(dtField.getText()));
            t.setArrivalTime(Timestamp.valueOf(atField.getText()));

            okClicked = true;
            dialogStage.close();
        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid data");
            a.showAndWait();
        }
    }


    void setTrain(Train t){
        this.t=t;
    }

    public void onCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public boolean isOkClicked() {
        return okClicked;
    }


}
