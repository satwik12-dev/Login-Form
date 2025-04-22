package LoginForm;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginForm extends JFrame {
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private JCheckBox showPasswordCheckBox;
    private JCheckBox rememberMeCheckBox;
    private HashMap<String, String> userDatabase;

    public LoginForm() {
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize user database
        userDatabase = new HashMap<>();
        userDatabase.put("admin", "password123");
        userDatabase.put("user", "userpass");

        // Components
        JLabel userLabel = new JLabel("Username:");
        userTextField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordField.setEchoChar('*');
        showPasswordCheckBox = new JCheckBox("Show Password");
        rememberMeCheckBox = new JCheckBox("Remember Me");
        JButton loginButton = new JButton("Login");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        // Add components to frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        gbc.gridx = 1;
        add(userTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(showPasswordCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(rememberMeCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(loginButton, gbc);

        gbc.gridx = 1;
        add(clearButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(exitButton, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(messageLabel, gbc);

        // Add tooltips
        userTextField.setToolTipText("Enter your username");
        passwordField.setToolTipText("Enter your password");
        showPasswordCheckBox.setToolTipText("Check to show password");
        rememberMeCheckBox.setToolTipText("Check to remember your credentials");

        // Show password action
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        // Login button action
        loginButton.addActionListener(e -> handleLogin());

        // Clear button action
        clearButton.addActionListener(e -> clearForm());

        // Exit button action
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void handleLogin() {
        String username = userTextField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Fields cannot be empty!");
            messageLabel.setForeground(Color.ORANGE);
            return;
        }

        if (authenticate(username, password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setForeground(Color.GREEN);
            if (rememberMeCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Credentials remembered!");
            }
        } else {
            messageLabel.setText("Invalid credentials!");
            messageLabel.setForeground(Color.RED);
        }
    }

    private void clearForm() {
        userTextField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
        rememberMeCheckBox.setSelected(false);
        showPasswordCheckBox.setSelected(false);
        passwordField.setEchoChar('*');
    }

    private boolean authenticate(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}
