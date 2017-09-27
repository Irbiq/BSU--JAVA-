package rmi;


import entity.Train;
import entity.TrainList;
import org.xml.sax.SAXException;
import utils.JAXBParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 21.03.2017.
 */
public class TrainObjectServiceXML extends UnicastRemoteObject implements TrainRemote {


    String fileName;

    protected TrainObjectServiceXML() throws RemoteException {
    }


    @Override
    public void addTrain(Train t) {

        ArrayList<Train> tl = (ArrayList<Train>) getAllTrains(fileName);
        tl.add(t);
        TrainList trl = new TrainList();
        trl.setTrains(tl);
        saveTrains(fileName, trl);

    }

    @Override
    public List<Train> range(String destination, Time from, Time to) {

        ArrayList<Train> tl = (ArrayList<Train>) getAllTrains(fileName);

        ArrayList<Train> rl = new ArrayList<>();

        for (Train t : tl) {
            if (t.getDestination().equals(destination)
                    && (t.getDepartureTime().compareTo(from) != -1)
                    && (t.getDepartureTime().compareTo(to) != 1)) {
                rl.add(t);
            }
        }

        TrainList trl = new TrainList();
        trl.setTrains(rl);
        return rl;

    }

    @Override
    public void deleteTrain(Integer number) {

        ArrayList<Train> tl = (ArrayList<Train>) getAllTrains(fileName);


        for (int i = 0; i < tl.size(); ++i) {
            if(tl.get(i).getNumb()==number){
                tl.remove(i);
            }
        }


        TrainList trl = new TrainList();
        trl.setTrains(tl);
        saveTrains(fileName, trl);

    }

    @Override
    public List<Train> getAllTrains() throws RemoteException {
        return null;
    }


    public List<Train> getAllTrains(String file) {

        fileName = file;
        JAXBParser parser = new JAXBParser();
        List<Train> list;
        TrainList tl;
        try {
            tl = (TrainList) parser.getObject(new File(file), TrainList.class);
            System.out.println(tl.getList());

            list = tl.getList();
            return list;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void saveTrains(String file, TrainList tl) {

        JAXBParser parser = new JAXBParser();
        try {
            parser.saveObject(new File(file), tl);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
