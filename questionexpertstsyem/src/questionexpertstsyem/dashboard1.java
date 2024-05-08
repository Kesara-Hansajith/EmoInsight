package questionexpertstsyem;

import questionexpertstsyem.Questionexpertstsyem;
import questionexpertstsyem.Questionexpertstsyem2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jpl7.*;

import java.util.ArrayList;
import java.util.List;

public class dashboard1 extends JFrame {

    private JLabel userNameLabel;
    private JLabel helloLabel;
    private JButton button1;
    private JButton button2;
    private String username; // Add a field to store the username


    public dashboard1() {
        this.username = username; // Initialize the username field
        initComponents();
        setLocationRelativeTo(null);
    }

    public dashboard1(String username) {
    this.username = username; // Initialize the username field
    initComponents();
    setLocationRelativeTo(null);    }

    private void initComponents() {

        setTitle("Dashboard");
        setMinimumSize(new Dimension(750, 500));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        helloLabel = new JLabel("Hello");
        helloLabel.setForeground(Color.WHITE);
        helloLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 22));
        getContentPane().add(helloLabel);
        helloLabel.setBounds(80, 80, 70, 34);

        userNameLabel = new JLabel(username); // Set the text to the received username
        userNameLabel.setForeground(new Color(255, 204, 204));
        userNameLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 22));
        getContentPane().add(userNameLabel);
        userNameLabel.setBounds(140, 80, 300, 34);

        button1 = new JButton(" ");
        //button1.setBackground(new Color(204, 204, 0));
        // Load the background image
        ImageIcon backgroundImage1 = new ImageIcon("im1-01.png");

        // Set the background image to the button
        button1.setIcon(backgroundImage1);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
        // Perform action for button2
        Questionexpertstsyem2.createAndShowGUI1();
    }
        });
        getContentPane().add(button1);
        button1.setBounds(80, 160, 260, 260);

       button2 = new JButton("  ");
       button2.setBackground(Color.YELLOW);
       // Load the background image
       ImageIcon backgroundImage2 = new ImageIcon("ima-01.png");
       // Set the background image to the button
       button2.setIcon(backgroundImage2);

      button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        // Perform action for button2
        Questionexpertstsyem.createAndShowGUI();
    }
});


        getContentPane().add(button2);
        button2.setBounds(380, 160, 260, 260);

        getContentPane().setBackground(new Color(255, 51, 102));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                close();
            }
        });

        pack();
    }

    public void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            Query q1 = new Query("consult", new Term[]{new Atom("expertsystem.pl")});
            System.out.println("Prolog1 consult " + (q1.hasSolution() ? "succeeded." : "failed."));
            new dashboard1().setVisible(true);
            
            Query q2 = new Query("consult", new Term[]{new Atom("expertsystem1.pl")});
            System.out.println("Prolog2 consult " + (q2.hasSolution() ? "succeeded." : "failed."));
            new dashboard1().setVisible(true);
            
        }
    });
}



   
}