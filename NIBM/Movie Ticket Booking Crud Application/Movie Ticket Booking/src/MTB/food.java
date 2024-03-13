import javax.swing.*;//package MTB;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class food extends JFrame implements ActionListener {
//
//    public food() {
//        // JFrame
//        setTitle("MovieHub");
//        setSize(1500, 800);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//        getContentPane().setBackground(Color.BLACK);
//        ImageIcon logo = new ImageIcon("src/MTB/logo.png");
//        setIconImage(logo.getImage());
//
//        // Create components
//        JLabel titleLabel = new JLabel("Foods and Beverages");
//        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        titleLabel.setVerticalAlignment(JLabel.CENTER);
//        titleLabel.setForeground(Color.WHITE);
//        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
//        titleLabel.setFont(montserratFont);
//
//        JButton nextBtn = new JButton("Next");
//        nextBtn.setPreferredSize(new Dimension(220, 60));
//        nextBtn.setForeground(Color.WHITE);
//        nextBtn.setBackground(new Color(33, 38, 45));
//        nextBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
//        nextBtn.setFocusable(false);
//        nextBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                performNext();
//            }
//        });
//
//
//        JButton backBtn = new JButton("Back");
//        backBtn.setPreferredSize(new Dimension(220, 60));
//        backBtn.setForeground(Color.WHITE);
//        backBtn.setBackground(new Color(33, 38, 45));
//        backBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
//        backBtn.setFocusable(false);
//        backBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                performBack();
//            }
//        });
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
//
//        gbc.gridy = 1;
//        panel1.add(nextBtn, gbc);
//
//        gbc.gridy = 2;
//        panel1.add(backBtn, gbc);
//
//        // Panel2 (Center Panel) with GridLayout for movie posters (cards)
//        JPanel panel2 = new JPanel(new GridLayout(2, 3, 20, 20)); // Change the grid dimensions as needed
//
//        // Adding movie posters (cards) to the center panel
//        for (int i = 1; i <= 6; i++) {
//            ImageIcon moviePoster = new ImageIcon("C:\\Users\\MSI\\OneDrive\\Desktop\\MTB Crud Application\\Assest\\images\\1.jpg" + i + ".jpg");
//            JButton movieButton = new JButton("Food " + i);
//            movieButton.setForeground(Color.WHITE);
//            movieButton.setBackground(new Color(33, 38, 45));
//            movieButton.setFont(new Font("Montserrat", Font.BOLD, 30));
//            movieButton.setFocusable(false);
//            movieButton.addActionListener(this);
//
//            JPanel cardPanel = new JPanel(new BorderLayout());
//            cardPanel.add(new JLabel(moviePoster), BorderLayout.CENTER);
//            cardPanel.add(movieButton, BorderLayout.SOUTH);
//
//            panel2.add(cardPanel);
//        }
//
//        // Add components to the JFrame
//        add(panel1, BorderLayout.EAST);
//        add(panel2, BorderLayout.CENTER);
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        // Handle button clicks if needed
//    }
//
//
//    public void performBack() {
//        dispose();
//        new SeatBooking();
//    }
//
//    public void performNext() {
//        dispose();
//        new Payment();
//    }
//
//    public static void main(String[] args) {
//        food food = new food();
//    }
//}
