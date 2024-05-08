package questionexpertstsyem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jpl7.*;

import java.util.ArrayList;
import java.util.List;

public class Questionexpertstsyem2 {

    private static JFrame frame;
    private static List<String> patientSymptoms1 = new ArrayList<>();
    private static String[] allSymptoms1 = {
        "One_on_One_Time", "Practical_Help", "Receiving_Gifts", "Physical_Touch", "Importance_of_Gifts", "Quality_Time_with_Family", "Words_of_Affirmation", "Affirming_Notes_and_Texts",
        "Kind_Words", "Actions_vs_Words", "Hugs", "Praise_vs_Criticism", "Shared_Activities", "Physical_Affection", "Gift_Preference"
    };
    private static int currentSymptomIndex = 0;

    public static void main(String[] args) {
        // Initialize Prolog
        Query q2 = new Query("consult", new Term[]{new Atom("expertsystem1.pl")});
        System.out.println("Prolog2 consult " + (q2.hasSolution() ? "succeeded." : "failed."));

        SwingUtilities.invokeLater(Questionexpertstsyem2::createAndShowGUI1);
    }

    public static void createAndShowGUI1() {
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
        showNextSymptom1(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void showNextSymptom1(JPanel panel) {
        if (currentSymptomIndex < allSymptoms1.length) {
            String symptom1 = allSymptoms1[currentSymptomIndex];
            String symptomDescription = querySymptomDescription1(symptom1);
            JLabel descriptionLabel = new JLabel("<html><font face='Poppins Semibold' color='black' size='6' >Hello, "  + "! </font></html>");

            descriptionLabel.setForeground(Color.WHITE);
            JLabel symptomLabel = new JLabel("<html><font face='Poppins Medium' color='black' size='15'>" + symptom1 + "</font></html>");

            // Set text color and font style for "Do you have"
            symptomLabel.setForeground(Color.white);
            Font labelFont = symptomLabel.getFont();
            //symptomLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 22));

            JLabel symptomDescriptionLabel = new JLabel("<html><font face='Poppins' color='gray' size='9'>" + symptomDescription + "</font></html>");
            symptomDescriptionLabel.setForeground(Color.WHITE);

            JButton yesButton = new JButton("Yes");
            yesButton.setBackground(new java.awt.Color(255, 255, 153));
            yesButton.setFont(new java.awt.Font("Poppins Light", 1, 18)); 
            yesButton.setForeground(new java.awt.Color(255, 51, 102));

            JButton noButton = new JButton("No");
            noButton.setBackground(new java.awt.Color(255, 255, 153));
            noButton.setFont(new java.awt.Font("Poppins Light", 1, 18)); 
            noButton.setForeground(new java.awt.Color(255, 51, 102));

            yesButton.addActionListener(e -> {
                patientSymptoms1.add(symptom1);
                panel.removeAll();
                currentSymptomIndex++;
                showNextSymptom1(panel); // Show next symptom
                panel.revalidate();
                panel.repaint();
            });

            noButton.addActionListener(e -> {
                currentSymptomIndex++;
                showNextSymptom1(panel); // Show next symptom
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
            displayDiagnosis1(panel);
        }
    }

public static void displayDiagnosis1(JPanel panel) {
    identifyLoveLanguage(panel);
}

public static void identifyLoveLanguage(JPanel panel) {
    String query = "identify_love_language([" + String.join(",", patientSymptoms1) + "], Diagnosis).";
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
    diagnosisLabel.setForeground(Color.WHITE);
    panel.removeAll();
    panel.add(diagnosisLabel);
    panel.revalidate();
    panel.repaint();
}


    public static String querySymptomDescription1(String symptom) {
        Query query= new Query("symptom", new Term[]{new Atom(symptom), new Variable("Desc")});
        if (query.hasSolution()) {
            return query.oneSolution().get("Desc").toString();
        } else {
            return "No description available.";
        }
    }


}
