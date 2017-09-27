package dao;

import dbmanager.DBManager;
import entity.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 05.06.2017.
 */
@SuppressWarnings("duplicates")
public class StudentDAO {
        private final static  String SELECT_ALL_STUDENTS = "SELECT * FROM students";

        private final static  String SELECT_COUNT_STUDENTS = "SELECT Count(*)  as id FROM students";

        public int getCount(){
            try {
                Connection connection = DBManager.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SELECT_COUNT_STUDENTS);
                while (rs.next()) {
                    int count = rs.getInt(1);
                    return count;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        public List<Student> getAll(){
            List<Student> products;
            try  {
                Connection connection = DBManager.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SELECT_ALL_STUDENTS);
                products = new ArrayList<>();
                int id;
                while (rs.next()) {
                    id = rs.getInt(1);
                    String surname =rs.getString(2) ;
                    String klass=rs.getString(3);
                    String letter=rs.getString(4);

                    products.add(new Student(id,surname,klass,letter));
                }

                return products;
            } catch (SQLException e) {
                //logger.error(e.getMessage());
                e.printStackTrace();
            }
            return Collections.emptyList();

        }
}
