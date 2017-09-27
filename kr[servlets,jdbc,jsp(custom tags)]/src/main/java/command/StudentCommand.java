package command;

import entity.Student;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ASUS on 05.06.2017.
 */
public class StudentCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        StudentService stService = new StudentService();
        List<Student> students = stService.getAll();
        students.forEach(System.out::println);
        req.getSession().setAttribute("students",students);
        req.getSession().setAttribute("count_st",stService.getCount());

        return "/pages/students.jsp";
    }
}
