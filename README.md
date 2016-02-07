A java based RMI chat server for multiple clients with a GUI in Java Swing

To run:

go to bin (or wherever your class files are)
Start up a cmd window and change directory to the current bin
do rmic Server_Package.ChatServer
do rmic Client_Package.ChatClient 
now start registry .....rmiregistry

To Start Server run ChatServerDriver
To Start a client run ChatClientGUI

you now have a client and a server, you can send messages, repeat the last step to make another client to

communicate between the 2
