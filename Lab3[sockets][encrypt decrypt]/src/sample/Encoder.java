package sample;

/**
 * Created by Maxim on 05.10.2016.
 */
public class Encoder {


    private int a;
    private int b;
    private  final static int n = 65536 ;
    {
        this.a=11;
        this.b=11;
    }

    void setA(int a) {
        this.a = a;
    }

    void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public Encoder (){};

    public Encoder (int a,  int b){
        this.a=a;
        this.b=b;
    }

    public String encrypt(String text) {

        StringBuilder encrypter = new StringBuilder(text);

        for (int i = 0; i < encrypter.length(); i++) {
            int ch = (((int) encrypter.charAt(i)) * a + b)%n;
            encrypter.setCharAt(i, (char) ch);
        }

        return encrypter.toString();
    }


    public String decrypt(String text) {

        StringBuilder encrypter = new StringBuilder(text);
        for (int i = 0; i < encrypter.length(); i++) {
         //   int ch = (((int) encrypter.charAt(i)) - b) / a;
            int a_r=0;
            while ( (a*a_r)%n!=1 ){
                a_r++;
            }
            int ch2 = ( (a_r*((int)encrypter.charAt(i)+n-b))%n  );


            encrypter.setCharAt(i, (char) ch2);
        }
        return encrypter.toString();
    }

}

