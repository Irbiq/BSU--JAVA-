package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ASUS on 05.06.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    int id;
    String surname;
    String klass;
    String letter;

    @Override
    public String toString (){

        return id+" "+surname+" "+klass+" "+letter;
    }
}
