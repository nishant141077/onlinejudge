/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import config.Configuration;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JOptionPane;
import management.Message;
import management.ServerManagement;

/**
 *
 * @author nishant
 */
public class Connector {
    public boolean connectToServer() {
        try {
            InetAddress addr = InetAddress.getByName(Configuration.SERVER_IP);
            Socket socket = new Socket();
            //Socket bsocket = new Socket();
            
            socket.connect(new InetSocketAddress(addr, Configuration.PORT), 0);
            System.err.println("Connected to server");
            
            /*bsocket.connect(new InetSocketAddress(addr, Configuration.PORT), 0);
            System.err.println("Connected to server *broadcast*");
            */
            
            Configuration.socket = socket;
            //Configuration.bsocket = bsocket;
            
            ObjectOutputStream oos = new ObjectOutputStream(Configuration.socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(Configuration.socket.getInputStream());
            /*
            ObjectOutputStream boos = new ObjectOutputStream(Configuration.bsocket.getOutputStream());
            ObjectInputStream bois = new ObjectInputStream(Configuration.bsocket.getInputStream());     
            */
            Configuration.oos = oos;
            Configuration.ois = ois;
            //Configuration.boos = boos;
            //Configuration.bois = bois;           
            
            return true;
        }
        catch(Exception exception) {
            System.err.println(exception.getMessage());
            return false;
        }
    }
}
