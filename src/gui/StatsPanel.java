/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import config.Configuration;
import entities.ProblemStats;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import management.CoderManagement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author nishant
 */
public class StatsPanel extends JPanel {
    private JLayeredPane layeredPane;
    private JPanel thisPanel;
    private ProblemStats problemStats;
    private JLabel backgroundLabel;
    private JLabel nameLabel;
    private JLabel closeLabel;
    private JLabel refreshLabel;
    private JTabbedPane tabbedPane;
    private JLabel solvedByLabel;
    private JLabel attemptedByLabel;
    private JLabel submissionsLabel;
    private JLabel acLabel;
    private JLabel cteLabel;
    private JLabel waLabel;
    private JLabel rteLabel;
    private JLabel tleLabel;
    private JLabel solvedByValueLabel;
    private JLabel attemptedByValueLabel;
    private JLabel submissionsValueLabel;
    private JLabel acValueLabel;
    private JLabel cteValueLabel;
    private JLabel waValueLabel;
    private JLabel rteValueLabel;
    private JLabel tleValueLabel;
    private JPanel pieChartPanel;
    private ChartPanel chartPanel;
    
    public StatsPanel() {
        layeredPane = new JLayeredPane();
        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
    }

    void initComponents(ProblemStats problemStats, JTabbedPane tabbedPane, StatsPanel thisPanel) {
        this.thisPanel = thisPanel;
        this.problemStats = problemStats;
        this.tabbedPane = tabbedPane;
        
        nameLabel = new JLabel();
        nameLabel.setBounds(240, 5, 800, 55);
        nameLabel.setFont(new Font("Ubuntu", 1, 28));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBorder(BorderFactory.createEtchedBorder());
        nameLabel.setBackground(Configuration.LBROWN);
        nameLabel.setText("Statistics - " + problemStats.name);
        layeredPane.add(nameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        closeLabel = new JLabel(new ImageIcon(getClass().getResource(Configuration.CLOSE_ICON)));
        closeLabel.setBounds(1227, 5, 40, 40);
        closeLabelActionPerformed();
        layeredPane.add(closeLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        refreshLabel = new JLabel(new ImageIcon(getClass().getResource(Configuration.REFRESH_ICON)));
        refreshLabel.setBounds(1182, 5, 40, 40);
        refreshLabelActionPerformed();
        layeredPane.add(refreshLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        /*********************Heading Labels***********************************/
        
        initHeadingLabels(solvedByLabel, "Solved By", 160, 65, 120, 40);
        
        initHeadingLabels(attemptedByLabel, "Attempts By", 280, 65, 120, 40);
        
        initHeadingLabels(submissionsLabel, "Submissions", 400, 65, 120, 40);
        
        initHeadingLabels(acLabel, "AC", 520, 65, 120, 40);
        
        initHeadingLabels(cteLabel, "CTE", 640, 65, 120, 40);
        
        initHeadingLabels(waLabel, "WA", 760, 65, 120, 40);
        
        initHeadingLabels(rteLabel, "RTE", 880, 65, 120, 40);
        
        initHeadingLabels(tleLabel, "TLE", 1000, 65, 120, 40);
        
        /**********************************************************************/
        
        /*******************Value Labels***************************************/
        
        solvedByValueLabel = initValueLabels(solvedByValueLabel, problemStats.solvedBy, 160, 105, 120, 40);
        
        attemptedByValueLabel = initValueLabels(attemptedByValueLabel, problemStats.attemptedBy, 280, 105, 120, 40);
        
        submissionsValueLabel = initValueLabels(submissionsValueLabel, problemStats.submissions, 400, 105, 120, 40);
        
        acValueLabel = initValueLabels(acValueLabel, problemStats.accepted, 520, 105, 120, 40);
        
        cteValueLabel = initValueLabels(cteValueLabel, problemStats.compilationErrors, 640, 105, 120, 40);
        
        waValueLabel = initValueLabels(waValueLabel, problemStats.wrongAnswers, 760, 105, 120, 40);
        
        rteValueLabel = initValueLabels(rteValueLabel, problemStats.runtimeErrors, 880, 105, 120, 40);
        
        tleValueLabel = initValueLabels(tleValueLabel, problemStats.timeLimitExceeds, 1000, 105, 120, 40);
        
        /**********************************************************************/

        pieChartPanel = new JPanel();
        pieChartPanel.setLayout(new BorderLayout());
        pieChartPanel.setBounds(428, 150, 424, 424);
        pieChartPanel.setOpaque(true);
        layeredPane.add(pieChartPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        makePieChart();
        
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 1280, 580);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBorder(BorderFactory.createEtchedBorder());
        backgroundLabel.setBackground(Color.WHITE);
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

    private void initHeadingLabels(JLabel label, String text, int x, int y, int width, int height) {
        label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Ubuntu", 1, 17));
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setBackground(Configuration.LGREEN);
        layeredPane.add(label, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }

    private JLabel initValueLabels(JLabel label, int value, int x, int y, int width, int height) {
        label = new JLabel(value + "");
        label.setBounds(x, y, width, height);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Ubuntu", 1, 17));
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setBackground(Configuration.LLBROWN);
        layeredPane.add(label, javax.swing.JLayeredPane.DEFAULT_LAYER);
        return label;
    }

    private void makePieChart() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("WA", new Integer(problemStats.wrongAnswers));
        pieDataset.setValue("CTE", new Integer(problemStats.compilationErrors));
        pieDataset.setValue("AC", new Integer(problemStats.accepted));
        pieDataset.setValue("TLE", new Integer(problemStats.timeLimitExceeds));
        pieDataset.setValue("RTE", new Integer(problemStats.runtimeErrors));
        JFreeChart chart = ChartFactory.createPieChart("", pieDataset, true, true, true);
        chartPanel = new ChartPanel(chart);
        pieChartPanel.add(chartPanel, BorderLayout.CENTER);
        pieChartPanel.validate();
    }

    private void refreshLabelActionPerformed() {
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                try {
                    problemStats = new CoderManagement().getProblemStats(problemStats.code);
                    refreshComponents();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(layeredPane, "Client : " + ex.getMessage());
                }
                
                
            }

            private void refreshComponents() {
                nameLabel.setText("Statistics - " + problemStats.name);
                solvedByValueLabel.setText(problemStats.solvedBy + "");
                attemptedByValueLabel.setText(problemStats.attemptedBy + "");
                submissionsValueLabel.setText(problemStats.submissions + "");
                acValueLabel.setText(problemStats.accepted + "");
                cteValueLabel.setText(problemStats.compilationErrors + "");
                waValueLabel.setText(problemStats.wrongAnswers + "");
                rteValueLabel.setText(problemStats.runtimeErrors + "");
                tleValueLabel.setText(problemStats.timeLimitExceeds + "");
                pieChartPanel.remove(chartPanel);
                makePieChart();
            }
        });
    }
    
}
