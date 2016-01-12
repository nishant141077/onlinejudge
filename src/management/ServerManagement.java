/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javax.swing.JOptionPane;

/**
 *
 * @author nishant
 */
public class ServerManagement {

    public Message processRequestFromServer(Message message) {
        
        JOptionPane.showMessageDialog(null, message.displayMessage);
        return new Message();
    }
    
}
