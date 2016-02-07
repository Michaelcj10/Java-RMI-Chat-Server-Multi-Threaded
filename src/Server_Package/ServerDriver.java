package Server_Package;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

class ServerDriver {

    public static void main (String [] args) throws RemoteException, MalformedURLException {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        new ServerGUI();
        Naming.rebind("RMIChatServer",new ChatServer());
    }
}
