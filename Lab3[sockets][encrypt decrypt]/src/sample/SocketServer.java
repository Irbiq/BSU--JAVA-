package sample;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 28.09.2016.
 */
public class SocketServer {

    private final static int PORT = 8081;
    public static void main(String[] args) throws IOException {
/*
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Running Shutdown Hook");
                try {
                    for (Socket client : clients) {
                        System.out.println("sended_ERROR");
                        new  DataOutputStream (client.getOutputStream()).writeUTF("closed");
                    }
                }catch (Exception e){
                }

            }
        });
*/
        ServerSocket server = new ServerSocket(PORT);
        Socket incoming;
        System.out.println("server started");
        try {
            while (true) {
                incoming = server.accept();
                Thread t = new Thread(new SocketHandler(incoming));
                t.start();
                System.out.println("connected");
            }
        } catch (IOException e) {
            System.out.println("----------SERVER ERROR----------- ");
        } finally {
            server.close();
        }
    }
}
