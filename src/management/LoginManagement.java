/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import com.sun.corba.se.impl.protocol.giopmsgheaders.ReplyMessage;
import entities.User;
import java.io.IOException;
import java.io.Serializable;
import socket.Communication;

/**
 *
 * @author nishant
 */
public class LoginManagement implements Serializable {

    public boolean  checkValidity(String handle, String question, String answer) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 3;
        message.user = new User(handle, "", "", question, answer, 0, "");
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.status;
    }

    public boolean searchHandle(String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 1;
        message.user = new User(handle, "");
   
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.status;
    }

    public boolean checkAuthenticity(String handle, String password) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 2;
        message.user = new User(handle, password);
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        
        return reply.status;
    }

    public boolean resetPassword(String handle, String password) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 4;
        message.user = new User(handle, password);
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.status;
    }

    public boolean registerUser(User user) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 5;
        message.user = user;
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.status;
    }
    
}
