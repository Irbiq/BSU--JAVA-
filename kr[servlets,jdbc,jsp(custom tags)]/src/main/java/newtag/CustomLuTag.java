package newtag;

import lombok.Data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * <p>Custom tag for html list</p>
 *
 * @author Maxim Karzhavin
 * @version 1.0
 **/
@Data
public class CustomLuTag extends BodyTagSupport {
    private StringBuilder writer = new StringBuilder();
    private String type;



    @Override
    public int doStartTag() throws JspException {

        try {
            writer.setLength(0);
            writer.append("<table type=\"")
                    .append(type)
                    .append("\">");
            pageContext.getOut().print(writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        writer.setLength(0);
        writer.append("</ul>");
        try {
            pageContext.getOut().print(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
