package MTB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeatBooking extends JFrame implements ActionListener {
    String movieName;
    private JTextField seatAmountField;
    private JTextField cusNameField;
    private JButton nextBtn;
    private JButton backBtn;

    public SeatBooking() {
        setTitle("MovieHub");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        ImageIcon logo = new ImageIcon("src/MTB/logo.png");
        setIconImage(logo.getImage());

        JLabel titleLabel = new JLabel("Grab Your Seat");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
        titleLabel.setFont(montserratFont);

        nextBtn = new JButton("Add");
        nextBtn.setPreferredSize(new Dimension(220, 60));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setBackground(new Color(33, 38, 45));
        nextBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        nextBtn.setFocusable(false);
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performNext();
            }
        });

        backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(220, 60));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(33, 38, 45));
        backBtn.setFont(new Font("Montserrat", Font.BOLD, 30));
        backBtn.setFocusable(false);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performBack();
            }
        });

        JLabel setAmountLabel = new JLabel("Enter the amount of seats you want to book : ");
        setAmountLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        setAmountLabel.setForeground(Color.WHITE);
        seatAmountField = new JTextField();
        seatAmountField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        seatAmountField.setPreferredSize(new Dimension(200, 30));
        seatAmountField.setForeground(Color.BLACK);

        JLabel cusNameLabel = new JLabel("Enter your name : ");
        cusNameLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        cusNameLabel.setForeground(Color.WHITE);
        cusNameField = new JTextField();
        cusNameField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        cusNameField.setPreferredSize(new Dimension(200, 30));
        cusNameField.setForeground(Color.BLACK);

        JPanel panel1 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 0, 0));
            }
        };
        JPanel panel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 0, 0));
            }
        };
        JPanel panel3 = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 0, 0));
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(cusNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(cusNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(setAmountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(seatAmountField, gbc);

        panel3.add(backBtn, BorderLayout.WEST);
        panel3.add(nextBtn, BorderLayout.EAST);

        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.NORTH);
        add(panel3, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void performNext() {

        String customerName = cusNameField.getText();
        String seatAmount = seatAmountField.getText();

        if (customerName.isEmpty() || seatAmount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and seat amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int seatQuantity;
        try {
            seatQuantity = Integer.parseInt(seatAmount);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid seat amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root")) {
            String customerQuery = "INSERT INTO customer (userName) VALUES (?)";
            try (PreparedStatement customerStatement = connection.prepareStatement(customerQuery)) {
                customerStatement.setString(1, customerName);
                customerStatement.executeUpdate();
            }

            String seatQuery = "INSERT INTO seat (sQuantity, userName) VALUES (?, ?)";
            try (PreparedStatement seatStatement = connection.prepareStatement(seatQuery)) {
                seatStatement.setInt(1, seatQuantity);
                seatStatement.setString(2, customerName);
                seatStatement.executeUpdate();
            }


            dispose();
//            new food();
            new Payment(customerName);


        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void performBack() {
        dispose();
        new dashboard();
    }



    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new SeatBooking());
    }
}
