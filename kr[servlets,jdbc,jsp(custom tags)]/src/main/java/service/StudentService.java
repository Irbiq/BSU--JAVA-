package service;

import dao.StudentDAO;
import entity.Student;

import java.util.List;

/**
 * Created by ASUS on 05.06.2017.
 */
public class StudentService {
    StudentDAO stDAO = new StudentDAO();

    public List<Student> getAll() {

        return this.stDAO.getAll();

    }

    public int getCount(){
        return stDAO.getCount();
    }
}