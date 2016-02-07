package Client_Package;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Server_Package.ServerIF;

class ClientGUI extends JFrame implements ActionListener {

    private static final JLabel message = new JLabel("Joined at: ");
    private static final JLabel welcome = new JLabel();
    static final JTextField field = new JTextField(20);
    private static final JButton post = new JButton("Post Message");
    private static final JButton disconnect = new JButton("Disconnect");
    static final JTextArea area = new JTextArea(5, 5);
    static Boolean posted = false;
    static Boolean disconnected = false;

    private ClientGUI() {

        super("Chat Client");
        setUpPanel(this);
        setAddEventListeners();
    }

    private void setUpPanel(ClientGUI clientGUI) {
        JPanel panel_north = new JPanel();
        JPanel panel_middle = new JPanel(new GridLayout(1, 2));
        panel_north.add(welcome);
        panel_north.add(message);
        JPanel panel_south = new JPanel();
        panel_south.add(field);
        panel_south.add(post);

        clientGUI.setResizable(false);
        clientGUI.setVisible(false);
        clientGUI.showTime();

        area.setEditable(false);
        addScrollPanelToPanel(panel_middle);

        clientGUI.setUserTextColor();
        setComponentColors(panel_south,panel_north);

        clientGUI.setBounds(400, 400, 750, 500);
        addEverythingToMainPanel(clientGUI,panel_middle,panel_north,panel_south);

        area.setBackground(Color.white);
        clientGUI.setVisible(true);
    }

    private void addScrollPanelToPanel(JPanel panel_middle){
        JScrollPane scrollPane = new JScrollPane(area);
        panel_middle.add(scrollPane);
    }

    private void setComponentColors(JPanel panel_south, JPanel panel_north){
        post.setBackground(Color.BLACK);
        post.setForeground(Color.WHITE);
        disconnect.setBackground(Color.BLACK);
        disconnect.setForeground(Color.WHITE);
        panel_north.setBackground(Color.WHITE);
        panel_south.setBackground(Color.WHITE);
    }

    private void addEverythingToMainPanel(ClientGUI clientGUI, JPanel panel_middle, JPanel panel_north, JPanel panel_south){
        JPanel c = new JPanel();
        c.setBackground(Color.WHITE);
        c.setBorder(new EmptyBorder(5, 5, 5, 5));
        c.setLayout(new BorderLayout(0, 0));
        clientGUI.setContentPane(c);
        c.add(panel_north, BorderLayout.NORTH);
        c.add(panel_middle, BorderLayout.CENTER);
        c.add(panel_south, BorderLayout.SOUTH);
        panel_north.add(disconnect);
    }

    private void setAddEventListeners(){
        field.addActionListener(this);
        post.addActionListener(this);
        disconnect.addActionListener(this);
    }

    private int generateRandomRGBValue(){
        Random rand = new Random();
        return rand.nextInt(255);
    }

    private void setUserTextColor(){
        area.setForeground(new Color(generateRandomRGBValue(),generateRandomRGBValue(),generateRandomRGBValue()));
    }

    private void showTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        message.setText("Joined: " + dateFormat.format(cal.getTime()));
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == post) {
            System.out.println(field.getText());
            posted = true;
         }

        else if (source == disconnect) {

            disconnected = true;
            dispose();
        }
    }

    public static void main(String[] args) throws MalformedURLException,RemoteException, NotBoundException {
        new ClientGUI();
        String name = getUserName();
        String chatServerURL = "rmi://localhost/RMIChatServer";
        ServerIF chatServer = (ServerIF) Naming.lookup(chatServerURL);
        new Thread(new Client(name, chatServer)).start();
        welcomeUser(name);
    }

    private static String getUserName(){
        String name = JOptionPane.showInputDialog("Enter Your Name");
        welcome.setText("Welcome " + name);
        return name;
    }

    private static void welcomeUser(String name){
        System.out.println("Welcome " + name);
        System.out.println("Please Enter A Message");
    }
}
