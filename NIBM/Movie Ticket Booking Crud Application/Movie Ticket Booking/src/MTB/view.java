package MTB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Blob;
import java.sql.SQLException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;




public class view extends JFrame implements ActionListener {

    private DefaultTableModel model;
    private JTable table;

    public view() {
        setTitle("MovieHub");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        ImageIcon logo = new ImageIcon("src/MTB/logo.png");
        setIconImage(logo.getImage());

        JLabel titleLabel = new JLabel("View");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        Font montserratFont = new Font("Montserrat", Font.BOLD, 50);
        titleLabel.setFont(montserratFont);

        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(220, 60));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(33, 38, 45));
        updateButton.setFont(new Font("Montserrat", Font.BOLD, 30));
        updateButton.setFocusable(false);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performUpdate();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(220, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(33, 38, 45));
        deleteButton.setFont(new Font("Montserrat", Font.BOLD, 30));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDelete();
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

        String[] columns = {"Movie ID", "Movie Name", "Movie Poster", "Admin ID"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

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

        panel2.add(updateButton, BorderLayout.EAST);
        panel2.add(backBtn, BorderLayout.WEST);
        panel2.add(deleteButton, BorderLayout.CENTER);

        add(panel1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);

        loadDataFromDatabase();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private void performBack() {
        dispose();
        new dashboard();
    }





    private void loadDataFromDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");

            String query = "SELECT mID, mName, moviePoster, AID FROM cinema.movie";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            model.setRowCount(0);

            while (resultSet.next()) {
                String mID = resultSet.getString("mID");
                String mName = resultSet.getString("mName");

                Blob moviePosterBlob = resultSet.getBlob("moviePoster");
                byte[] moviePosterBytes = moviePosterBlob.getBytes(1, (int) moviePosterBlob.length());

                String moviePoster = new String(moviePosterBytes);

                String AID = resultSet.getString("AID");

                model.addRow(new Object[]{mID, mName, moviePoster, AID});
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void performDelete() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");

            String deleteQuery = "DELETE FROM cinema.movie WHERE mID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);

            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                String mID = model.getValueAt(selectedRow, 0).toString();

                deleteStatement.setString(1, mID);

                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Deleted successfully", "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Delete failed", "Delete Status", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete", "Delete Status", JOptionPane.WARNING_MESSAGE);
            }

            deleteStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    private void performUpdate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");

            String updateQuery = "UPDATE cinema.movie SET mName = ?, moviePoster = ? WHERE mID = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

            for (int row = 0; row < model.getRowCount(); row++) {
                String mID = model.getValueAt(row, 0).toString();
                String newName = model.getValueAt(row, 1).toString();

                if (!newName.equals(getOriginalNameFromDatabase(mID, connection))) {

                    ImageIcon moviePosterIcon = (ImageIcon) model.getValueAt(row, 2);
                    Blob moviePosterBlob = connection.createBlob();
                    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                        ImageIO.write((BufferedImage) moviePosterIcon.getImage(), "jpg", byteArrayOutputStream);
                        moviePosterBlob.setBytes(1, byteArrayOutputStream.toByteArray());
                    }

                    updateStatement.setString(1, newName);
                    updateStatement.setBlob(2, moviePosterBlob);
                    updateStatement.setString(3, mID);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Updated successfully", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Update failed", "Update Status", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            updateStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getOriginalNameFromDatabase(String mID, Connection connection) throws SQLException {
        String originalName = null;
        String query = "SELECT mName FROM cinema.movie WHERE mID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            originalName = resultSet.getString("mName");
        }

        resultSet.close();
        preparedStatement.close();

        return originalName;
    }




    public static void main(String[] args) {
        new view().setVisible(true);
    }
}
