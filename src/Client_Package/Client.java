package Client_Package;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Server_Package.ServerIF;

public class Client extends UnicastRemoteObject implements ClientIF,Runnable {

    private final ServerIF chatServer;
    private String name = null;

    Client(String name, ServerIF chatServer)throws RemoteException {
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this);
        chatServer.broadcastMessage(name + " Has Connected");
    }

    public void getMessage(String message) throws RemoteException {
        System.out.println(message);
        ClientGUI.area.append("\n\n" + message);
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            check();
            try {
                checkDisconnect();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void check() {
        String text = ClientGUI.field.getText();

        if (ClientGUI.posted) {
            try {
                chatServer.broadcastMessage(name + " Says: " + text);
                ClientGUI.posted = false;
                ClientGUI.field.setText("");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkDisconnect() throws RemoteException {
        if(ClientGUI.disconnected){
            chatServer.broadcastMessage(name + " Has Disconnected");
            ClientGUI.disconnected =false;
            chatServer.disconnectClient();
        }
    }
}
