package entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "Trains")
@XmlSeeAlso(Train.class)
public class TrainList implements Serializable {

    @XmlElement(name = "train")
    private ArrayList<Train> trains ;

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }


    public List<Train> getList() {
        return trains;
    }

}
