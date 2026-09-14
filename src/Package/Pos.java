package Package;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;
import java.util.Vector;

public class Pos {

    private final JFrame frame = new JFrame();
    private final JPanel panel_1 = new JPanel();
    private final JPanel panel_2 = new JPanel();
    private final JPanel panel_3 = new JPanel();
    private final JPanel panel_4 = new JPanel();
    private final JPanel panel_5 = new JPanel();

    private final JTextField quantityTf = new JTextField();
    private final JTextField invoiceNo_tf = new JTextField();
    private final JTextField totalAmount_tf = new JTextField();

    private final JButton search_btn = new JButton("Search");
    private final JButton removeItem_btn = new JButton("Remove Item");
    private final JButton payment_btn = new JButton("Payment");
    private final JButton cancel_btn = new JButton("Cancel");
    
    private final Object[] column = { "Item Code", "Item Name", "Price", "Size", "Quantity", "Total" };
    private final DefaultTableModel pos_Tablemodel = new DefaultTableModel(null, column) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable pos_table = new JTable(pos_Tablemodel);
    private final JScrollPane scroll_pane = new JScrollPane(pos_table);

    private final ButtonGroup btn_Group = new ButtonGroup();
    private final JRadioButton student_rdbtn = new JRadioButton("Student (5%)");
    private final JRadioButton senior_rdbtn = new JRadioButton("Senior (20%)");
    private final JRadioButton regularCustomer_rdbtn = new JRadioButton("Regular Customer (10%)");
    private final JRadioButton employee_rdbtn = new JRadioButton("Employee (15%)");
    private final JRadioButton none_rdbtn = new JRadioButton("Clear Selection");
    private final double[] noOfDiscount = { 0.05, 0.2, 0.1, 0.15 };

    private void radioBtn_range() {
        student_rdbtn.setBounds(870, 180, 150, 30);
        student_rdbtn.setFocusable(false);
        frame.add(student_rdbtn);
        student_rdbtn.addActionListener(e -> refresh_total());

        senior_rdbtn.setBounds(870, 210, 150, 30);
        senior_rdbtn.setFocusable(false);
        frame.add(senior_rdbtn);
        senior_rdbtn.addActionListener(e -> refresh_total());

        regularCustomer_rdbtn.setBounds(870, 240, 200, 30);
        regularCustomer_rdbtn.setFocusable(false);
        frame.add(regularCustomer_rdbtn);
        regularCustomer_rdbtn.addActionListener(e -> refresh_total());

        employee_rdbtn.setBounds(870, 270, 150, 30);
        employee_rdbtn.setFocusable(false);
        frame.add(employee_rdbtn);
        employee_rdbtn.addActionListener(e -> refresh_total());

        none_rdbtn.setBounds(870, 300, 150, 30);
        none_rdbtn.setFocusable(false);
        frame.add(none_rdbtn);
        none_rdbtn.addActionListener(e -> {
            refresh_total();
            btn_Group.clearSelection();
        });

        btn_Group.add(student_rdbtn);
        btn_Group.add(senior_rdbtn);
        btn_Group.add(regularCustomer_rdbtn);
        btn_Group.add(employee_rdbtn);
        btn_Group.add(none_rdbtn);
    }

    protected void label_range() {
        JLabel title_label_1 = new JLabel("Secret Market Express");
        title_label_1.setBounds(350, 45, 500, 30);
        title_label_1.setForeground(Color.BLACK);
        title_label_1.setFont(new Font("Arial", Font.PLAIN, 30));
        frame.add(title_label_1);

        JLabel title_date = new JLabel();
        title_date.setBounds(100, 155, 350, 30);
        title_date.setForeground(Color.BLACK);
        title_date.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(title_date);

        Timer timer1 = new Timer(1000, e -> {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy, EEEE");
            String formattedDateTime1 = currentDateTime.format(formatter);
            title_date.setText("Date: " + formattedDateTime1);
        });
        timer1.start();

        JLabel title_time = new JLabel();
        title_time.setBounds(550, 155, 350, 30);
        title_time.setForeground(Color.BLACK);
        title_time.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(title_time);
        
        Timer timer = new Timer(1000, e -> {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            String formattedDateTime = currentDateTime.format(formatter);
            formattedDateTime = formattedDateTime.replaceFirst("^0+(?!$)", "");
            title_time.setText("Time: " + formattedDateTime);
        });
        timer.start();

        JLabel quantity_label_3 = new JLabel("Quantity");
        quantity_label_3.setBounds(90, 250, 150, 30);
        quantity_label_3.setForeground(Color.BLACK);
        frame.add(quantity_label_3);

        JLabel searchItem_label_4 = new JLabel("Search Item");
        searchItem_label_4.setBounds(290, 250, 150, 30);
        searchItem_label_4.setForeground(Color.BLACK);
        frame.add(searchItem_label_4);

        JLabel invoiceNo_label_5 = new JLabel("Invoice No");
        invoiceNo_label_5.setBounds(490, 250, 150, 30);
        invoiceNo_label_5.setForeground(Color.BLACK);
        frame.add(invoiceNo_label_5);

        JLabel totalAmount_label_6 = new JLabel("Total Amount");
        totalAmount_label_6.setBounds(680, 250, 150, 30);
        totalAmount_label_6.setForeground(Color.BLACK);
        frame.add(totalAmount_label_6);

        JLabel discount_label7 = new JLabel("Discount");
        discount_label7.setBounds(885, 140, 150, 30);
        discount_label7.setFont(new Font("Arial", Font.PLAIN, 25));
        discount_label7.setForeground(Color.BLACK);
        frame.add(discount_label7);
    }

    protected void tf_range() {
        quantityTf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quantityTf.setBounds(50, 280, 130, 30);
        quantityTf.setHorizontalAlignment(JTextField.CENTER);
        frame.add(quantityTf);
        quantityTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        invoiceNo_tf.setBounds(450, 280, 130, 30);
        invoiceNo_tf.setEditable(false);
        invoiceNo_tf.setFocusable(false);
        invoiceNo_tf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        invoiceNo_tf.setText("0");
        invoiceNo_tf.setHorizontalAlignment(JTextField.CENTER);
        frame.add(invoiceNo_tf);

        totalAmount_tf.setBounds(650, 280, 130, 30);
        totalAmount_tf.setEditable(false);
        totalAmount_tf.setFocusable(false);
        totalAmount_tf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalAmount_tf.setHorizontalAlignment(JTextField.CENTER);
        frame.add(totalAmount_tf);
    }

    protected void btn_range() {
        search_btn.setBounds(250, 280, 150, 30);
        search_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        search_btn.setBackground(Color.decode("#0d6efd"));
        search_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));
        search_btn.setForeground(Color.white);
        search_btn.setFocusable(false);
        frame.add(search_btn);

        search_btn.addActionListener(e -> {
            if (!quantityTf.getText().isEmpty()) {
                SwingUtilities.invokeLater(() -> showSearchWindow());
            } else {
                JOptionPane.showMessageDialog(frame, "Please put quantity before proceeding", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Remove item button
        removeItem_btn.setBounds(870, 450, 150, 30);
        removeItem_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        removeItem_btn.setFocusable(false);
        removeItem_btn.setBackground(Color.WHITE);
        removeItem_btn.setForeground(Color.decode("#dc3545"));
        removeItem_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#dc3545")));
        frame.add(removeItem_btn);
        removeItem_btn.addActionListener(e -> {
            if (pos_table.getSelectedRow() >= 0) {
                try {
                    AccessMs accessMS = new AccessMs();
                    Connection connect = accessMS.getConnection2();

                    if (pos_table.getSelectedRowCount() > 1) {
                        JOptionPane.showMessageDialog(frame, "Please select only one item to delete.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int message = JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to delete this data?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (message == JOptionPane.YES_OPTION) {
                        int selectedRowIndex = pos_table.getSelectedRow();
                        String itemCode = pos_Tablemodel.getValueAt(selectedRowIndex, 0).toString();

                        try (PreparedStatement prstatement = connect
                                .prepareStatement("DELETE FROM Table2 WHERE `Item Code` = ?")) {
                            prstatement.setString(1, itemCode);
                            int result = prstatement.executeUpdate();
                            pos_Table_reload();
                            refresh_total();

                            if (result > 0) {
                                JOptionPane.showMessageDialog(frame, "Successfully removed the Item");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Failed to remove the Item");
                            }
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an item to delete.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Payment button
        payment_btn.setBounds(870, 500, 150, 30);
        payment_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        payment_btn.setFocusable(false);
        payment_btn.setBackground(Color.white);
        payment_btn.setForeground(Color.decode("#198754"));
        payment_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        frame.add(payment_btn);
        payment_btn.addActionListener(e -> showPaymentWindow());

        // Cancel button
        cancel_btn.setBounds(870, 550, 150, 30);
        cancel_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cancel_btn.setFocusable(false);
        cancel_btn.setBackground(Color.white);
        cancel_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));
        cancel_btn.setForeground(Color.decode("#0d6efd"));
        frame.add(cancel_btn);
        cancel_btn.addActionListener(e -> {
            try {
                AccessMs accessMS = new AccessMs();
                Connection connect = accessMS.getConnection2();

                int message = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to cancel this transaction", "Confirm", JOptionPane.YES_NO_OPTION);

                if (message == JOptionPane.YES_OPTION) {
                    try (PreparedStatement prepare_statement = connect.prepareStatement("DELETE FROM Table2")) {
                        int result = prepare_statement.executeUpdate();
                        pos_Table_reload();
                        refresh_total();
                        btn_Group.clearSelection();

                        if (result > 0) {
                            JOptionPane.showMessageDialog(frame, "Successfully removed all data from the table");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to remove data from the table");
                        }
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void showSearchWindow() {
        // Search window implementation - using parameterized queries
        JFrame sh_Frame = new JFrame("Search Item");
        ImageIcon sh_icon = new ImageIcon("search_icon.png");
        sh_Frame.setIconImage(sh_icon.getImage());

        Object[] sh_Column = { "Item Code", "Item Description", "Price", "Size", "Stocks",
                "Re-Order Point", "Remarks" };
        DefaultTableModel sh_tableModel = new DefaultTableModel(null, sh_Column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable sh_table = new JTable(sh_tableModel);

        // Load data WITHOUT the vector duplication loop bug
        try {
            AccessMs accessMS = new AccessMs();
            Connection connected = accessMS.getConnection1();
            try (PreparedStatement prsm = connected.prepareStatement("Select * From Table1");
                 ResultSet rsltSet = prsm.executeQuery()) {
                
                sh_tableModel.setRowCount(0);
                while (rsltSet.next()) {
                    Vector<Object> sh_vector = new Vector<>();
                    // FIX: Removed the inner loop that was duplicating rows
                    sh_vector.add(rsltSet.getString("Item Code"));
                    sh_vector.add(rsltSet.getString("Item Description"));
                    sh_vector.add(rsltSet.getString("Price"));
                    sh_vector.add(rsltSet.getString("Size"));
                    sh_vector.add(rsltSet.getString("Stocks"));
                    sh_vector.add(rsltSet.getString("Re-Order Point"));
                    sh_vector.add(rsltSet.getString("Remarks"));
                    sh_tableModel.addRow(sh_vector);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane sh_scroll_pane = new JScrollPane(sh_table);
        JPanel sh_panel = new JPanel(new BorderLayout());
        sh_panel.setBounds(20, 20, 945, 520);
        sh_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sh_panel.add(sh_scroll_pane);
        sh_Frame.add(sh_panel);

        sh_Frame.setLayout(null);
        sh_Frame.setSize(1000, 700);
        sh_Frame.setResizable(false);
        // FIX: Removed sh_Frame.dispose() before setVisible
        sh_Frame.setLocationRelativeTo(null);
        sh_Frame.setVisible(true);
    }

    private void showPaymentWindow() {
        JFrame py_Frame = new JFrame("Payment");
        ImageIcon py_icon = new ImageIcon("payment_icon.png");
        py_Frame.setIconImage(py_icon.getImage());

        JTextField payment_tf = new JTextField();
        JButton okBtn = new JButton("Ok");
        JButton cancelBtn = new JButton("Cancel");

        JLabel label = new JLabel("Enter Your Payment");
        label.setBounds(50, 30, 200, 30);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        py_Frame.add(label);

        payment_tf.setBounds(70, 80, 150, 30);
        payment_tf.setHorizontalAlignment(JTextField.CENTER);
        py_Frame.add(payment_tf);
        payment_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number) || number == KeyEvent.VK_DELETE
                        || number == KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        okBtn.setBounds(30, 140, 100, 30);
        okBtn.setFocusable(false);
        okBtn.setBackground(Color.decode("#198754"));
        okBtn.setForeground(Color.WHITE);
        okBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        py_Frame.add(okBtn);
        okBtn.addActionListener(e -> {
            try {
                long countingNumber = Long.parseLong(totalAmount_tf.getText());
                long amount = Long.parseLong(payment_tf.getText());

                if (pos_table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "Sorry, you cannot buy at the moment. The table is empty.");
                } else {
                    // FIX: Changed >= to < for correct payment validation logic
                    if (amount < countingNumber) {
                        JOptionPane.showMessageDialog(frame,
                                "Your payment has been declined. The amount entered is not enough to complete this transaction.",
                                "Not Enough", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int dialog = JOptionPane.showConfirmDialog(frame,
                                "Order confirmed! Your purchase is being processed, Would you like to see the receipt?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (dialog == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(frame, "Receipt printed. Thank you for your purchase!");
                        }
                        py_Frame.dispose();
                        try (AccessMs accessMS = new AccessMs();
                             Connection connect = accessMS.getConnection2();
                             PreparedStatement statement = connect.prepareStatement("Delete From Table2")) {
                            statement.executeUpdate();
                            pos_Table_reload();
                            refresh_total();
                            btn_Group.clearSelection();
                            invoiceNo_tf.setText(String.valueOf(Long.parseLong(invoiceNo_tf.getText()) + 1));
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Error: Invalid payment amount. Please enter a valid number.",
                        "Payment Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Error: Database error occurred.",
                        "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.setBounds(150, 140, 100, 30);
        cancelBtn.setFocusable(false);
        cancelBtn.setBackground(Color.white);
        cancelBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));
        cancelBtn.setForeground(Color.decode("#0d6efd"));
        py_Frame.add(cancelBtn);
        cancelBtn.addActionListener(e -> {
            payment_tf.setText("");
            py_Frame.dispose();
        });

        py_Frame.setResizable(false);
        py_Frame.setLayout(null);
        py_Frame.setSize(300, 250);
        py_Frame.setLocationRelativeTo(null);
        py_Frame.setVisible(true);
    }

    protected void table_range() {
        pos_table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = pos_table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        pos_table.setDefaultRenderer(Object.class, centerRenderer);

        panel_4.setBounds(10, 340, 800, 310);
        panel_4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel_4.setLayout(new BorderLayout());
        panel_4.add(scroll_pane);
        frame.add(panel_4);
    }

    protected void panel_range() {
        panel_1.setBounds(10, 10, 1065, 100);
        panel_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel_1);

        panel_2.setBounds(10, 120, 800, 100);
        panel_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel_2);

        panel_3.setBounds(10, 230, 800, 100);
        panel_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel_3);

        panel_5.setBounds(820, 120, 255, 530);
        panel_5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel_5);
    }

    protected void pos_Table_reload() {
        try {
            AccessMs accessMS = new AccessMs();
            Connection connection = accessMS.getConnection2();
            try (PreparedStatement preparedStatement = connection.prepareStatement("Select * From Table2");
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                
                pos_Tablemodel.setRowCount(0);
                while (resultSet.next()) {
                    Vector<Object> vector = new Vector<>();
                    // FIX: Removed inner loop duplication
                    vector.add(resultSet.getString("Item Code"));
                    vector.add(resultSet.getString("Item Name"));
                    vector.add(resultSet.getString("Price"));
                    vector.add(resultSet.getString("Size"));
                    vector.add(resultSet.getString("Quantity"));
                    vector.add(resultSet.getString("Total"));
                    pos_Tablemodel.addRow(vector);
                }
            }
        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(frame, Ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refresh_total() {
        try {
            AccessMs accessMS = new AccessMs();
            Connection connect = accessMS.getConnection2();
            int totalValue = 0;

            try (Statement statement = connect.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT Total FROM Table2")) {
                
                while (resultSet.next()) {
                    totalValue += resultSet.getInt("Total");
                }
            }

            if (student_rdbtn.isSelected()) {
                long value = Math.round(totalValue * noOfDiscount[0]);
                totalAmount_tf.setText(String.valueOf(totalValue - value));
            } else if (senior_rdbtn.isSelected()) {
                long value = Math.round(totalValue * noOfDiscount[1]);
                totalAmount_tf.setText(String.valueOf(totalValue - value));
            } else if (regularCustomer_rdbtn.isSelected()) {
                long value = Math.round(totalValue * noOfDiscount[2]);
                totalAmount_tf.setText(String.valueOf(totalValue - value));
            } else if (employee_rdbtn.isSelected()) {
                long value = Math.round(totalValue * noOfDiscount[3]);
                totalAmount_tf.setText(String.valueOf(totalValue - value));
            } else {
                totalAmount_tf.setText(String.valueOf(totalValue));
            }
        } catch (Exception E1) {
            JOptionPane.showMessageDialog(frame, E1);
        }
    }

    public void extend_Pos() {
        radioBtn_range();
        label_range();
        tf_range();
        btn_range();
        table_range();
        panel_range();
        pos_Table_reload();
        refresh_total();

        ImageIcon imgicon = new ImageIcon("pos.png");
        frame.setIconImage(imgicon.getImage());

        frame.setTitle("Sales");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
