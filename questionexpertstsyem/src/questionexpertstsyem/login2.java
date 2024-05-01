/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questionexpertstsyem;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class login2 extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;

    private static JFrame frame;
    private static java.util.List<String> patientSymptoms = new java.util.ArrayList<>();

    public login2() {
        initComponents();
        setLocationRelativeTo(null);
        setBackgroundImage(); // Set background image
    }

    private void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(255, 255, 153));
        jButton1.setFont(new java.awt.Font("Poppins Light", 1, 18));
        jButton1.setForeground(new java.awt.Color(255, 51, 102));
        jButton1.setText("Start Here");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(400, 370, 170, 40);

        jTextField1.setBackground(new java.awt.Color(255, 153, 153));
        jTextField1.setFont(new java.awt.Font("Poppins Light", 0, 18));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(null);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(400, 300, 170, 40);

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 20));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Enter Your Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(400, 250, 180, 60);

        jLabel1.setPreferredSize(new java.awt.Dimension(750, 500));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 750, 500);

        setSize(new java.awt.Dimension(750, 500));
        setLocationRelativeTo(null);
    }

    private void setBackgroundImage() {
        ImageIcon background = new ImageIcon(getClass().getResource("img0-01.png"));
        Image img = background.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(img));
}


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String username = jTextField1.getText();
        dashboard1 dash = new dashboard1(username);
        dash.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login2().setVisible(true);
            }
        });
    }
}
