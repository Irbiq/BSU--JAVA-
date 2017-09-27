package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            //Instantiate server object
            Scanner sc = new Scanner(System.in);
            System.out.println("enter mode: xml/db ");
            String mode = sc.next();

            TrainRemote to;
            if(mode.equals("db")) {
                 to = new TrainObjectServiceDB();////////// TrainObjectServiceXML
            }else {
                 to = new TrainObjectServiceXML();
            }
            //Register server object
            registry.rebind("Train", to);
            System.out.println("RMIServer is created!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
