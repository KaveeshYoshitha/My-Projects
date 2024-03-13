//package MTB;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.ResultSet;
//
//
//public class add extends JFrame implements ActionListener {
//
//    public add() {
//        // JFrame
//        setTitle("add");
//        setSize(1500, 800);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//        getContentPane().setBackground(Color.BLACK);
//
//        // Create components
//        JLabel titleLabel = new JLabel("Admin");
//        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        titleLabel.setVerticalAlignment(JLabel.CENTER);
//        titleLabel.setForeground(Color.WHITE);
//        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
//        titleLabel.setFont(montserratFont);
//
//        JButton dashboardBtn = new JButton("Dashboard");
//        dashboardBtn.setPreferredSize(new Dimension(220, 60));
//        dashboardBtn.setForeground(Color.WHITE);
//        dashboardBtn.setBackground(new Color(33, 38, 45));
//        dashboardBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
//        dashboardBtn.setFocusable(false);
//        dashboardBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                performDashboard();
//            }
//        });
//
//
//        JButton addBtn = new JButton("Add");
//        addBtn.setPreferredSize(new Dimension(220, 60));
//        addBtn.setForeground(Color.WHITE);
//        addBtn.setBackground(new Color(33, 38, 45));
//        addBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
//        addBtn.setFocusable(false);
//
//
//        // Panel1
//        JPanel panel1 = new JPanel(new GridBagLayout()) {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                setBackground(new Color(0, 0, 0));
//            }
//        };
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0, 10, 80, 30); // Padding at the bottom
//        panel1.add(titleLabel, gbc);
//
//        gbc.gridy = 1;
//        panel1.add(dashboardBtn, gbc);
//
//        gbc.gridy = 2;
//        panel1.add(addBtn, gbc);
//
////        JPanel panel2 = new JPanel(new GridLayout(2, 3, 20, 20));
//
//        // Adding movie posters  to the center panel
////
//
//        }
//
//        // Add components to the JFrame
//        add(panel1, BorderLayout.WEST);
////        add(new JScrollPane(panel2), BorderLayout.CENTER);
//        setVisible(true);
//    }
//
////    public void actionPerformed(ActionEvent e) {
////    }
//
//    private void performDashboard() {
//        dispose();
//        new dashboard();
//    }
//
//
//
//    public static void main(String[] args) {
//    new add();
//}


package MTB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add extends JFrame {

    public Add() {
        setTitle("MovieHub");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        ImageIcon logo = new ImageIcon("src/MTB/logo.png");
        setIconImage(logo.getImage());

        JLabel titleLabel = new JLabel("Admin");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
        titleLabel.setFont(montserratFont);

        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setPreferredSize(new Dimension(220, 60));
        dashboardBtn.setForeground(Color.WHITE);
        dashboardBtn.setBackground(new Color(33, 38, 45));
        dashboardBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        dashboardBtn.setFocusable(false);
        dashboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDashboard();
            }
        });

        JButton addBtn = new JButton("Add");
        addBtn.setPreferredSize(new Dimension(220, 60));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(new Color(33, 38, 45));
        addBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAdd();
            }
        });

        JButton viewBtn = new JButton("View");
        viewBtn.setPreferredSize(new Dimension(220, 60));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setBackground(new Color(33, 38, 45));
        viewBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        viewBtn.setFocusable(false);
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performView();
            }
        });

        JButton movieBtn = new JButton("Movie");
        movieBtn.setPreferredSize(new Dimension(220, 60));
        movieBtn.setForeground(Color.WHITE);
        movieBtn.setBackground(new Color(33, 38, 45));
        movieBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        movieBtn.setFocusable(false);
        movieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perfomAddMovie();
            }
        });

        JButton hallBtn = new JButton("Hall");
        hallBtn.setPreferredSize(new Dimension(220, 60));
        hallBtn.setForeground(Color.WHITE);
        hallBtn.setBackground(new Color(33, 38, 45));
        hallBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        hallBtn.setFocusable(false);
        hallBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAddHall();
            }
        });

        JPanel panel1 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 0, 0));
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 80, 30);
        panel1.add(titleLabel, gbc);

        gbc.gridy = 1;
        panel1.add(dashboardBtn, gbc);

        gbc.gridy = 2;
        panel1.add(addBtn, gbc);

        gbc.gridy = 3;
        panel1.add(viewBtn, gbc);

        JPanel panel2 = new JPanel(new GridLayout(2, 1, 20, 20)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 0, 0));
            }};

        panel2.add(movieBtn);
        panel2.add(hallBtn);



        add(panel1, BorderLayout.WEST);
        add(new JScrollPane(panel2), BorderLayout.CENTER);
        setVisible(true);
    }

    private void performDashboard() {
        dispose();
        new dashboard();
    }

    private void performAdd() {
        dispose();
        new Add();
    }

    private void performView() {
        dispose();
        new view();
    }

    private void perfomAddMovie() {
        dispose();
        new AddMovie();
    }

    private void performAddHall() {
        dispose();
        new AddHall();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Add::new);
    }
}
