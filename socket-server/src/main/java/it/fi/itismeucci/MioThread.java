package it.fi.itismeucci;

import java.io.*;
import java.net.*;

public class MioThread extends Thread {
    Socket client;

    public MioThread(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            comunica();
        } catch (Exception e) {
        }
    }

    public void comunica() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        for (;;) {
            String recv = in.readLine();
            String modifiedRecv = recv.toUpperCase();
            if (modifiedRecv.equals("FINE"))
            this.client.close();
            out.writeBytes(modifiedRecv + '\n');
        }
    }
}
