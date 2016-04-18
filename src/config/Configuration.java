/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.crypto.SecretKey;
/**
 *
 * @author nishant
 */
public class Configuration {
    public static String SERVER_IP = "127.0.0.1";
    public static int PORT = 9900;
    public static Socket socket = null;
    
    public static ObjectOutputStream oos = null;
    public static ObjectInputStream ois = null;
    public static String handle;
    public static String password;
    public static boolean CAN_SUBMIT = true;
    
    /**************Some data as cache******************/
    public static String questions[] = new String[]{
        "What was your favorite place to visit as a child?",
        "Who is your favorite actor, musician, or artist?",
        "What is the last name of your favorite teacher?",
        "What was the name of your elementary school?"
    };
    
    public static byte[] encryptionKey = {'O', 'n', 'l', 'j', '2', '0', '1', '6'};
    public static SecretKey desKey = null;
    
    public static String gender[] = new String[]{"Male", "Female"};
    public static String pLevelImagePath[] = new String[]{
        "",
        "/resources/level_1.png",
        "/resources/level_2.png",
        "/resources/level_3.png",
        "/resources/level_4.png",
        "/resources/level_5.png",
        "/resources/level_6.png",
        "/resources/level_7.png",
        "/resources/level_8.png",
        "/resources/level_9.png",
        "/resources/level_10.png"
    };
    
    public static String CLOSE_ICON = "/resources/close.png";
    public static String REFRESH_ICON = "/resources/refresh.png";
    
    public static Color LGREEN = new Color(191, 241, 146);
    public static Color LBLUE = new Color(204, 232, 227);
    public static Color LBROWN = new Color(203, 203, 163);
    public static Color LLBROWN = new Color(225, 218, 174);
    public static Color COBALT = new Color(153, 171, 207);
    /**************************************************/
}