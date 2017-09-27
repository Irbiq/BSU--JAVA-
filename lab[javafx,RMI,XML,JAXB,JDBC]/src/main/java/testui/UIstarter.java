package testui;

import org.xml.sax.SAXException;
import entity.Train;
import entity.TrainList;
import utils.JAXBParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;


public class UIstarter {


    public static void main(String[] args) {

        Train t = new Train(1,"France",new Time(12,12,12),new Time(15,15,15));

        ArrayList<Train> trains= new ArrayList<>();

        trains.add(t);
        trains.add(t);

        TrainList tl = new TrainList();
        tl.setTrains(trains);

        tl.getList().forEach(System.out::println);

        JAXBParser parser = new JAXBParser();
        File f = new File("data.xml");
        try {
            parser.saveObject(f,tl);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            tl = (TrainList) parser.getObject(new File("data.xml"),TrainList.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println(tl);

        tl.getList().forEach(System.out::println);

    }
}
