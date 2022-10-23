package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MioThread extends Thread {
    Socket client;
    ArrayList<Socket> S;
    ServerSocket server;

    public MioThread(Socket client, ArrayList<Socket> S, ServerSocket server) {
        this.client = client;
        this.S = S;
        this.server=server;
    }

    public void run() {
        while(SocketServer.serverAttivo){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                String recv = in.readLine();
                String modifiedRecv = recv.toUpperCase();
                if (recv.equals("FINE"))
                this.client.close();
                out.writeBytes(modifiedRecv + '\n');
                if(recv.equals("SPEGNI")){
                    SocketServer.serverAttivo = false;
                    for (Socket s : S){
                        try{
                            s.close();
                        }catch (Exception e){
                            System.out.println("Socket già chiuso");
                        }
                    }
                    server.close();
                    return;
                }
             } catch (IOException e) {
            System.out.println("Errore");
        }
        }
    }

  /*  public void comunica() throws IOException {
            while(SocketServer.serverAttivo){
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String recv = in.readLine();
            String modifiedRecv = recv.toUpperCase();
            if (modifiedRecv.equals("FINE"))
            this.client.close();
            out.writeBytes(modifiedRecv + '\n');
            if(modifiedRecv.equals("SPEGNI")){
                SocketServer.serverAttivo = false;
                for (Socket s : S){
                    try{
                        s.close();
                    }catch (Exception e){
                        System.out.println("Socket già chiuso");
                    }
                }
                server.close();
                return;
            }
            
         }
        }
        */ 
        
    }
