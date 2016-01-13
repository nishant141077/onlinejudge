/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author nishant
 */
public class Configuration {
    public static String SERVER_IP = "172.31.76.50";
    public static int PORT = 9900;
    public static Socket socket = null;
//    public static Socket bsocket = null;

    public static ObjectOutputStream oos = null;
    public static ObjectInputStream ois = null;
//    public static ObjectOutputStream boos = null;
//    public static ObjectInputStream bois = null;
    public static String handle;
    public static String password;
    
    /**************Some data as cache******************/
    public static String questions[] = new String[]{
        "What was your favorite place to visit as a child?",
        "Who is your favorite actor, musician, or artist?",
        "What is the last name of your favorite teacher?",
        "What was the name of your elementary school?"
    };
    
    public static String gender[] = new String[]{"Male", "Female"};
    
    /**************************************************/
}