/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Coder;
import entities.Problem;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import socket.Communication;

/**
 *
 * @author nishant
 */
public class CoderManagement implements Serializable {
    public Coder getCoderDetails(String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 6;
        message.coder = new Coder(handle);
        
        Communication.send(message);

        Message reply = new Message();
        reply = Communication.receive();
        return reply.coder;
    }
    
    public List<Problem> getProblemsList() throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 7;
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.problemsList;
    }
}
