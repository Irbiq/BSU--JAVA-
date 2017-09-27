package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Optional;

/**
 * Created by Maxim on 11.09.2016.
 */

public class MenuController {

    @FXML
    TextArea ta1;
    @FXML
    TextArea ta2;
    private Main mainApp;

    public Main getMainApp (){
        return this.mainApp;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        ta1.setWrapText(true);
        ta2.setWrapText(true);

        this.mainApp.getPrimaryStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Do you wanna save ur masterpiece ?");
                    ButtonType bOk = new ButtonType("Yes");
                    ButtonType bC = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(bOk, bC);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == bOk) {
                        FileChooser fileChooser = new FileChooser();
                        String currentDir = System.getProperty("user.dir");
                        fileChooser.setInitialDirectory(new File(currentDir));
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                                "txt files (*.txt)", "*.txt"));
                        File file = fileChooser.showSaveDialog(((Stage)event.getSource()));

                        if (file != null) {
                            if (!file.getPath().endsWith(".txt")) {
                                file = new File(file.getPath() + ".txt");
                            }
                            RdWrUtil.savePersonDataToFile(file, ta2.getText());
                        }else {
                            event.consume();
                        }
                    } else if (result.get() == bC) {
                       // event.consume();
                    }
            }
        });

    }

    public void onOpen(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        String currentDir = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDir));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "txt files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            ta1.setText(RdWrUtil.loadPersonDataFromFile(file));
        }
    }

    public void onSave(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        String currentDir = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDir));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "txt files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }
            RdWrUtil.savePersonDataToFile(file, ta2.getText());
        }
    }

    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TextTransform");
        alert.setHeaderText("About");
        alert.setContentText("Author: Maxim Karzhavin\n");
        alert.showAndWait();
    }

    public void onClose(ActionEvent actionEvent) {
    }

    public void onAnalyze(ActionEvent actionEvent) {
        ta2.setText(Analyzer.analyze(ta1.getText()));
    }

}
