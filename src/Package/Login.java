package Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private final JPanel panel = new JPanel();

    public Login() {
        JLabel label_user = new JLabel("Username");
        label_user.setForeground(Color.BLACK);
        label_user.setBounds(160, 70, 100, 30);
        label_user.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(label_user);

        JTextField tf_user = new JTextField();
        tf_user.setBounds(120, 100, 150, 30);
        tf_user.setHorizontalAlignment(JTextField.CENTER);
        tf_user.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(tf_user);

        JLabel label_pass = new JLabel("Password");
        label_pass.setForeground(Color.BLACK);
        label_pass.setBounds(160, 150, 100, 30);
        label_pass.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(label_pass);

        JPasswordField tf_pass = new JPasswordField();
        tf_pass.setBounds(120, 180, 150, 30);
        tf_pass.setHorizontalAlignment(JPasswordField.CENTER);
        tf_pass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tf_pass.setEchoChar('*');
        panel.add(tf_pass);

        JButton btn_login = new JButton("Login");
        btn_login.setBounds(80, 240, 100, 35);
        btn_login.setBorder(BorderFactory.createLineBorder(Color.decode("#2196F3")));
        btn_login.setFocusable(false);
        btn_login.setBackground(Color.decode("#2196F3"));
        btn_login.setForeground(Color.white);
        panel.add(btn_login);
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str_user = tf_user.getText();
                String str_pass = String.valueOf(tf_pass.getPassword());
                
                // TODO: Replace with database-driven authentication
                // SECURITY: Should validate against database with hashed passwords
                if (str_user.equals("admin") && str_pass.equals("admin")) {
                    JOptionPane.showMessageDialog(Login.this, "Welcome!, you are successfully login");
                    tf_user.setText("");
                    tf_pass.setText("");
                    dispose();
                    Home home = new Home();
                    home.extend_Home();
                } else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Invalid username or password. Please try again.");
                }
            }
        });

        JButton btn_cancel = new JButton("Cancel");
        btn_cancel.setBounds(210, 240, 100, 35);
        btn_cancel.setBorder(BorderFactory.createLineBorder(Color.decode("#2196F3")));
        btn_cancel.setFocusable(false);
        btn_cancel.setForeground(Color.decode("#2196F3"));
        btn_cancel.setBackground(Color.white);
        panel.add(btn_cancel);
        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialog = JOptionPane.showConfirmDialog(Login.this,
                        "Are you sure you want to cancel the Operation", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (dialog == JOptionPane.YES_OPTION) {
                    tf_user.setText("");
                    tf_pass.setText("");
                }
            }
        });
    }

    public void extend_Login() {
        ImageIcon icon = new ImageIcon("login_icon.png");
        setIconImage(icon.getImage());

        panel.setLayout(null);
        panel.setBackground(Color.white);
        add(panel);
        setTitle("Log in");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
