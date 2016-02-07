package Server_Package;
import Client_Package.ClientIF;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    void registerChatClient(ClientIF chatClient) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void disconnectClient() throws RemoteException;
}
