import java.util.regex.Pattern;

/**
 * Created by ASUS on 24.12.2016.
 */
public class Main {



    public static void main(String[] args) {


       String pattern = "(?=([^']*'[^']*')*[^']*$)((//.*)|(\\{[^\\}]*\\})|(\\(\\*([^*]*|(\\*+([^\\)])))*\\*\\)))";


        String code = "  Program comments;\n" +
                "  Var a,b:Integer;  // тут строчный комментарий\n" +
                "  Begin\n" +
                "     WriteLn('Введи{те} // число');\n" +
                "\ta:=0;\n" +
                "\tb:=10;\n" +
                "\tfor a:=1 to 10 do\n" +
                "     WriteLn('Введи{*те}  чи*)сло');\n" +

                "\t  begin\n" +
                "\t    Write (a*b, ' ');\n" +
                "\t  end;\n" +
                "\t{  Это блочный комментарий\n" +
                "\tfor a:=1 to 10 do\n" +
                "\t  begin\n" +
                "\t    Write (a*b, ' ');\n" +
                "\t  end;\n" +
                "\t все что внутри не исполняется}\n" +
                "  End." + "(*sdfgh' 'jklkj)h*g)fd*)" +
                "(*****fghjklk**jhgf***)" ;

        System.out.println(code);
        System.out.println(Pattern.compile(pattern).matcher(code).replaceAll(""));

    }


}
