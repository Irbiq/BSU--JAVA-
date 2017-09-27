package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rmi.RMIClient;
import entity.Train;
import entity.TrainList;
import sample.Main;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    RMIClient client;
    private Main mainApp;
    @FXML
    ListView mainList;
    @FXML
    ListView rangeList;


    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        client = mainApp.getClient();
        mainList.setItems(mainApp.getMainList());
        rangeList.setItems(mainApp.getRangeList());

    }

    public void onRange(ActionEvent actionEvent) {

        List<Train> trains;
        trains = showRangeDialog(mainApp.getMainList(), client);
        mainApp.getRangeList().clear();
        if (trains != null) {
            mainApp.getRangeList().addAll(trains);
        }
    }

    public void onAdd(ActionEvent actionEvent) {
        Train t = new Train();
        boolean isOkClicked = showEditDialog(t);
        if (isOkClicked) {
            try {
                client.getRemoteObject().addTrain(t);   //добавление
                mainApp.getMainList().add(t);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public void onDelete(ActionEvent actionEvent) {

        int number = showDeleteDialog();
        if (number != -1) {
            try {
                client.getRemoteObject().deleteTrain(number);

                mainApp.getMainList().clear();
                mainApp.getRangeList().clear();
                try {
                    mainApp.getMainList().addAll(client.getRemoteObject().getAllTrains("data.xml")); // for SQL delete data.xml
                } catch (RemoteException e) {
                    System.out.println("Что-то пошло не так");
                }

                mainList.setItems(mainApp.getMainList());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    public void onOpen(ActionEvent actionEvent) {


        mainApp.getMainList().clear();
        mainApp.getRangeList().clear();
        try {
            mainApp.getMainList().addAll(client.getRemoteObject().getAllTrains());
        } catch (RemoteException e) {
            System.out.println("Что-то пошло не так");
        }

        mainList.setItems(mainApp.getMainList());

    }

    public void onClose(ActionEvent actionEvent) {
    }


    public boolean showEditDialog(Train train) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/edit.fxml"));
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

    public int showDeleteDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/delete.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Delete Train");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            DeleteDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                return controller.getNumb();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public List<Train> showRangeDialog(List<Train> trains, RMIClient cl) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/range.fxml"));
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
            controller.setRmiClient(cl);
            dialogStage.showAndWait();


            if (controller.isOkClicked()) {
                return controller.getTrains();
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void onXMLOpen( ) {

        mainApp.getMainList().clear();

        try {
            mainApp.getMainList().addAll(client.getRemoteObject().getAllTrains("data.xml"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void onXMLSave( ) {

        TrainList tl = new TrainList();

        ArrayList<Train> tr = new ArrayList<>();
        tr.addAll(mainApp.getMainList());
        tl.setTrains(tr);
        try {
            client.getRemoteObject().saveTrains("data.xml", tl);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}