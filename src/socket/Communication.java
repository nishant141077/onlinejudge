/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import config.Configuration;
import java.io.IOException;
import java.io.Serializable;
import management.Message;

/**
 *
 * @author nishant
 */
public class Communication implements Serializable {

    public static void send(Message message) throws IOException {
        Configuration.oos.writeObject(message);
        Configuration.oos.flush();
        System.err.println("Message passed");
    }

    public static Message receive() throws IOException, ClassNotFoundException {
        Message reply = new Message();
        reply = (Message) Configuration.ois.readObject();
        System.err.println("Reply received");
        return reply;
    }
}
