package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Maxim on 11.09.2016.
 */

public class MenuController {


    @FXML
    Label idTextL ;
    @FXML
    Label idCurrencyL ;
    @FXML
    TextField idText ;
    @FXML
    TextField idCurrency ;
    @FXML
    Menu menuLang ;



    Locale locale = new Locale("en","US" ) ;

    Stage primaryStage ;

    BorderPane rootLayout ;

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Main mainApp;

    public Main getMainApp (){
        return this.mainApp;
    }

    public void setMainApp(Main mainApp) {
            this.mainApp = mainApp ;

    }


    public void onEng(ActionEvent actionEvent) {

        locale = new Locale( "en","US" );

        ResourceBundle rb = ResourceBundle.getBundle("bundle",locale);

        idTextL.setText( rb.getString("textL") );
        idText.setText( rb.getString("text")  );
        idCurrencyL.setText(( rb.getString("currencyL") ));
        menuLang.setText( rb.getString("menu") );


    }

    public void onRus(ActionEvent actionEvent) throws Exception  {

        locale = new Locale( "ru","RU" );

        ResourceBundle rb = ResourceBundle.getBundle("bundle",locale);

        idTextL.setText(  translate( rb.getString("textL") ) );
        idText.setText( translate( rb.getString("text") )  );
        idCurrencyL.setText(translate ( rb.getString("currencyL") ));
        menuLang.setText( translate( rb.getString("menu") ));


    }

    public void onShowInfo (  ){

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        Date date = new Date();
        System.out.println(Currency.getInstance(locale) );
        System.out.println(dateFormat.format(date));

    }

    static String translate (String str ) throws Exception{

        return new String(str.getBytes("ISO-8859-1"), "UTF-8");
    }


    public void onTest1() throws Exception {

        Class cl1 = Test1.class;
        Method myInit1= cl1.getDeclaredMethod("myInit", Stage.class);
        Method myPaint1 = cl1.getDeclaredMethod("myPaint");
        //Method setRoot1 = cl1.getDeclaredMethod("mySetRootLayout", BorderPane.class);
        Object c1 = cl1.newInstance();
        myInit1.invoke(c1, this.primaryStage);
        //setRoot1.invoke(c1, this.rootLayout);
        myPaint1.invoke(c1, null);
    }

    public void onTest2( ) throws Exception{

        BorderPane rootLayout ;
        Class cl = Test2.class;
        Method myInit = cl.getDeclaredMethod("myInit", Stage.class);
        Method myPaint = cl.getDeclaredMethod("myPaint");
        //Method setRoot2 = cl2.getDeclaredMethod("mySetRootLayout", BorderPane.class);
        Object c2 = cl.newInstance();
        myInit.invoke(c2, this.primaryStage);
        //setRoot2.invoke(c2, this.rootLayout);
        myPaint.invoke(c2, null);
    }




}
