package utils;

import java.sql.Time;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimeAdapter extends XmlAdapter<String, Time> {

    @Override
    public String marshal(Time v) throws Exception {

        synchronized (v) {
            return v.toString();
        }
    }

    @Override
    public Time unmarshal(String v) throws Exception {

            String[] hms = v.split(":");
            return new Time(new Integer(hms[0]),
                            new Integer(hms[1]),
                            new Integer(hms[2]));
    }

}