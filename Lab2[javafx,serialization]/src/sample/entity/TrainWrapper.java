package sample.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 18.09.2016.
 */
public class TrainWrapper {

    public List<Train> trains;

    public static List<Train> select(List<Train> trains,String where, Timestamp from, Timestamp to) {

        List<Train> res = new ArrayList<>();
        trains.forEach((e) -> {
            if (e.getDestination().compareTo(where) == 0 &&
                    e.getDepartureTime().compareTo(from) >= 0 &&
                    e.getDepartureTime().compareTo(to) <= 0) {
                res.add(e);
            }
        });
        return res;
    }

    public List<Train> getTrains() {
        return trains;
    }

}

