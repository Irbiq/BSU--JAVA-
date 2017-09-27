package command;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class AddCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String message = request.getParameter("message");
        ((List<String>) request.getSession().getAttribute("list")).add(message);

        return "/index.jsp";
    }
}
