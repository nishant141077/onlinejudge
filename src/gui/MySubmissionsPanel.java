/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import config.Configuration;
import entities.Submission;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import management.CoderManagement;

/**
 *
 * @author nishant
 */
public class MySubmissionsPanel extends JPanel {
    String handle;
    String code;
    JLayeredPane layeredPane;
    JScrollPane scrollPane;
    int height;
    JLabel refreshLabel;
    JLabel closeLabel;
    JPanel thisPanel;
    List<Submission> mySubmissionsList;
    JTabbedPane tabbedPane;
    JLabel submissions[][];
    JLabel headingLabel;
    int submissionsCount;
    
    public MySubmissionsPanel() {
    }
    public MySubmissionsPanel(String handle, String code) {
        scrollPane = new JScrollPane();
        this.handle = handle;
        this.code = code;
        
        //set layout
        javax.swing.GroupLayout mySubmissionsPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(mySubmissionsPanelLayout);
        mySubmissionsPanelLayout.setHorizontalGroup(
            mySubmissionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        mySubmissionsPanelLayout.setVerticalGroup(
            mySubmissionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
    }

    void initComponents(List<Submission> mySubmissionsList, JTabbedPane tabbedPane, MySubmissionsPanel mySubmissionsPanel) {
        this.mySubmissionsList = mySubmissionsList;
        thisPanel = mySubmissionsPanel;
        this.tabbedPane = tabbedPane;
        submissionsCount = 0;
        
        layeredPane = new JLayeredPane();
        //set height of layered pane according to number of submissions to show
        height = getLayeredPaneHeight(mySubmissionsList.size());
        layeredPane.setPreferredSize(new Dimension(1280, height));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(layeredPane);

        refreshLabel = new JLabel();
        refreshLabel.setBounds(1172, 5, 40, 40);
        refreshLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/refresh.png"))); // NOI18N
        refreshLabelActionPerformed();
        layeredPane.add(refreshLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        closeLabel = new JLabel(new ImageIcon(getClass().getResource(Configuration.CLOSE_ICON)));
        closeLabel.setBounds(1217, 5, 40, 40);
        closeLabelActionPerformed();
        layeredPane.add(closeLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        headingLabel = new JLabel(handle + "'s submissions for " + code);
        headingLabel.setBounds(231, 5, 800, 55);
        headingLabel.setOpaque(true);
        headingLabel.setFont(new Font("Ubuntu", 1, 28));
        headingLabel.setBackground(Configuration.LBROWN);
        headingLabel.setBorder(BorderFactory.createEtchedBorder());
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(headingLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        submissions = new JLabel[100][6];
        for(int i = 0;i<100;i++)
            for(int j = 0;j<6;j++)
                submissions[i][j] = new JLabel();
        
        initHeadingLabels();
        displaySubmissions();
    }

    private int getLayeredPaneHeight(int size) {
        return 580;  //set a formula anyway
    }

    private void closeLabelActionPerformed() {
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                tabbedPane.remove(thisPanel);
            }
        });
    }

    private void initHeadingLabels() {
        //sid
        submissions[0][0].setBounds(5, 70, 100, 30);
        submissions[0][0].setText("SID");
        
        //dateTime
        submissions[0][1].setBounds(105, 70, 200, 30);
        submissions[0][1].setText("Date/Time");
        
        //handle
        submissions[0][2].setBounds(305, 70, 200, 30);
        submissions[0][2].setText("Handle");
        
        //submission status
        submissions[0][3].setBounds(505, 70, 552, 30);
        submissions[0][3].setText("Status");
        
        //time taken
        submissions[0][4].setBounds(1057, 70, 100, 30);
        submissions[0][4].setText("Time");
        
        //language
        submissions[0][5].setBounds(1157, 70, 100, 30);
        submissions[0][5].setText("Language");
        
        //common attributes
        for(int i = 0; i<6; i++) {
            submissions[0][i].setOpaque(true);
            submissions[0][i].setHorizontalAlignment(SwingConstants.CENTER);
            submissions[0][i].setFont(new Font("Ubuntu", 1, 18));
            submissions[0][i].setBackground(Configuration.LBROWN);
            submissions[0][i].setBorder(BorderFactory.createEtchedBorder());
            layeredPane.add(submissions[0][i], javax.swing.JLayeredPane.DEFAULT_LAYER);
        }
    }

    private void displaySubmissions() {
        int i = 0;
        for(Submission sub : mySubmissionsList) {
            i++;
            for(int j = 0;j<6;j++) {
                submissions[i][j].setBounds(submissions[i-1][j].getX(), 
                        submissions[i-1][j].getY() + submissions[i-1][j].getHeight(), 
                        submissions[i-1][j].getWidth(), submissions[i-1][j].getHeight());
                submissions[i][j].setOpaque(true);
                submissions[i][j].setBackground(Color.WHITE);
                submissions[i][j].setFont(new Font("Ubuntu", 1, 17));
                submissions[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                submissions[i][j].setBorder(BorderFactory.createEtchedBorder());
            }
            
            //insert contents
            submissions[i][0].setText(sub.sid);
            submissions[i][1].setText(sub.dateTime.substring(0, sub.dateTime.lastIndexOf('.')));
            submissions[i][2].setText(sub.handle);
            submissions[i][3].setText(sub.status);
            submissions[i][3].setIcon(new ImageIcon(getClass().getResource(getStatusIconPath(sub.status))));
            if(sub.status.equals("Time Limit Exceeded") || sub.status.equals("Compilation Error")) {
                submissions[i][4].setText("-");
            }
            else submissions[i][4].setText(getRoundedTime(sub.time));
            submissions[i][5].setText(sub.language);
            
            if(i > submissionsCount) {
                for(int j = 0;j<6;j++) {
                    layeredPane.add(submissions[i][j], javax.swing.JLayeredPane.DEFAULT_LAYER);
                }
            }
        }
        submissionsCount = mySubmissionsList.size();
    }

    private String getStatusIconPath(String status) {
        if(status.equals("Accepted")) {
            return "/resources/ac30.png";
        }
        else if(status.equals("Compilation Error")) {
            return "/resources/cte30.png";
        }
        else if(status.equals("Wrong Answer")) {
            return "/resources/wa30.png";
        }
        else if(status.equals("Runtime Error")) {
            return "/resources/rte30.png";
        }
        else return "/resources/tle30.png";
    }

    private String getRoundedTime(double time) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(time);
    }

    private void refreshLabelActionPerformed() {
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                try {
                    mySubmissionsList = new CoderManagement().getMySubmissionsList(handle, code);
                    displaySubmissions();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(layeredPane, "Client : " + ex.getMessage());
                }
            }
        });
    }
}
