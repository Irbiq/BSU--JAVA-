package newtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class CustomTableTag extends BodyTagSupport {
    private StringBuilder writer = new StringBuilder();
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            writer.append("<table border=\"1\"").append(">");
            writer.append("<tr>");
            writer.append("<th>");
            writer.append("id");
            writer.append("</th>");
            writer.append("<th>");
            writer.append("surname");
            writer.append("</th>");
            writer.append("<th>");
            writer.append("klass");
            writer.append("</th>");
            writer.append("<th>");
            writer.append("letter");
            writer.append("</th>");
            writer.append("</tr>");
            pageContext.getOut().print(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            writer.setLength(0);
            writer.append("</table>");
            pageContext.getOut().print(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }
}
