package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.RdWrUtil;
import sample.entity.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private Main mainApp;
    @FXML
    ListView mainList;
    @FXML
    ListView rangeList;


    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        mainList.setItems( mainApp.getMainList());
        rangeList.setItems(mainApp.getRangeList());

    }

    public void onSelect(ActionEvent actionEvent) {

        List<Train> trains ;
        trains = showRangeDialog(mainApp.getMainList());
        mainApp.getRangeList().clear();
        if(trains!=null) {
            mainApp.getRangeList().addAll(trains);
        }
    }

    public void onAdd(ActionEvent actionEvent) {
        Train t = new Train() ;
        boolean isOkClicked = showEditDialog(t);
        if(isOkClicked) {
            mainApp.getMainList().add(t);
        }
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
            mainApp.getMainList().clear();
            mainApp.getRangeList().clear();
            mainApp.getMainList().addAll(RdWrUtil.loadPersonDataFromFile(file));
          //  mainList.setItems(mainApp.getMainList() );
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
            RdWrUtil.savePersonDataToFile(file, mainApp.getMainList());
        }
    }


    public void onClose(ActionEvent actionEvent) {
    }


    public boolean showEditDialog(Train train ) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("edit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Train");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            controller.setTrain(train);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Train> showRangeDialog(List<Train> trains ) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("range.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Set Range");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());


            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RangeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTrains(trains);
            dialogStage.showAndWait();


            if( controller.isOkClicked()){
                return controller.getTrains();
            }else{
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
