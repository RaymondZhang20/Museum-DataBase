package GUI;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFrame extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    JTextField username = new JTextField(15);
    JTextField password = new JPasswordField(15);
    private static final String USERNAME_RAYMOND = "ora_eiczxy20";
    private static final String PASSWORD_RAYMOND = "a25012923";
    JLabel usernameRemind = new JLabel("username is ora_<cwl>");
    JLabel passwordRemind = new JLabel("password is a<student ID>");
    private DatabaseConnectionHandler dbHandler;

    public ConnectFrame() {
        dbHandler = new DatabaseConnectionHandler();
        init();
        setTitle("Login");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void init() {
        usernameRemind.setVisible(false);
        usernameRemind.setForeground(Color.red);
        passwordRemind.setVisible(false);
        passwordRemind.setForeground(Color.red);
        username.setText(USERNAME_RAYMOND);
        password.setText(PASSWORD_RAYMOND);

        Box uBox = Box.createHorizontalBox();
        uBox.add(Box.createHorizontalStrut(80));
        uBox.add(new JLabel("Username"));
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(username);
        uBox.add(Box.createHorizontalStrut(80));

        Box pBox = Box.createHorizontalBox();
        pBox.add(Box.createHorizontalStrut(80));
        pBox.add(new JLabel("Password"));
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(password);
        pBox.add(Box.createHorizontalStrut(80));

        Box bBox = Box.createHorizontalBox();
        bBox.add(Box.createHorizontalStrut(80));
        bBox.add(initializeLoginButton());
        bBox.add(Box.createHorizontalStrut(70));
        bBox.add(initializeRemindButton());
        bBox.add(Box.createHorizontalStrut(80));

        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(bBox);
        vBox.add(usernameRemind);
        vBox.add(passwordRemind);
        vBox.add(Box.createVerticalStrut(50));
        add(vBox);
    }

    public JButton initializeRemindButton() {
        JButton button = new JButton("Remind");
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameRemind.setVisible(true);
                passwordRemind.setVisible(true);
            }
        });
        return button;
    }

    public JButton initializeLoginButton() {
        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(username.getText().trim(), String.valueOf(password.getText().trim()));
            }
        });
        return button;
    }

    public void login(String username, String password) {
        boolean connected = dbHandler.login(username, password);

        if (connected) {
            dispose();
            new MainFrame(dbHandler);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to connect the database");
        }
    }

    public static void main(String[] args) {
        new ConnectFrame();
    }
}

