package entity;

import utils.TimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Time;


@XmlRootElement(name = "train")
@XmlType(propOrder = {"numb","destination","departureTime","arrivalTime"})
public class Train implements Comparable<Train>, Serializable {



    private String destination;
    private int numb;

    private Time departureTime;
    private Time arrivalTime;


    @XmlElement
    public String getDestination() {
        return destination;
    }

    @XmlElement
    public int getNumb() {
        return numb;
    }

    @XmlElement
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public Time getDepartureTime() {
        return departureTime;
    }

    @XmlElement
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public Train(String destination, int numb, Time departureTime, Time arrivalTime) {
        this.destination = destination;
        this.numb = numb;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Train() {
    }

    ;

    public Train(int numb, String destination, Time arrivalTime,Time departureTime) {
        this.numb = numb;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return
                " №: " + numb +
                        " D: " + destination +
                        " DT: " + departureTime +
                        " AT: " + arrivalTime;
    }


    @Override
    public int compareTo(Train o) {
        int result;
        if ((result = this.destination.compareTo(o.destination)) != 0)
            return result;
        else if ((result = this.departureTime.compareTo(o.departureTime)) != 0)
            return result;
        else if ((result = this.arrivalTime.compareTo(o.arrivalTime)) != 0)
            return result;
        return 0;
    }
}
