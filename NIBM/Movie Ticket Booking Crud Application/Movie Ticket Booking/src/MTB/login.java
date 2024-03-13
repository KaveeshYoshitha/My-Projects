package MTB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;


import com.mysql.cj.jdbc.JdbcConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class login extends JFrame  {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/cinema";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";



    public login() {
        // JFrame
        setTitle("MovieHub");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        ImageIcon logo = new ImageIcon("src/MTB/logo.png");
        setIconImage(logo.getImage());

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
        titleLabel.setFont(montserratFont);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField();
        usernameField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setForeground(Color.BLACK);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setForeground(Color.BLACK);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(220, 60));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(33, 38, 45));
        loginButton.setFont(new Font("Montserrat", Font.BOLD, 30));
        loginButton.setFocusable(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });



        // Panel1
        JPanel panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0,0,0));
            }
        };
        panel1.setPreferredSize(new Dimension(1500, 100));
        panel1.add(titleLabel);

        JPanel panel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(13, 17, 23));
            }
        };
        panel2.setPreferredSize(new Dimension(1500, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel2.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel2.add(passwordField, gbc);

        JPanel panel3 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(13, 17, 23));
            }
        };
        panel3.setPreferredSize(new Dimension(1500, 150));
        panel3.add(loginButton);



        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);

        setVisible(true);
    }




    private void performLogin(){

        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();

        if (authenticateUser(username, new String(password))) {
            System.out.println("Login successful for: " + username);
            dispose();
            new dashboard();

        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Invalid credentials.");
            System.out.println("Login failed. Invalid credentials.");
        }

    }






    private boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM cinema.adminn WHERE AID = ? AND APassword = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login();
            }
        });

    }
}
