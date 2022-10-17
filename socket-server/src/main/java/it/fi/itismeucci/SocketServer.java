package it.fi.itismeucci;

import java.io.*;
import java.net.*;

public class SocketServer {

    public Socket avvia() throws IOException {
        ServerSocket server = new ServerSocket(6789);
        for (;;) {
            Socket client = server.accept();
            MioThread t1 = new MioThread(client);
            t1.start();
        }
    }

}
