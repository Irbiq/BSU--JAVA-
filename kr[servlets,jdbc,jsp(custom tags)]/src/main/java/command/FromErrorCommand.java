package command;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class FromErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        int size = ((List<String>) request.getSession().getAttribute("list")).size() ;
        ((List<String>)request.getSession().getAttribute("list")).remove(size-1);

        return "/index.jsp";
    }
}
