package Client_Package;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
    void getMessage(String message) throws RemoteException;
}
