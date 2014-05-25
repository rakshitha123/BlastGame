/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketConnections;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compaq
 */
public class OutputSocket {

    private String host;
    private int port;
    private Socket clientSocket;
    private BufferedWriter out;

    public OutputSocket(String host, int port) {
        
        this.host = host;
        this.port = port;
        connect();
    }

    public final void connect() {
        try {
            clientSocket = new Socket(host, port);
        } catch (IOException ex) {
            Logger.getLogger(OutputSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String msg) {
        try {

            if (clientSocket == null) {         //If the socket is closed previously create new socket
                connect();
            }
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write(msg);
            out.close();
            clientSocket.close();               // close the socket to send data to the network
            clientSocket = null;                // make the client soc null after closing

        } catch (IOException ex) {
            Logger.getLogger(OutputSocket.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void closeCon() {
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(OutputSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
