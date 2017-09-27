package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.entity.Train;
import sample.entity.TrainWrapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Maxim on 19.09.2016.
 */
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
            Timestamp from = Timestamp.valueOf(atfromField.getText());
            Timestamp to = Timestamp.valueOf(attoField.getText());
            this.trains = TrainWrapper.select(trains, where, from, to);
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
}
