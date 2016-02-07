package Server_Package;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Client_Package.ClientIF;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ServerIF {

    private final ArrayList<ClientIF> chatClients;
    private static int client_count = 0;

    ChatServer() throws RemoteException {
        chatClients = new ArrayList<>();
    }

    public synchronized void registerChatClient(ClientIF chatClient)throws RemoteException {
        this.chatClients.add(chatClient);
        client_count++;
    }

    public synchronized void broadcastMessage(String message)throws RemoteException {
        int i = 0;
        while (i < chatClients.size()) {
            chatClients.get(i++).getMessage(message);
        }
    }

    public static int getClients() {
        return client_count;
    }
    public void disconnectClient() throws RemoteException {
        client_count--;
    }
}
