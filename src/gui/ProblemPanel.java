/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import config.Configuration;
import entities.DataStore;
import entities.ProblemDetails;
import entities.ProblemStats;
import entities.Submission;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import management.CacheManagement;
import management.CoderManagement;
import management.Message;

/**
 *
 * @author nishant
 */
public class ProblemPanel extends JPanel {

    private JLayeredPane layeredPane;
    private JLabel pCodeNameLabel;
    private JLabel levelLabel;
    private JScrollPane statementScrollPane;
    private JEditorPane statementArea;
    private JLabel backgroundLabel;
    private JLabel mySubmissionsLabel;
    private JLabel statisticsLabel;
    private JLabel submitLabel;
    private JLabel closeLabel;
    private JLabel refreshLabel;
    private JLabel detailsLabel;
    private JLabel authorLabel;
    private JLabel authorValueLabel;
    private JLabel timeLimitLabel;
    private JLabel timeLimitValueLabel;
    private JLabel sourceLimitLabel;
    private JLabel sourceLimitValueLabel;
    private JLabel memoryLimitLabel;
    private JLabel memoryLimitValueLabel;
    private JLabel tagLabel;
    private Box addTagBox;
    private JCheckBox addTags[];
    private JScrollPane addTagScrollPane;
    private JTextPane tagsArea;
    private JLabel addTagLabel;
    private JButton addTagButton;
    private JTabbedPane tabbedPane;
    private ProblemPanel thisPanel;
    private JScrollPane tagsScrollPane;
    private ProblemDetails problemDetails;
    private String handle;
    private int tagsCount;
    
    public ProblemPanel(String handle) {
        layeredPane = new JLayeredPane();
        this.handle = handle;
        //set layout
        javax.swing.GroupLayout problemPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(problemPanelLayout);
        problemPanelLayout.setHorizontalGroup(
            problemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
        );
        problemPanelLayout.setVerticalGroup(
            problemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        
    }

    void initComponents(ProblemPanel problemPanel, JTabbedPane tabbedPane, ProblemDetails problemDetails) throws BadLocationException {
        
        this.tabbedPane = tabbedPane;
        thisPanel = problemPanel;
        this.problemDetails = problemDetails;
        
        levelLabel = new JLabel();
        levelLabel.setBounds(5, 5, 90, 90);
        layeredPane.add(levelLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        levelLabel.setOpaque(true);
        levelLabel.setBackground(Color.WHITE);
        levelLabel.setIcon(new ImageIcon(getClass().getResource(Configuration.pLevelImagePath[problemDetails.difficulty])));
        
        pCodeNameLabel = new JLabel();
        pCodeNameLabel.setBounds(236, 5, 800, 55);
        pCodeNameLabel.setFont(new Font("Ubuntu", 1, 28));
        pCodeNameLabel.setOpaque(true);
        pCodeNameLabel.setBackground(Configuration.LBROWN);
        pCodeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pCodeNameLabel.setText(problemDetails.code + " - " + problemDetails.name);
        pCodeNameLabel.setBorder(new EtchedBorder());
        layeredPane.add(pCodeNameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        statementScrollPane = new JScrollPane();
        statementScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        statementScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        statementScrollPane.setBorder(null);
        
        statementArea = new JEditorPane();
        statementArea.setContentType("text/html");
        statementArea.setOpaque(true);
        statementArea.setEditable(false);
        statementArea.setFocusable(true);
        DefaultCaret defaultCaret = (DefaultCaret) statementArea.getCaret();
        defaultCaret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        statementArea.setFont(new Font("Ubuntu", 0, 16));
        statementArea.setBorder(BorderFactory.createEmptyBorder());
        statementArea.setText(problemDetails.statement);
        statementScrollPane.setViewportView(statementArea);
        statementScrollPane.setBounds(5, 100, 1000, 480);
        layeredPane.add(statementScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
    
        mySubmissionsLabel = new JLabel("My Submissions");
        mySubmissionsLabel.setBounds(331, 70, 200, 25);
        mySubmissionsLabel.setOpaque(true);
        mySubmissionsLabel.setBackground(Configuration.LGREEN);
        mySubmissionsLabel.setFont(new Font("Ubuntu", 1, 17));
        mySubmissionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mySubmissionsLabel.setBorder(BorderFactory.createEtchedBorder());
        mySubmissionsLabel.setIcon(new ImageIcon(getClass().getResource("/resources/mysubmissions.png")));
        mySubmissionsLabelActionPerformed();
        layeredPane.add(mySubmissionsLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        statisticsLabel = new JLabel("Statistics");
        statisticsLabel.setBounds(536, 70, 200, 25);
        statisticsLabel.setOpaque(true);
        statisticsLabel.setBackground(Configuration.LGREEN);
        statisticsLabel.setFont(new Font("Ubuntu", 1, 17));
        statisticsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statisticsLabel.setBorder(BorderFactory.createEtchedBorder());
        statisticsLabel.setIcon(new ImageIcon(getClass().getResource("/resources/statistics.png")));   
        statisticsLabelActionPerformed();
        layeredPane.add(statisticsLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        submitLabel = new JLabel("Submit");
        submitLabel.setBounds(741, 70, 200, 25);
        submitLabel.setOpaque(true);
        submitLabel.setBackground(Configuration.LGREEN);
        submitLabel.setFont(new Font("Ubuntu", 1, 17));
        submitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        submitLabel.setBorder(BorderFactory.createEtchedBorder());
        submitLabel.setIcon(new ImageIcon(getClass().getResource("/resources/submit.png")));
        submitLabelActionPerformed();
        layeredPane.add(submitLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        closeLabel = new JLabel(new ImageIcon(getClass().getResource(Configuration.CLOSE_ICON)));
        closeLabel.setBounds(1227, 5, 40, 40);
        closeLabelActionPerformed();
        layeredPane.add(closeLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        refreshLabel = new JLabel(new ImageIcon(getClass().getResource(Configuration.REFRESH_ICON)));
        refreshLabel.setBounds(1182, 5, 40, 40);
        refreshLabelActionPerformed();
        layeredPane.add(refreshLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        authorLabel = new JLabel("Author :");
        authorLabel.setBounds(1013, 103, 110, 20);
        authorLabel.setFont(new Font("Ubuntu", 0, 16));
        authorLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(authorLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        authorValueLabel = new JLabel(problemDetails.author);
        authorValueLabel.setBounds(1124, 103, 140, 20);
        authorValueLabel.setFont(new Font("Ubuntu", 0, 16));
        authorValueLabel.setForeground(Color.BLUE);
        authorValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        authorValueLabelActionPerformed();
        layeredPane.add(authorValueLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        timeLimitLabel = new JLabel("Time limit :");
        timeLimitLabel.setBounds(1013, 123, 110, 20);
        timeLimitLabel.setFont(new Font("Ubuntu", 0, 16));
        timeLimitLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(timeLimitLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        timeLimitValueLabel = new JLabel(problemDetails.timeLimit + "s");
        timeLimitValueLabel.setBounds(1124, 123, 140, 20);
        timeLimitValueLabel.setFont(new Font("Ubuntu", 0, 16));
        timeLimitValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(timeLimitValueLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        sourceLimitLabel = new JLabel("Source limit :");
        sourceLimitLabel.setBounds(1013, 143, 110, 20);
        sourceLimitLabel.setFont(new Font("Ubuntu", 0, 16));
        sourceLimitLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(sourceLimitLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        sourceLimitValueLabel = new JLabel(problemDetails.sourceLimit + "B");
        sourceLimitValueLabel.setBounds(1124, 143, 140, 20);
        sourceLimitValueLabel.setFont(new Font("Ubuntu", 0, 16));
        sourceLimitValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(sourceLimitValueLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        memoryLimitLabel = new JLabel("Memory limit :");
        memoryLimitLabel.setBounds(1013, 163, 110, 20);
        memoryLimitLabel.setFont(new Font("Ubuntu", 0, 16));
        memoryLimitLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(memoryLimitLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        memoryLimitValueLabel = new JLabel(problemDetails.memoryLimit + "MB");
        memoryLimitValueLabel.setBounds(1124, 163, 140, 20);
        memoryLimitValueLabel.setFont(new Font("Ubuntu", 0, 16));
        memoryLimitValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        layeredPane.add(memoryLimitValueLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        detailsLabel = new JLabel();
        detailsLabel.setOpaque(true);
        detailsLabel.setBackground(Configuration.LBLUE);
        detailsLabel.setBounds(1010, 100, 257, 86);
        detailsLabel.setBorder(BorderFactory.createEtchedBorder());
        layeredPane.add(detailsLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        tagLabel = new JLabel("Tags");
        tagLabel.setBounds(1011, 195, 50, 20);
        tagLabel.setFont(new Font("Ubuntu", 1, 16));
        tagLabel.setOpaque(true);
        tagLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tagLabel.setBorder(BorderFactory.createEtchedBorder());
        tagLabel.setBackground(Configuration.LBROWN);
        layeredPane.add(tagLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
   
        addTagLabel = new JLabel("Own Tags");
        addTagLabel.setBounds(1011, 325, 100, 20);
        addTagLabel.setFont(new Font("Ubuntu", 1, 16));
        addTagLabel.setOpaque(true);
        addTagLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addTagLabel.setBorder(BorderFactory.createEtchedBorder());
        addTagLabel.setBackground(Configuration.LBROWN);
        layeredPane.add(addTagLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        addTagBox = Box.createVerticalBox();
        addTags = new JCheckBox[500];
        fillTagBox();
        addTagScrollPane = new JScrollPane(addTagBox);
        addTagScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addTagScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        addTagScrollPane.setBounds(1010, 346, 257, 175);
        layeredPane.add(addTagScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        tagsArea = new JTextPane();
        tagsArea.setFont(new Font("Ubuntu", 0, 16));
        setProblemTags(problemDetails.problemTags);
        setCoderTags(problemDetails.coderTags, false);
        tagsArea.setOpaque(true);
        tagsArea.setBackground(Configuration.LBLUE);
        tagsScrollPane = new JScrollPane(tagsArea);
        tagsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tagsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        tagsScrollPane.setBounds(1010, 218, 257, 100);
        layeredPane.add(tagsScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        
        
        addTagButton = new JButton("Add Selected tags");
        addTagButton.setBounds(1011, 525, 257, 30);
        addTagButton.setFont(new Font("Ubuntu", 1, 18));
        addTagButtonActionPerformed();
        layeredPane.add(addTagButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 1280, 580);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.WHITE);
        backgroundLabel.setBorder(BorderFactory.createEtchedBorder());
        layeredPane.add(backgroundLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }

    private void closeLabelActionPerformed() {
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                tabbedPane.remove(thisPanel);
            }
        });
    }

    private void fillTagBox() {
        List<String> tags = DataStore.getTagsList();
        int i = 0;
        for(String tag : tags) {
            if(i < tagsCount) {
                addTags[i].setText(tag);
            }
            else {
                addTags[i] = new JCheckBox(tag);
                addTagBox.add(addTags[i]);
            }
            i++;
        }
        if(i < tagsCount) {
            for(int j = i;j<tagsCount;j++) {
                addTagBox.remove(addTags[j]);
            }
        }
        tagsCount = i;
    }

    private void setProblemTags(List<String> problemTags) throws BadLocationException {
        tagsArea.setText("");
        String text = "";
        StyledDocument styledDocument = tagsArea.getStyledDocument();
        Style style = tagsArea.addStyle("Blue Color Style", null);
        StyleConstants.setForeground(style, Color.BLUE);
        for(String tag : problemTags) {
            text = text + "#" + tag + " ";
        }
        styledDocument.insertString(styledDocument.getLength(), text, style);
    }

    private void setCoderTags(List<String> coderTags, boolean added) throws BadLocationException {
        String text = "";
        StyledDocument styledDocument = tagsArea.getStyledDocument();
        Style style = tagsArea.addStyle("Black Color Style", null);
        StyleConstants.setForeground(style, Color.BLACK);
        for(String tag : coderTags) {
            text = text + "#" + tag + " ";
        }
        styledDocument.insertString(styledDocument.getLength(), text, style);
        
        if(added) {
            disableAddedCoderTags(coderTags);
        }
        else {
            disableAllCoderTags(coderTags);
        }
    }

    private void addTagButtonActionPerformed() {
        addTagButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> selectedTagsList = new ArrayList<String>();
                for(int i = 0;i<tagsCount;i++) {
                    if(addTags[i].isSelected()) {
                        selectedTagsList.add(addTags[i].getText());
                    }
                }
                if(selectedTagsList.size() + problemDetails.coderTags.size() > 5) {
                    JOptionPane.showMessageDialog(layeredPane, "You cannot add more than 5 tags to a problem in total");
                } 
                else {
                    try {
                        new CoderManagement().addSelectedTags(handle, problemDetails.code, selectedTagsList);
                        setCoderTags(selectedTagsList, true);
                    } catch(Exception exception) {
                        JOptionPane.showMessageDialog(layeredPane, "Error in adding tags : " + exception.getMessage());
                    }
                }
            }
        });
    }

    private void disableAllCoderTags(List<String> coderTags) {
        for(int i = 0;i<tagsCount;i++) {
            if(addTags[i].isEnabled() == false)
                addTags[i].setEnabled(true);
            addTags[i].setSelected(false);
        }
        
        for(String tag : coderTags) {
            for(int i = 0;i<tagsCount;i++) {
                if(addTags[i].getText().equals(tag)) {
                    addTags[i].setEnabled(false);
                }
            }
        }
    }

    private void refreshLabelActionPerformed() {
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                try {
                    Message reply = new CoderManagement().getRefreshedProblemPage(problemDetails.code, handle);
                    refreshComponents(reply.problemDetails, reply.tagsList);
                } catch (Exception ex) {
                    Logger.getLogger(ProblemPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void refreshComponents(ProblemDetails problemDetails, List<String> tagsList) throws BadLocationException {
                pCodeNameLabel.setText(problemDetails.code + " - " + problemDetails.name);
                levelLabel.setIcon(new ImageIcon(getClass()
                        .getResource(Configuration.pLevelImagePath[problemDetails.difficulty])));
                statementArea.setText(problemDetails.statement);
                authorValueLabel.setText(problemDetails.author);
                timeLimitValueLabel.setText(problemDetails.timeLimit + "s");
                memoryLimitValueLabel.setText(problemDetails.memoryLimit + "MB");
                sourceLimitValueLabel.setText(problemDetails.sourceLimit + "B");
                DataStore.addTagsList(tagsList);
                fillTagBox();
                setProblemTags(problemDetails.problemTags);
                setCoderTags(problemDetails.coderTags, false);  
            }

            private void updateTagsInConfiguration(List<String> tagsList) {
                
            }
        });
    }

    private void authorValueLabelActionPerformed() {
        authorValueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                authorValueLabel.setFont(new Font("Ubuntu", 1, 16));
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                authorValueLabel.setFont(new Font("Ubuntu", 0, 16));
            }
        });
    }

    private void disableAddedCoderTags(List<String> coderTags) {
        for(String tag : coderTags) {
            for(int i = 0;i<tagsCount;i++) {
                if(addTags[i].getText().equals(tag)) {
                    addTags[i].setSelected(false);
                    addTags[i].setEnabled(false);
                }
            }
        }
    }

    private void statisticsLabelActionPerformed() {
        statisticsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                statisticsLabel.setBackground(Configuration.COBALT);
                statisticsLabel.setFont(new Font("Ubuntu", 1, 18));
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                statisticsLabel.setBackground(Configuration.LGREEN);
                statisticsLabel.setFont(new Font("Ubuntu", 1, 17));
            }
            
            @Override
            public void mouseReleased(MouseEvent evt) {
                String title = "Stats-" + problemDetails.code;
                for(int i = 0;i<tabbedPane.getTabCount();i++) {
                    if(tabbedPane.getTitleAt(i).equals(title)) {
                        tabbedPane.setSelectedIndex(i);
                        return ;
                    }
                }
                try {
                    ProblemStats problemStats = new CoderManagement().getProblemStats(problemDetails.code);
                    StatsPanel statsPanel = new StatsPanel();
                    tabbedPane.add(title, statsPanel);
                    statsPanel.initComponents(problemStats, tabbedPane, statsPanel);
                } catch(Exception exception) {
                    JOptionPane.showMessageDialog(layeredPane, "Client : " + exception.getMessage());
                }
            }
        });
    }

    private void submitLabelActionPerformed() {
        submitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                submitLabel.setBackground(Configuration.COBALT);
                submitLabel.setFont(new Font("Ubuntu", 1, 18));
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                submitLabel.setBackground(Configuration.LGREEN);
                submitLabel.setFont(new Font("Ubuntu", 1, 17));
            }
            
            @Override
            public void mouseReleased(MouseEvent evt) {
                SubmitFrame submitFrame = new SubmitFrame();
                submitFrame.initFrame(problemDetails, handle, submitFrame);
            }
        });
    }

    private void mySubmissionsLabelActionPerformed() {
        mySubmissionsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                mySubmissionsLabel.setBackground(Configuration.COBALT);
                mySubmissionsLabel.setFont(new Font("Ubuntu", 1, 18));
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                mySubmissionsLabel.setBackground(Configuration.LGREEN);
                mySubmissionsLabel.setFont(new Font("Ubuntu", 1, 17));
            }
            
            @Override
            public void mouseReleased(MouseEvent evt) {
                String title = "MySubs-" + problemDetails.code;
                for(int i = 0;i<tabbedPane.getTabCount();i++) {
                    if(tabbedPane.getTitleAt(i).equals(title)) {
                        tabbedPane.setSelectedIndex(i);
                        return ;
                    }
                }
                try {
                    List<Submission> mySubmissions = new CoderManagement()
                            .getMySubmissionsList(handle, problemDetails.code);
                    MySubmissionsPanel mySubmissionsPanel = new MySubmissionsPanel(handle, problemDetails.code);
                    tabbedPane.add(title, mySubmissionsPanel);
                    mySubmissionsPanel.initComponents(mySubmissions, tabbedPane, mySubmissionsPanel);
                } catch(Exception exception) {
                    JOptionPane.showMessageDialog(layeredPane, "Client : " + exception.getMessage());
                }
            }
        });
    }
}
