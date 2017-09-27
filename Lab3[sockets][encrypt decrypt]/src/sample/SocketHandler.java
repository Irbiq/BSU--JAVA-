package sample;

import java.io.*;
import java.net.Socket;

/**
 * Created by Maxim on 29.09.2016.
 */
public class SocketHandler implements Runnable {


    private Encoder encoder = new Encoder();

    private Socket incoming;

    private InputStream sin;
    private OutputStream sout;
    private DataInputStream in;
    private DataOutputStream out;
    private String message;

    public SocketHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sin = incoming.getInputStream();
                sout = incoming.getOutputStream();
                in = new DataInputStream(sin);
                out = new DataOutputStream(sout);
                message = in.readUTF();
                String[] strs = message.split(" ");
                encoder.setA(new Integer(strs[0]));
                encoder.setB(new Integer(strs[1]));
                message = in.readUTF();
                message = encoder.decrypt(message);
                /**
                 * какая-нибудь бизнес-логика с сообщением
                 * */
                //System.out.println(message);
                message = encoder.encrypt(message + "_decoded_from_server");
                out.writeUTF(message);
                out.flush();
                System.out.println("sent");

            } catch (IOException x) {
                System.out.println("-----------CLIENT CLOSED-----------");
                return;
            }
        }
    }
}
