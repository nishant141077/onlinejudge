/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import config.Configuration;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import socket.Communication;

/**
 *
 * @author nishant
 */
public class LoginManagement implements Serializable {

    public Response checkValidity(String handle, String question, String answer) {
        return new Response();
    }

    public boolean searchHandle(String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 1;
        message.handle = handle;
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.status;
    }

    public boolean checkAuthenticity(String handle, String password) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 2;
        message.handle = handle;
        message.password = password;
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        
        return reply.status;
    }
    
}
