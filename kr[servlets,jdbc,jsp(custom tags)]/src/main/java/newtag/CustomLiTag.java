package newtag;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


@Data
public class CustomLiTag extends BodyTagSupport {

    private String color;
    private String font;
    private StringBuilder builder = new StringBuilder();

    @Override
    public int doEndTag() throws JspException {
        CustomLuTag parent = (CustomLuTag) findAncestorWithClass(this, CustomLuTag.class);
        if (parent == null) {
            try {
                ((HttpServletResponse)pageContext.getResponse()).sendRedirect("/error.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        builder.setLength(0);
        String context = getBodyContent().getString();
        if (context.length()>=40){
            throw new RuntimeException("Wrong length");
        }

        builder.append("<li style=\"").append("color : ")
                .append(color).append(";").append(" font-family : ")
                .append(font).append(";\">")
                .append(context).append("</li>");
        try {
            pageContext.getOut().write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }
}
