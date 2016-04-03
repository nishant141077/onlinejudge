/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Coder;
import entities.Problem;
import entities.ProblemDetails;
import entities.ProblemStats;
import entities.Submission;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import socket.Communication;

/**
 *
 * @author nishant
 */
public class CoderManagement implements Serializable {
    public Coder getCoderDetails(String handle) throws IOException, ClassNotFoundException {
                System.out.println("fetching Coder details");

        Message message = new Message();
        message.code = 6;
        message.coder = new Coder(handle);
        
        Communication.send(message);

        Message reply = new Message();
        reply = Communication.receive();
        System.out.println("Coder details succesfully ");
        return reply.coder;
    }
    
    public List<Problem> getProblemsList() throws IOException, ClassNotFoundException {
                System.out.println("fetching problems");

        Message message = new Message();
        message.code = 7;
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
                System.out.println("fetched problems succesfully ");

        return reply.problemsList;
    }

    public ProblemDetails getProblemDetails(String problemCode, String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 8;
        message.problemDetails = new ProblemDetails(problemCode);
        message.coder = new Coder(handle);
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.problemDetails;
    }

    public List<String> getTagsList() throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 9;
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.tagsList;
    }

    public void addSelectedTags(String handle, String code, List<String> selectedTagsList) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 10;
        
        message.coder = new Coder(handle);
        message.problemDetails = new ProblemDetails(code);
        message.tagsList = selectedTagsList;

        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
    }

    public Message getRefreshedProblemPage(String code, String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 11;
        
        message.coder = new Coder(handle);
        message.problemDetails = new ProblemDetails(code);
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply;
    }

    public ProblemStats getProblemStats(String code) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 12;
        
        message.problemStats = new ProblemStats(code);
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.problemStats;
    }

    public Submission sendSubmission(Submission submission, ProblemDetails problemDetails) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 13;
        
        message.submission = submission;
        message.problemDetails = problemDetails;
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.submission;
    }

    public List<Problem> getSolvedProblemsList(String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 14;
        
        message.coder = new Coder(handle);
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.problemsList;
    }

    public List<Problem> getAttemptedUnsolvedProblemsList(String handle) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 15;
        
        message.coder = new Coder(handle);
        
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.problemsList;
    }

    public List<Submission> getMySubmissionsList(String handle, String code) throws IOException, ClassNotFoundException {
        Message message = new Message();
        message.code = 16;
        
        message.coder = new Coder(handle);
        message.problemDetails = new ProblemDetails(code);
        Communication.send(message);
        
        Message reply = new Message();
        reply = Communication.receive();
        return reply.mySubmissions;
    }
}
