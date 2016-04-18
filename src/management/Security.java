/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import com.sun.mail.util.BASE64EncoderStream;
import config.Configuration;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.JOptionPane;

/**
 *
 * @author nishant
 */
public class Security {
    static Cipher cipher;
    
    public static String getEncryptedPassword(String password) {
        try {   
            cipher = Cipher.getInstance("DES");
            if(Configuration.desKey == null) {
                System.out.println("key is null");
                Configuration.desKey = KeyGenerator.getInstance("DES").generateKey();
            }
            cipher.init(Cipher.ENCRYPT_MODE, Configuration.desKey);
            //encode string to byte[]
            byte[] utf8 = password.getBytes("UTF8");
            //encrypt
            byte[] encryptedPassword = cipher.doFinal(utf8);
            //encode to base64
            encryptedPassword = BASE64EncoderStream.encode(encryptedPassword);
            return new String(encryptedPassword);
        } catch(Exception exception) {
            JOptionPane.showMessageDialog(null, "Client : " + exception.getMessage());
        }
        return "";
    }
    
}
