package rmi;

import entity.Train;
import entity.TrainList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Time;
import java.util.List;

/**
 * Created by ASUS on 21.03.2017.
 */
public interface TrainRemote extends Remote {

    void addTrain(Train t) throws RemoteException;
    List<Train> range(String where, Time from, Time to) throws RemoteException;
    void deleteTrain(Integer id) throws RemoteException;
    List<Train> getAllTrains() throws RemoteException;
    List<Train> getAllTrains(String file) throws RemoteException;
    void saveTrains(String file, TrainList tl) throws RemoteException;
}
