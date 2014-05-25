/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketConnections;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compaq
 */
public class InputSocket extends Observable implements Runnable {

    private ServerSocket servSoc;
    private Socket soc;
    private ObjectInputStream objIn;
    private OutputSocket outputSocket;

    public InputSocket(int port) {
        
        try {
            // addObserver(user);
            servSoc = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(InputSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("running");

        listen();

    }

    private void listen() {
        while (true) {
            try {
                soc = servSoc.accept();

                InputStream in = soc.getInputStream();
                //  System.out.println(in);

                byte[] buf = new byte[250]; // buffer size of 250bytes

                // reads the stream and store in the buf
                in.read(buf);
                // takes out the reply part of the buf removing null bytes

                String s = (new String(buf)).split("#")[0];

                // notifies observers
                setChanged();
                notifyObservers(s);


            } catch (IOException ex) {
                Logger.getLogger(InputSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
