package command;

import javax.servlet.http.HttpServletRequest;

public class DestroyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().setAttribute("destroy",true);

        System.out.println(request.getSession().getAttribute("destroy"));
        return "/index.jsp";

    }
}
