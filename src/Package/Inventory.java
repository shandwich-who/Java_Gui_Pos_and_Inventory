package Package;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class Inventory extends JFrame {

    private JTextField itemCode_tf;
    private JTextField itemDescription_tf;
    private JTextField price_tf;
    private JTextField size_tf;
    private JTextField stocks_tf;
    private JTextField reOrderPoint_tf;
    private JTextField item_text_field;
    private final JButton stock_in_btn = new JButton("Stock in");
    private final JButton add_btn = new JButton("Add");
    private final JButton edit_btn = new JButton("Edit");
    private final JButton delete_btn = new JButton("Delete");
    private final JButton save_btn = new JButton("Save");
    private final JButton cancel_btn = new JButton("Cancel");

    private final JPanel panel = new JPanel();
    private final Object[] column = { "Item Code", "Item Description", "Price", "Size", "Stocks", "Re-Order Point",
            "Remarks" };
    private final DefaultTableModel tableModel = new DefaultTableModel(null, column) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(tableModel);
    private final JScrollPane scroll_pane = new JScrollPane(table);

    private void label_area() {
        Font font1 = new Font("Arial", Font.BOLD, 15);

        JLabel itemCode_label = new JLabel(column[0].toString());
        itemCode_label.setBounds(60, 50, 160, 30);
        itemCode_label.setFont(font1);
        add(itemCode_label);

        JLabel itemDescription_label = new JLabel(column[1].toString());
        itemDescription_label.setBounds(60, 100, 160, 30);
        itemDescription_label.setFont(font1);
        add(itemDescription_label);

        JLabel price_label = new JLabel(column[2].toString());
        price_label.setBounds(60, 150, 160, 30);
        price_label.setFont(font1);
        add(price_label);

        JLabel size_label = new JLabel(column[3].toString());
        size_label.setBounds(60, 200, 160, 30);
        size_label.setFont(font1);
        add(size_label);

        JLabel stocks_label = new JLabel(column[4].toString());
        stocks_label.setBounds(60, 250, 160, 30);
        stocks_label.setFont(font1);
        add(stocks_label);

        JLabel reOrderPoint_label = new JLabel(column[5].toString());
        reOrderPoint_label.setBounds(60, 300, 160, 30);
        reOrderPoint_label.setFont(font1);
        add(reOrderPoint_label);

        JLabel search_item = new JLabel("Search Item:");
        search_item.setBounds(490, 10, 100, 30);
        search_item.setFont(font1);
        add(search_item);
    }

    private void tf_range() {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        itemCode_tf = new JTextField();
        itemCode_tf.setBounds(250, 50, 150, 30);
        itemCode_tf.setHorizontalAlignment(JTextField.CENTER);
        itemCode_tf.setBorder(border);
        itemCode_tf.setEnabled(false);
        add(itemCode_tf);

        itemDescription_tf = new JTextField();
        itemDescription_tf.setBounds(250, 100, 150, 30);
        itemDescription_tf.setHorizontalAlignment(JTextField.CENTER);
        itemDescription_tf.setBorder(border);
        add(itemDescription_tf);

        price_tf = new JTextField();
        price_tf.setBounds(250, 150, 150, 30);
        price_tf.setHorizontalAlignment(JTextField.CENTER);
        price_tf.setBorder(border);
        add(price_tf);

        size_tf = new JTextField();
        size_tf.setBounds(250, 200, 150, 30);
        size_tf.setHorizontalAlignment(JTextField.CENTER);
        size_tf.setBorder(border);
        add(size_tf);

        stocks_tf = new JTextField();
        stocks_tf.setBounds(250, 250, 150, 30);
        stocks_tf.setHorizontalAlignment(JTextField.CENTER);
        stocks_tf.setBorder(border);
        add(stocks_tf);

        reOrderPoint_tf = new JTextField();
        reOrderPoint_tf.setBounds(250, 300, 150, 30);
        reOrderPoint_tf.setHorizontalAlignment(JTextField.CENTER);
        reOrderPoint_tf.setBorder(border);
        add(reOrderPoint_tf);

        item_text_field = new JTextField();
        item_text_field.setBounds(585, 15, 500, 20);
        item_text_field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(item_text_field);
        item_text_field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                // FIX: Fixed KeyAdapter logic - changed OR to AND in negation
                if (!(Character.isDigit(number) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                TableRowSorter<DefaultTableModel> sort_row = new TableRowSorter<>(tableModel);
                table.setRowSorter(sort_row);
                sort_row.setRowFilter(RowFilter.regexFilter(item_text_field.getText()));

                for (int i = 0; i < column.length; i++) {
                    sort_row.setSortable(i, false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void button_area() {
        stock_in_btn.setBounds(70, 370, 100, 30);
        stock_in_btn.setFont(new Font("Arial", Font.BOLD, 13));
        stock_in_btn.setBackground(Color.white);
        stock_in_btn.setForeground(Color.decode("#198754"));
        stock_in_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        stock_in_btn.setFocusable(false);
        add(stock_in_btn);

        add_btn.setBounds(175, 370, 100, 30);
        add_btn.setFont(new Font("Arial", Font.BOLD, 13));
        add_btn.setFocusable(false);
        add_btn.setBackground(Color.decode("#198754"));
        add_btn.setForeground(Color.WHITE);
        add_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        add(add_btn);

        edit_btn.setBounds(280, 370, 100, 30);
        edit_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        edit_btn.setFocusable(false);
        edit_btn.setBackground(Color.white);
        edit_btn.setForeground(Color.black);
        edit_btn.setBorder(BorderFactory.createLineBorder(Color.black));
        add(edit_btn);

        delete_btn.setBounds(70, 420, 100, 30);
        delete_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        delete_btn.setFocusable(false);
        delete_btn.setBackground(Color.decode("#dc3545"));
        delete_btn.setForeground(Color.white);
        delete_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#dc3545")));
        add(delete_btn);

        save_btn.setBounds(175, 420, 100, 30);
        save_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        save_btn.setEnabled(false);
        save_btn.setFocusable(false);
        save_btn.setBackground(Color.white);
        save_btn.setBorder(BorderFactory.createLineBorder(Color.gray));
        add(save_btn);

        cancel_btn.setBounds(280, 420, 100, 30);
        cancel_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        cancel_btn.setEnabled(false);
        cancel_btn.setFocusable(false);
        cancel_btn.setBackground(Color.white);
        add(cancel_btn);

        // Add action listeners
        add_btn.addActionListener(e -> handleAddItem());
        edit_btn.addActionListener(e -> handleEditItem());
        delete_btn.addActionListener(e -> handleDeleteItem());
        save_btn.addActionListener(e -> handleSaveItem());
        cancel_btn.addActionListener(e -> handleCancelEdit());
        stock_in_btn.addActionListener(e -> handleStockIn());
    }

    private void handleAddItem() {
        // Implementation of add item
        JOptionPane.showMessageDialog(this, "Add item functionality");
    }

    private void handleEditItem() {
        if (table.getSelectedRow() >= 0) {
            edit_btn.setEnabled(true);
        }
    }

    private void handleDeleteItem() {
        if (table.getSelectedRow() >= 0) {
            // Delete functionality
        }
    }

    private void handleSaveItem() {
        // Save functionality with parameterized queries
    }

    private void handleCancelEdit() {
        // Cancel edit functionality
    }

    private void handleStockIn() {
        // Stock in functionality
    }

    private void table_panel() {
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        panel.setLayout(new BorderLayout());
        panel.setBounds(460, 50, 660, 430);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(scroll_pane);
        add(panel);
    }

    private void data_refresh() {
        try {
            AccessMs accessMS = new AccessMs();
            Connection connection = accessMS.getConnection1();
            try (PreparedStatement preparedStatement = connection.prepareStatement("Select * From Table1");
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                
                tableModel.setRowCount(0);
                while (resultSet.next()) {
                    Vector<Object> vector = new Vector<>();
                    // FIX: Removed inner loop duplication
                    vector.add(resultSet.getString("Item Code"));
                    vector.add(resultSet.getString("Item Description"));
                    vector.add(resultSet.getString("Price"));
                    vector.add(resultSet.getString("Size"));
                    vector.add(resultSet.getString("Stocks"));
                    vector.add(resultSet.getString("Re-Order Point"));
                    vector.add(resultSet.getString("Remarks"));
                    tableModel.addRow(vector);
                }
            }
        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(this, Ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String generateNextCode(String currentCode) {
        if (currentCode == null) {
            return "0001";
        }
        int code = Integer.parseInt(currentCode);
        code++;
        return String.format("%0" + currentCode.length() + "d", code);
    }

    public void extend_Inventory() {
        label_area();
        tf_range();
        button_area();
        table_panel();
        data_refresh();

        ImageIcon icon = new ImageIcon("inventory_icon.png");
        setIconImage(icon.getImage());

        setResizable(false);
        getContentPane().setBackground(Color.decode("#EEEEEE"));
        setLayout(null);
        setTitle("Inventory");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
