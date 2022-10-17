package it.fi.itismeucci;
import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner keyboard = new Scanner(System.in);
    private String userString="";
    private String serverString;

    public Socket connect() throws IOException
    {
        this.socket = new Socket(InetAddress.getLocalHost(), 6789);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        return socket;
    }
    
    public void send() throws IOException
    {
        while(!this.userString.equals("fine")){
            System.out.print("Inserisci la stringa da trasmettere al server: ");
            this.userString = this.keyboard.next();
            out.writeBytes(userString + '\n');
            serverString = in.readLine();
            System.out.print("Risposta dal server: " + serverString + "\n");
        }
        socket.close();
    }
}
