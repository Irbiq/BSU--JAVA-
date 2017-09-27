package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by ASUS on 24.03.2017.
 */
public class DeleteDialogController {

    private Stage dialogStage;
    @FXML
    TextField numberField;
    private boolean okClicked = false;
    private int numb;

    public int getNumb() {
        return this.numb;
    }

    public void onOk(ActionEvent actionEvent) {

        try {
            numb =Integer.parseInt(numberField.getText());
            okClicked = true;
            dialogStage.close();
        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid data");
            a.showAndWait();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }


}
