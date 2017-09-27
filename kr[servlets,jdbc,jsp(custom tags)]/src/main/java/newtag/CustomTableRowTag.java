package newtag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class CustomTableRowTag extends SimpleTagSupport {
    private JspWriter jspWriter;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    private String align;
    private Writer writer;

    @Override
    public void doTag() throws JspException, IOException {
        Object parent = findAncestorWithClass(this, CustomTableTag.class);
        if (parent == null)
            throw new JspTagException("no parent");
        jspWriter = getJspContext().getOut();
        jspWriter.append("<tr align=\"").append(align).append("\">");
        writer = new StringWriter();
        getJspBody().invoke(writer);
        String[] td = writer.toString().split(" ");
        for(int i =0;i<td.length;i++){
            jspWriter.append("<td>");
            jspWriter.append(td[i]);
            jspWriter.append("</td>");
        }
        jspWriter.append("</tr>");
    }

}
