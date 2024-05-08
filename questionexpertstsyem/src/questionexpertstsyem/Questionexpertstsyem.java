/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package questionexpertstsyem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jpl7.*;

import java.util.ArrayList;
import java.util.List;

public class Questionexpertstsyem {

    private static JFrame frame;
    private static List<String> patientSymptoms = new ArrayList<>();
    private static String[] allSymptoms = {
        "Anger_Expression", "Conflict_Resolution", "Forgiveness", "Frustration_Management", "Awareness_and_Control", "Conflict_Avoidance", "Temperament", "Emotional_Regulation",
        "Post_Anger_Behavior", "Resolution_and_Reflection", "Response_Evaluation"
    };
    private static int currentSymptomIndex = 0;

    public static void main(String[] args) {
        // Initialize Prolog
        Query q1 = new Query("consult", new Term[]{new Atom("expertsystem.pl")});
        System.out.println("Prolog consult " + (q1.hasSolution() ? "succeeded." : "failed."));

        SwingUtilities.invokeLater(Questionexpertstsyem::createAndShowGUI);
    }

    public static void createAndShowGUI() {
    frame = new JFrame(" ");

    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(750, 500);

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Load the background image
            ImageIcon background = new ImageIcon("img-01.png");
            // Draw the image at the specified location
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    };
    panel.setLayout(new BorderLayout());
    frame.add(panel);
    showNextSymptom(panel);

    // Center the frame on the screen
    frame.setLocationRelativeTo(null);

    frame.setVisible(true);
}




public static void showNextSymptom(JPanel panel) {
    if (currentSymptomIndex < allSymptoms.length) {
        String symptom = allSymptoms[currentSymptomIndex];
        String symptomDescription = querySymptomDescription(symptom);
        JLabel descriptionLabel = new JLabel("<html><font face='Poppins Semibold' color='black' size='6' >Hello, "  + "! </font></html>");
        
        descriptionLabel.setForeground(Color.WHITE);
        JLabel symptomLabel = new JLabel("<html><font face='Poppins Medium' color='black' size='15'>" + symptom + "</font></html>");

        // Set text color and font style for "Do you have"
        symptomLabel.setForeground(Color.white);
        Font labelFont = symptomLabel.getFont();
        //symptomLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 22));

        JLabel symptomDescriptionLabel = new JLabel("<html><font face='Poppins' color='#be1e1e' size='9'>" + symptomDescription + "</font></html>");
        symptomDescriptionLabel.setForeground(Color.WHITE);
        
        
        

        JButton yesButton = new JButton();
        
        
        yesButton.setBackground(new java.awt.Color(190, 30, 30));
        yesButton.setFont(new java.awt.Font("Poppins Light", 1, 18)); 
        yesButton.setForeground(new java.awt.Color(255, 255, 153));
        yesButton.setText("Yes");


        JButton noButton = new JButton("No ");        
        noButton.setBackground(new java.awt.Color(190, 30, 30));
        noButton.setFont(new java.awt.Font("Poppins Light", 1, 18)); 
        noButton.setForeground(new java.awt.Color(255, 255, 153));
        noButton.setText("No");

        

        yesButton.addActionListener(e -> {
            patientSymptoms.add(symptom);
            panel.removeAll();
            currentSymptomIndex++;
            showNextSymptom(panel); // Show next symptom
            panel.revalidate();
            panel.repaint();
        });

        noButton.addActionListener(e -> {
            currentSymptomIndex++;
            showNextSymptom(panel); // Show next symptom
            panel.revalidate();
            panel.repaint();
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout
        panel.removeAll();
        panel.add(descriptionLabel);
        panel.add(symptomLabel);
        panel.add(symptomDescriptionLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(yesButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(noButton);
        panel.revalidate();
        panel.repaint();
    } else {
        // If all symptoms have been shown, display diagnosis
        displayDiagnosis(panel);
    }
}


    public static void displayDiagnosis(JPanel panel) {
        String query = "diagnose([" + String.join(",", patientSymptoms) + "], Diagnosis).";
        Query q2 = new Query(query);

        String diagnosis = "No matching diagnosis found.";
        if (q2.hasSolution()) {
            java.util.Map<String, Term> solution = q2.oneSolution();
            Term diagnosisTerm = solution.get("Diagnosis");
            if (diagnosisTerm != null) {
                diagnosis = diagnosisTerm.toString();
            }
        }

        JLabel diagnosisLabel = new JLabel("<html><font face='Poppins Medium' size='8' color='black' >Based on the characteristics <font face='Poppins Medium' size='8' color='purple' >" + "</font>" + ", "+ "<br><br><b><font face='Poppins Medium' size='5'>"+ diagnosis + "</html>");
        //diagnosisLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        diagnosisLabel.setForeground(Color.WHITE);
        panel.removeAll();
        panel.add(diagnosisLabel);
        panel.revalidate();
        panel.repaint();
    }

    public static String querySymptomDescription(String symptom) {
        Query query = new Query("symptom", new Term[]{new Atom(symptom), new Variable("Desc")});
        if (query.hasSolution()) {
            return query.oneSolution().get("Desc").toString();
        } else {
            return "No description available.";
        }
    }

    private static void setResizable(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void setMinimumSize(Dimension dimension) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}