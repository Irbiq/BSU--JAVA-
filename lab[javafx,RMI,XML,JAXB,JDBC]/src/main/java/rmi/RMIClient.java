package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by ASUS on 21.03.2017.
 */
public class RMIClient {

    private static final String host;
    private static final String bindName;
    static{
        host="localhost";
        bindName="Train";
    }


    private Registry registry ;
    private TrainRemote remoteObject ;

    public TrainRemote getRemoteObject() {
        return remoteObject;
    }

    public void init(){
        try {
            registry = LocateRegistry.getRegistry(host);
            //Lookup server object
            remoteObject = (TrainRemote) registry.lookup(bindName);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}