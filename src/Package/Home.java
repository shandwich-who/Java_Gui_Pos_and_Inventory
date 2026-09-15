package Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;

public class Home extends JFrame {

    private final Icon icon_pos = new ImageIcon(Path.of("images","pos.png").toString());
    private final JButton btn_pos = new JButton(icon_pos);
    private final Icon icon_inventory = new ImageIcon(Path.of("images","inventory.png").toString());
    private final JButton btn_inventory = new JButton(icon_inventory);

    public Home() {
        JLabel pos_name = new JLabel("Point Of Sale");
        pos_name.setForeground(Color.BLACK);
        pos_name.setBounds(90, 80, 100, 100);
        pos_name.setFont(new Font("Arial", Font.BOLD, 15));
        add(pos_name);

        btn_pos.setBounds(90, 150, 100, 100);
        btn_pos.setFocusable(false);

        btn_pos.setBorder(BorderFactory.createLineBorder(Color.black));
        btn_pos.setBackground(Color.white);
        add(btn_pos);
        btn_pos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pos pos = new Pos();
                pos.extend_Pos();
            }
        });

        JLabel inventory_name = new JLabel("Inventory");
        inventory_name.setBounds(315, 80, 100, 100);
        inventory_name.setForeground(Color.BLACK);
        inventory_name.setFont(new Font("Arial", Font.BOLD, 15));
        add(inventory_name);

        btn_inventory.setBounds(300, 150, 100, 100);
        btn_inventory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_inventory.setFocusable(false);
        btn_inventory.setBackground(Color.white);
        add(btn_inventory);
        btn_inventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Inventory inventory = new Inventory();
                inventory.extend_Inventory();
            }
        });

    }

    public void extend_Home() {
        ImageIcon icon = new ImageIcon(Path.of("images","home_icon.png").toString());
        setIconImage(icon.getImage());
        setTitle("Home");

        getContentPane().setBackground(Color.decode("#FAEACB"));;

        setResizable(false);
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
