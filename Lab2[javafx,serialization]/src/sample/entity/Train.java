package sample.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * Created by Maxim on 17.09.2016.
 * 4.	В файле хранится информация о движении поездов: номер рейса,
 * пункт назначения, время отправления и прибытия. Вывести информацию
 * обо всех рейсах, отправляющихся в заданный город, время отправления
 * которых находится в определенном временном отрезке.
 */
public class Train implements Comparable<Train>,Serializable {

   private String destination;
   private int numb;
   private Timestamp departureTime;

    public String getDestination() {
        return destination;
    }

    public int getNumb() {
        return numb;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private Timestamp arrivalTime;
    public Train(){};

    public Train(int numb, String destination, Timestamp departureTime, Timestamp arrivalTime) {
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
                " AT: " + arrivalTime ;
    }



    @Override
    public int compareTo(Train o) {
        int mark;
        if ((mark = this.destination.compareTo(o.destination)) != 0)
            return mark;
        else if ((mark = this.departureTime.compareTo(o.departureTime)) != 0)
            return mark;
        else if ((mark = this.arrivalTime.compareTo(o.arrivalTime)) != 0)
            return mark;
        return 0;
    }
}
