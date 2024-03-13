package MTB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.File;




public class AddMovie extends JFrame {

    JTextField movieNameField;
    JTextField movieIDField;
    JTextField moviePosterField;
    JTextField adminIDField;

    public AddMovie() {
        setTitle("MovieHub");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        ImageIcon logo = new ImageIcon("src/MTB/logo.png");
        setIconImage(logo.getImage());

        JLabel titleLabel = new JLabel("Add Movie");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
        titleLabel.setFont(montserratFont);

        JLabel movieNameLabel = new JLabel("Movie Name : ");
        movieNameLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        movieNameLabel.setForeground(Color.WHITE);
        movieNameField = new JTextField();
        movieNameField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        movieNameField.setPreferredSize(new Dimension(200, 30));
        movieNameField.setForeground(Color.BLACK);

        JLabel movieIDLabel = new JLabel("Movie Id : ");
        movieIDLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        movieIDLabel.setForeground(Color.WHITE);
        movieIDField = new JTextField();
        movieIDField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        movieIDField.setPreferredSize(new Dimension(200, 30));
        movieIDField.setForeground(Color.BLACK);

        JLabel moviePosterLabel = new JLabel("Movie Poster : ");
        moviePosterLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        moviePosterLabel.setForeground(Color.WHITE);
        moviePosterField = new JTextField();
        moviePosterField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        moviePosterField.setPreferredSize(new Dimension(200, 30));
        moviePosterField.setForeground(Color.BLACK);

        JButton choosePosterButton = new JButton("Choose Poster");
        choosePosterButton.setPreferredSize(new Dimension(220, 60));
        choosePosterButton.setForeground(Color.WHITE);
        choosePosterButton.setBackground(new Color(33, 38, 45));
        choosePosterButton.setFont(new Font("Montserrat", Font.BOLD, 30));
        choosePosterButton.setFocusable(false);

        choosePosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    moviePosterField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        JLabel adminIDLabel = new JLabel("Admin ID : ");
        adminIDLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        adminIDLabel.setForeground(Color.WHITE);
        adminIDField = new JTextField();
        adminIDField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        adminIDField.setPreferredSize(new Dimension(200, 30));
        adminIDField.setForeground(Color.BLACK);

        JButton addMovieButton = new JButton("Add Movie");
        addMovieButton.setPreferredSize(new Dimension(220, 60));
        addMovieButton.setForeground(Color.WHITE);
        addMovieButton.setBackground(new Color(33, 38, 45));
        addMovieButton.setFont(new Font("Montserrat", Font.BOLD, 30));
        addMovieButton.setFocusable(false);

        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAddMovie();
            }
        });

        JButton backBtn = new JButton("Back");
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

        JPanel panel1 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 0, 0));
            }
        };
        JPanel panel2 = new JPanel(new BorderLayout()) {
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
        panel1.add(movieNameLabel, gbc);
        gbc.gridy = 2;
        panel1.add(movieIDLabel, gbc);
        gbc.gridy = 3;
        panel1.add(moviePosterLabel, gbc);
        gbc.gridx = 2;
        panel1.add(choosePosterButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel1.add(adminIDLabel, gbc);



        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(movieNameField, gbc);
        gbc.gridy = 2;
        panel1.add(movieIDField, gbc);
        gbc.gridy = 3;
        panel1.add(moviePosterField, gbc);
        gbc.gridy = 4;
        panel1.add(adminIDField, gbc);


        panel2.add(addMovieButton, BorderLayout.EAST);
        panel2.add(backBtn, BorderLayout.WEST);


        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setVisible(true);
    }



    private void performBack() {
        dispose();
        new Add();
    }

    private void performAddMovie() {
        String movieName = movieNameField.getText().trim();
        String movieID = movieIDField.getText().trim();
        String moviePoster = moviePosterField.getText().trim();
        String adminID = adminIDField.getText().trim();

        if (movieName.isEmpty() || movieID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Movie Name and Movie ID cannot be empty");
            return;
        }
        if (addMovieToDB(movieName, movieID, moviePoster, adminID)) {
            JOptionPane.showMessageDialog(this, "Movie Added Successfully");
            dispose();
            new Add();
        } else {
            JOptionPane.showMessageDialog(this, "Adding failed for: " + movieName);
        }


    }

    private boolean addMovieToDB(String movieName, String movieID, String moviePoster, String adminID) {
        dbConnection conObj1 = new dbConnection();
        try (Connection dbConnector1 = conObj1.getConnection();
             PreparedStatement preparedStatement = dbConnector1.prepareStatement("INSERT INTO cinema.movie (mID, mName, moviePoster, AID) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, movieID);
            preparedStatement.setString(2, movieName);
            preparedStatement.setString(3, moviePoster);
            preparedStatement.setString(4, adminID);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Movie Data inserted successfully.");
                return true;
            } else {
                System.out.println("Movie Data insertion failed.");
                return false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        } finally {
            conObj1.closeConnection();
        }
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddMovie::new);
    }
}
