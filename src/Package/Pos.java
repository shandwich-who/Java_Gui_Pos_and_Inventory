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
    // panel
    private final JPanel panel_1 = new JPanel();
    private final JPanel panel_2 = new JPanel();
    private final JPanel panel_3 = new JPanel();
    private final JPanel panel_4 = new JPanel();
    private final JPanel panel_5 = new JPanel();
    // panel

    // text field
    private final JTextField quantityTf = new JTextField();
    private final JTextField invoiceNo_tf = new JTextField();
    private final JTextField totalAmount_tf = new JTextField();
    // text field

    // button
    private final JButton search_btn = new JButton("Search");
    private final JButton removeItem_btn = new JButton("Remove Item");
    private final JButton payment_btn = new JButton("Payment");
    private final JButton cancel_btn = new JButton("Cancel");
    // button
    private final Object[] column = { "Item Code", "Item Name", "Price", "Size", "Quantity", "Total" };
    private final DefaultTableModel pos_Tablemodel = new DefaultTableModel(null, column) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };// Make all cells non-editable
    private final JTable pos_table = new JTable(pos_Tablemodel);
    private final JScrollPane scroll_pane = new JScrollPane(pos_table);
    // range of get quantity

    private final ButtonGroup btn_Group = new ButtonGroup();
    private final JRadioButton student_rdbtn = new JRadioButton("Student (5%)");
    private final JRadioButton senior_rdbtn = new JRadioButton("Senior (20%)");
    private final JRadioButton regularCustomer_rdbtn = new JRadioButton("Regular Customer (10%)");
    private final JRadioButton employee_rdbtn = new JRadioButton("Employee (15%)");
    private final JRadioButton none_rdbtn = new JRadioButton("Clear Selection");
    private final double[] noOfDiscount = { 0.05, 0.2, 0.1, 0.15 };

    // --->>>
    private void radioBtn_range() {
        student_rdbtn.setBounds(870, 180, 150, 30);
        student_rdbtn.setFocusable(false);
        frame.add(student_rdbtn);
        student_rdbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh_total();
            }
        });

        senior_rdbtn.setBounds(870, 210, 150, 30);
        senior_rdbtn.setFocusable(false);
        frame.add(senior_rdbtn);
        senior_rdbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh_total();
            }
        });

        regularCustomer_rdbtn.setBounds(870, 240, 200, 30);
        regularCustomer_rdbtn.setFocusable(false);
        frame.add(regularCustomer_rdbtn);
        regularCustomer_rdbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh_total();
            }
        });

        employee_rdbtn.setBounds(870, 270, 150, 30);
        employee_rdbtn.setFocusable(false);
        frame.add(employee_rdbtn);
        employee_rdbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh_total();
            }
        });

        none_rdbtn.setBounds(870, 300, 150, 30);
        none_rdbtn.setFocusable(false);
        frame.add(none_rdbtn);
        none_rdbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh_total();
                btn_Group.clearSelection();
            }
        });

        btn_Group.add(student_rdbtn);
        btn_Group.add(senior_rdbtn);
        btn_Group.add(regularCustomer_rdbtn);
        btn_Group.add(employee_rdbtn);
        btn_Group.add(none_rdbtn);

    }

    protected void label_range() {
        // this range is for the label
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
        timer1.start();/* for the date real time */

        JLabel title_time = new JLabel();
        title_time.setBounds(550, 155, 350, 30);
        title_time.setForeground(Color.BLACK);
        title_time.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.add(title_time);
        Timer timer = new Timer(1000, e -> {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            String formattedDateTime = currentDateTime.format(formatter);

            // Replace the 13:00 with 1:00 in the formatted date and time
            formattedDateTime = formattedDateTime.replaceFirst("^0+(?!$)", "");

            title_time.setText("Time: " + formattedDateTime);
        });
        timer.start();/* for the time real time */

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
                if (!(Character.isDigit(number)) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_BACK_SPACE) {
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
        // this is for the button
        search_btn.setBounds(250, 280, 150, 30);
        search_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        search_btn.setBackground(Color.decode("#0d6efd"));
        search_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));
        search_btn.setForeground(Color.white);
        search_btn.setFocusable(false);
        frame.add(search_btn);

        search_btn.addActionListener(e -> {
            if (!(quantityTf.getText().isEmpty())) {

                SwingUtilities.invokeLater(new Runnable() { // start
                    @Override
                    public void run() {

                        JFrame sh_Frame = new JFrame("Search Item");

                        ImageIcon sh_icon = new ImageIcon("search_icon.png");
                        sh_Frame.setIconImage(sh_icon.getImage());

                        Object[] sh_Column = { "Item Code", "Item Description", "Price", "Size", "Stocks",
                                "Re-Order Point", "Remarks" };
                        DefaultTableModel sh_tableModel = new DefaultTableModel(null, sh_Column) {
                            public boolean isCellEditable(int row, int column) {
                                return false; // Make all cells non-editable
                            }
                        };
                        JTable sh_table = new JTable(sh_tableModel);// table

                        JButton sh_okBtn = new JButton("Okay");// button

                        JTextField tf_code = new JTextField();

                        JTextField tf_name = new JTextField();

                        JTextField tf_price = new JTextField();

                        JTextField tf_size = new JTextField();

                        JTextField tf_quantity = new JTextField();

                        JTextField tf_total = new JTextField();

                        SwingUtilities.invokeLater(new Runnable() {// start label
                            @Override
                            public void run() {
                                JLabel lbl_Code = new JLabel("Item Code");
                                lbl_Code.setBounds(60, 540, 100, 30);
                                sh_Frame.add(lbl_Code);

                                JLabel lbl_Name = new JLabel("Item Name");
                                lbl_Name.setBounds(220, 540, 100, 30);
                                sh_Frame.add(lbl_Name);

                                JLabel lbl_Price = new JLabel("Item Price");
                                lbl_Price.setBounds(380, 540, 100, 30);
                                sh_Frame.add(lbl_Price);

                                JLabel lbl_Size = new JLabel("Item Size");
                                lbl_Size.setBounds(550, 540, 100, 30);
                                sh_Frame.add(lbl_Size);

                                JLabel lbl_Quantity = new JLabel("Quantity");
                                lbl_Quantity.setBounds(710, 540, 100, 30);
                                sh_Frame.add(lbl_Quantity);

                                JLabel lbl_Total = new JLabel("Item Total");
                                lbl_Total.setBounds(870, 540, 100, 30);
                                sh_Frame.add(lbl_Total);

                            }
                        });// end label

                        SwingUtilities.invokeLater(new Runnable() {// start text field layout
                            @Override
                            public void run() {
                                // code text field
                                tf_code.setBounds(20, 570, 130, 30);
                                tf_code.setEditable(false);
                                tf_code.setFocusable(false);
                                tf_code.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                tf_code.setHorizontalAlignment(JTextField.CENTER);
                                sh_Frame.add(tf_code);

                                // name text field
                                tf_name.setBounds(180, 570, 130, 30);
                                tf_name.setEditable(false);
                                tf_name.setFocusable(false);
                                tf_name.setHorizontalAlignment(JTextField.CENTER);
                                tf_name.setBorder(BorderFactory.createLineBorder(Color.black));
                                sh_Frame.add(tf_name);

                                // price text field
                                tf_price.setBounds(340, 570, 130, 30);
                                tf_price.setEditable(false);
                                tf_price.setFocusable(false);
                                tf_price.setBorder(BorderFactory.createLineBorder(Color.black));
                                tf_price.setHorizontalAlignment(JTextField.CENTER);
                                sh_Frame.add(tf_price);

                                // size text field
                                tf_size.setBounds(510, 570, 130, 30);
                                tf_size.setEditable(false);
                                tf_size.setFocusable(false);
                                tf_size.setBorder(BorderFactory.createLineBorder(Color.black));
                                tf_size.setHorizontalAlignment(JTextField.CENTER);
                                sh_Frame.add(tf_size);

                                // quantity text field
                                tf_quantity.setBounds(670, 570, 130, 30);
                                tf_quantity.setEditable(false);
                                tf_quantity.setFocusable(false);
                                tf_quantity.setBorder(BorderFactory.createLineBorder(Color.black));
                                tf_quantity.setHorizontalAlignment(JTextField.CENTER);
                                sh_Frame.add(tf_quantity);

                                // total text field
                                tf_total.setBounds(835, 570, 130, 30);
                                tf_total.setEditable(false);
                                tf_total.setFocusable(false);
                                tf_total.setBorder(BorderFactory.createLineBorder(Color.black));
                                tf_total.setHorizontalAlignment(JTextField.CENTER);
                                sh_Frame.add(tf_total);

                            }
                        });// end text field layout

                        SwingUtilities.invokeLater(new Runnable() {// start button
                            @Override
                            public void run() {
                                sh_okBtn.setBounds(415, 615, 150, 30);
                                sh_okBtn.setFocusable(false);
                                sh_okBtn.setEnabled(true);
                                sh_okBtn.setBackground(Color.decode("#198754"));
                                sh_okBtn.setForeground(Color.black);
                                sh_okBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
                                sh_Frame.add(sh_okBtn);

                                sh_okBtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        if (tf_code.getText().isEmpty() || tf_name.getText().isEmpty()
                                                || tf_price.getText().isEmpty() || tf_size.getText().isEmpty()
                                                || tf_quantity.getText().isEmpty() || tf_total.getText().isEmpty()) {
                                            JOptionPane.showMessageDialog(new JFrame(), " Error: Field is empty.",
                                                    "Error", JOptionPane.ERROR_MESSAGE);

                                        } else {
                                            try {
                                                AccessMs accessMS = new AccessMs();
                                                Connection connected = accessMS.getConnection2();

                                                PreparedStatement prstStatement = connected.prepareStatement(
                                                        "Insert Into Table2 (`Item Code`, `Item Name`, Price, Size, Quantity, Total) Values (?, ?, ?, ?, ?, ?)");

                                                String[] listData = { tf_code.getText(), tf_name.getText(),
                                                        tf_price.getText(), tf_size.getText(), tf_quantity.getText(),
                                                        tf_total.getText() };
                                                prstStatement.setString(1, listData[0]);
                                                prstStatement.setString(2, listData[1]);
                                                prstStatement.setString(3, listData[2]);
                                                prstStatement.setString(4, listData[3]);
                                                prstStatement.setString(5, listData[4]);
                                                prstStatement.setString(6, listData[5]);
                                                prstStatement.executeUpdate();
                                                pos_Table_reload();
                                                sh_Frame.dispose();
                                                refresh_total();
                                                quantityTf.setText("");

                                            } catch (Exception E1) {
                                                JOptionPane.showMessageDialog(new JFrame(),
                                                        "This field already contains a duplicate entry. Please provide a different item.");
                                                System.out.println(E1.getMessage());
                                            }

                                        }

                                    }
                                });

                            }
                        });// end button

                        SwingUtilities.invokeLater(new Runnable() {// start refresh table data
                            @Override
                            public void run() {
                                int Loop;
                                try {
                                    AccessMs accessMS = new AccessMs();
                                    Connection connected = accessMS.getConnection1();

                                    PreparedStatement prsm = connected.prepareStatement("Select * From Table1");

                                    ResultSet rsltSet = prsm.executeQuery();

                                    ResultSetMetaData rslt_metaData = rsltSet.getMetaData();

                                    Loop = rslt_metaData.getColumnCount();
                                    sh_tableModel.setRowCount(0);

                                    while (rsltSet.next()) {

                                        Vector<Object> sh_vector = new Vector<>();
                                        for (int i = 0; i <= Loop; i++) {

                                            sh_vector.add(rsltSet.getString("Item Code"));
                                            sh_vector.add(rsltSet.getString("Item Description"));
                                            sh_vector.add(rsltSet.getString("Price"));
                                            sh_vector.add(rsltSet.getString("Size"));
                                            sh_vector.add(rsltSet.getString("Stocks"));
                                            sh_vector.add(rsltSet.getString("Re-Order Point"));
                                            sh_vector.add(rsltSet.getString("Remarks"));
                                        }
                                        sh_tableModel.addRow(sh_vector);

                                    }

                                } catch (Exception Ex) {
                                    JOptionPane.showMessageDialog(new JFrame(), Ex.getMessage(), "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                } // this is the boundaries of the reloading the data in the table

                            }
                        });// end refresh table data

                        SwingUtilities.invokeLater(new Runnable() {// start table
                            @Override
                            public void run() {
                                sh_table.getTableHeader().setReorderingAllowed(false); // cannot move the order column
                                TableColumnModel sh_ColumnModel = sh_table.getColumnModel();
                                for (int i = 0; i < sh_ColumnModel.getColumnCount(); i++) {
                                    sh_ColumnModel.getColumn(i).setResizable(false);
                                }
                                DefaultTableCellRenderer sh_centerRender = new DefaultTableCellRenderer();
                                sh_centerRender.setHorizontalAlignment(JLabel.CENTER);
                                sh_table.setDefaultRenderer(Object.class, sh_centerRender);

                                sh_table.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        String column_Ic = sh_tableModel.getValueAt(sh_table.getSelectedRow(), 0)
                                                .toString();
                                        String column_Name = sh_tableModel.getValueAt(sh_table.getSelectedRow(), 1)
                                                .toString();
                                        String column_Price = sh_tableModel.getValueAt(sh_table.getSelectedRow(), 2)
                                                .toString();
                                        String column_Size = sh_tableModel.getValueAt(sh_table.getSelectedRow(), 3)
                                                .toString();
                                        String column_Quantity = quantityTf.getText();
                                        long compute = Long.parseLong(column_Quantity) * Long.parseLong(column_Price);
                                        String column_Total = String.valueOf(compute);
                                        // the selected item
                                        tf_code.setText(column_Ic);
                                        tf_name.setText(column_Name);
                                        tf_price.setText(column_Price);
                                        tf_size.setText(column_Size);
                                        tf_quantity.setText(column_Quantity);
                                        tf_total.setText(column_Total);
                                    }
                                });// boundaries of mouse listener

                                JPanel sh_panel = new JPanel();
                                sh_panel.setBounds(20, 20, 945, 520);
                                sh_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                sh_panel.setLayout(new BorderLayout());

                                JScrollPane sh_scroll_pane = new JScrollPane(sh_table);
                                sh_panel.add(sh_scroll_pane);
                                sh_Frame.add(sh_panel);
                            }
                        });// end table

                        SwingUtilities.invokeLater(new Runnable() { // frame start
                            @Override
                            public void run() {
                                sh_Frame.setLayout(null);
                                sh_Frame.setSize(1000, 700);
                                sh_Frame.setResizable(false);
                                sh_Frame.dispose();
                                sh_Frame.setLocationRelativeTo(null);
                                sh_Frame.setVisible(true);
                            }
                        });// frame end

                    }
                });// end

            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Please put quantity before proceeding", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        });

        removeItem_btn.setBounds(870, 450, 150, 30);
        removeItem_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        removeItem_btn.setFocusable(false);
        removeItem_btn.setBackground(Color.WHITE);
        removeItem_btn.setForeground(Color.decode("#dc3545"));
        removeItem_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#dc3545")));
        frame.add(removeItem_btn);
        removeItem_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (pos_table.getSelectedRow() >= 0) {
                    try {
                        AccessMs accessMS = new AccessMs();
                        Connection connect = accessMS.getConnection2();

                        // Check if an item is selected
                        if (pos_table.getSelectedRowCount() > 1) {
                            JOptionPane.showMessageDialog(new JFrame(), "Please select only one item to delete.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method
                        }

                        int message = JOptionPane.showConfirmDialog(new JFrame(),
                                "Are you sure you want to delete this data?", "Confirm", JOptionPane.YES_NO_OPTION);

                        if (message == JOptionPane.YES_OPTION) {
                            int selectedRowIndex = pos_table.getSelectedRow();
                            String itemCode = pos_Tablemodel.getValueAt(selectedRowIndex, 0).toString();

                            PreparedStatement prstatement = connect
                                    .prepareStatement("DELETE FROM Table2 WHERE `Item Code` = ?");
                            prstatement.setString(1, itemCode);
                            int result = prstatement.executeUpdate();
                            pos_Table_reload();
                            refresh_total();

                            if (result > 0) {
                                JOptionPane.showMessageDialog(new JFrame(), "Successfully removed the Item");
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Failed to remove the Item");
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Please select an item to delete.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        payment_btn.setBounds(870, 500, 150, 30);
        payment_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        payment_btn.setFocusable(false);
        payment_btn.setBackground(Color.white);
        payment_btn.setForeground(Color.decode("#198754"));
        payment_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        frame.add(payment_btn);
        payment_btn.addActionListener(e -> {

            SwingUtilities.invokeLater(new Runnable() {// hold the whole function
                @Override
                public void run() {
                    JFrame py_Frame = new JFrame("Payment");

                    ImageIcon py_icon = new ImageIcon("payment_icon.png");
                    py_Frame.setIconImage(py_icon.getImage());

                    JTextField payment_tf = new JTextField();
                    JButton okBtn = new JButton("Ok");
                    JButton cancelBtn = new JButton("Cancel");
                    SwingUtilities.invokeLater(new Runnable() {// ------> this it the frame of the payment
                        @Override
                        public void run() {
                            JLabel label = new JLabel("Enter Your  Payment");
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
                                    if (!(Character.isDigit(number)) || number == KeyEvent.VK_DELETE
                                            || number == KeyEvent.VK_BACK_SPACE) {
                                        e.consume();
                                    }

                                }
                            });

                        }
                    });

                    SwingUtilities.invokeLater(new Runnable() {// ---->> button
                        @Override
                        public void run() {

                            okBtn.setBounds(30, 140, 100, 30);
                            okBtn.setFocusable(false);
                            okBtn.setBackground(Color.decode("#198754"));
                            okBtn.setForeground(Color.WHITE);
                            okBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
                            py_Frame.add(okBtn);
                            okBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {

                                        long countingNumber = Long.parseLong(totalAmount_tf.getText());
                                        long amount = Long.parseLong(payment_tf.getText());

                                        if (pos_table.getRowCount() == 0){
                                            JOptionPane.showMessageDialog(new JFrame(), "Sorry, you cannot buy at the moment. The table is empty.");

                                        }else {
                                            if (countingNumber >= amount ) {
                                                JOptionPane.showMessageDialog(new JFrame(),
                                                        "Your payment has been declined The amount entered is not enough to complete this transaction.",
                                                        "Not Enough", JOptionPane.ERROR_MESSAGE);

                                            } else {
                                                int dialog = JOptionPane.showConfirmDialog(new JFrame(),
                                                        "Order confirmed! Your purchase is being processed, Would you like to see the receipt?",
                                                        "Confirmation", JOptionPane.YES_NO_OPTION);
                                                if (dialog == JOptionPane.YES_OPTION) {

                                                    JFrame frame_receipt = new JFrame();

                                                    SwingUtilities.invokeLater(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            JLabel name_of_shop = new JLabel("Secret Market Express");
                                                            name_of_shop.setBounds(115, 30, 200, 30);
                                                            name_of_shop.setFont(Font.getFont("Arial"));
                                                            frame_receipt.add(name_of_shop);

                                                            JLabel address_of_shop = new JLabel(
                                                                    "1234 Fictional Street, Imaginary City, Dreamland");
                                                            address_of_shop.setBounds(40, 60, 300, 30);
                                                            address_of_shop.setFont(Font.getFont("Arial"));
                                                            frame_receipt.add(address_of_shop);

                                                            LocalDate currentDate = LocalDate.now();
                                                            DateTimeFormatter formatter = DateTimeFormatter
                                                                    .ofPattern("EEEE, MMMM dd, yyyy");
                                                            String formattedDate = currentDate.format(formatter);

                                                            JLabel date_label = new JLabel(formattedDate);
                                                            date_label.setBounds(95, 90, 350, 30);
                                                            date_label.setFont(new Font("Arial", Font.PLAIN, 15));
                                                            frame_receipt.add(date_label);

                                                            JLabel label_receipt = new JLabel("Receipt for Payment");
                                                            label_receipt.setBounds(85, 130, 200, 30);
                                                            label_receipt.setFont(new Font("Arial", Font.BOLD, 20));
                                                            frame_receipt.add(label_receipt);

                                                            JLabel line = new JLabel(
                                                                    "__________________________________________");
                                                            line.setBounds(25, 150, 350, 30);
                                                            frame_receipt.add(line);

                                                            JLabel im_Name = new JLabel("Item Name");
                                                            im_Name.setBounds(25, 180, 150, 30);
                                                            im_Name.setFont(new Font("Arial", Font.BOLD, 12));
                                                            im_Name.setForeground(Color.BLACK);
                                                            frame_receipt.add(im_Name);

                                                            JLabel qt_Name = new JLabel("Quantity");
                                                            qt_Name.setBounds(150, 180, 150, 30);
                                                            qt_Name.setFont(new Font("Arial", Font.BOLD, 12));
                                                            qt_Name.setForeground(Color.BLACK);
                                                            frame_receipt.add(qt_Name);

                                                            JLabel tl_name = new JLabel("Price");
                                                            tl_name.setBounds(285, 180, 150, 30);
                                                            tl_name.setFont(new Font("Arial", Font.BOLD, 12));
                                                            tl_name.setForeground(Color.BLACK);
                                                            frame_receipt.add(tl_name);

                                                            // displaying the item in the table
                                                            int pos_row_count = pos_table.getRowCount();
                                                            JLabel[] itemLabel = new JLabel[pos_row_count];
                                                            JLabel[] qtyLabel = new JLabel[pos_row_count];
                                                            JLabel[] prcLabel = new JLabel[pos_row_count];
                                                            int y = 210;
                                                            for (int i = 0; i < pos_row_count; i++) {
                                                                itemLabel[i] = new JLabel();
                                                                itemLabel[i].setText(
                                                                        String.valueOf(pos_table.getValueAt(i, 1)));
                                                                itemLabel[i].setBounds(25, y, 200, 30);
                                                                frame_receipt.add(itemLabel[i]);

                                                                qtyLabel[i] = new JLabel();
                                                                qtyLabel[i].setText(
                                                                        String.valueOf(pos_table.getValueAt(i, 4)));
                                                                qtyLabel[i].setBounds(170, y, 300, 30);
                                                                frame_receipt.add(qtyLabel[i]);

                                                                prcLabel[i] = new JLabel();
                                                                prcLabel[i].setText(
                                                                        String.valueOf(pos_table.getValueAt(i, 5)));
                                                                prcLabel[i].setBounds(285, y, 300, 30);
                                                                frame_receipt.add(prcLabel[i]);

                                                                y += 25;
                                                            }

                                                            JLabel Justline = new JLabel(
                                                                    "__________________________________________");
                                                            Justline.setBounds(25, 400, 350, 30);
                                                            frame_receipt.add(Justline);

                                                            JLabel payment_lbl = new JLabel("Payment");
                                                            payment_lbl.setBounds(25, 430, 350, 30);
                                                            payment_lbl.setFont(new Font("Arial", Font.BOLD, 12));
                                                            frame_receipt.add(payment_lbl);

                                                            JLabel payment_getNo = new JLabel(payment_tf.getText());
                                                            payment_getNo.setBounds(25, 460, 200, 30);
                                                            frame_receipt.add(payment_getNo);

                                                            JLabel change_lbl = new JLabel("Change");
                                                            change_lbl.setBounds(150, 430, 350, 30);
                                                            change_lbl.setFont(new Font("Arial", Font.BOLD, 12));
                                                            frame_receipt.add(change_lbl);

                                                            Long minusLong = Long.parseLong(payment_tf.getText())
                                                                    - countingNumber;

                                                            JLabel change_getNo = new JLabel(String.valueOf(minusLong));
                                                            change_getNo.setBounds(150, 460, 200, 30);
                                                            frame_receipt.add(change_getNo);

                                                            JLabel totol_lbl = new JLabel("Total");
                                                            totol_lbl.setBounds(285, 430, 350, 30);
                                                            totol_lbl.setFont(new Font("Arial", Font.BOLD, 12));
                                                            frame_receipt.add(totol_lbl);

                                                            JLabel total_getNO = new JLabel(totalAmount_tf.getText());
                                                            total_getNO.setBounds(285, 460, 200, 30);
                                                            frame_receipt.add(total_getNO);

                                                            JButton button_ok = new JButton("OK");
                                                            button_ok.setBounds(120, 540, 100, 30);
                                                            button_ok.setFocusable(false);
                                                            button_ok.setBackground(Color.decode("#198754"));
                                                            button_ok.setForeground(Color.WHITE);
                                                            button_ok.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));

                                                            frame_receipt.add(button_ok);
                                                            button_ok.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    frame_receipt.dispose();
                                                                    try {

                                                                        AccessMs accessMS = new AccessMs();
                                                                        Connection connect = accessMS.getConnection2();

                                                                        PreparedStatement statement_Prst = connect
                                                                                .prepareStatement("Delete From Table2");
                                                                        statement_Prst.executeUpdate();
                                                                        pos_Table_reload();
                                                                        refresh_total();
                                                                        btn_Group.clearSelection();
                                                                        invoiceNo_tf.setText(String.valueOf(
                                                                                Long.parseLong(invoiceNo_tf.getText())
                                                                                        + 1));

                                                                    } catch (Exception Exe) {
                                                                        JOptionPane.showMessageDialog(new JFrame(),
                                                                                Exe.getMessage());
                                                                    }

                                                                }
                                                            });

                                                        }
                                                    });

                                                    Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            frame_receipt.getContentPane().setBackground(Color.WHITE);
                                                            frame_receipt.getRootPane()
                                                                    .setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                                            frame_receipt.setUndecorated(true);
                                                            frame_receipt.setSize(350, 600);
                                                            frame_receipt.setLayout(null);
                                                            frame_receipt.setLocationRelativeTo(null);
                                                            frame_receipt.setVisible(true);
                                                        }
                                                    });
                                                    thread.start();

                                                } else {

                                                    JOptionPane.showMessageDialog(new JFrame(),
                                                            "Thank you for your support.");
                                                }
                                                py_Frame.dispose();

                                            }
                                        }

                                    } catch (Exception e1) {

                                        JOptionPane.showMessageDialog(new JFrame(),
                                                "Error: Payment information is missing. Please provide payment details.",
                                                "Payment Error", JOptionPane.ERROR_MESSAGE);
                                        System.out.println(e1.getMessage());

                                    }

                                }
                            });

                        }
                    });/// ----> ok button for the payment

                    SwingUtilities.invokeLater(new Runnable() {// -------> this is the cancel button
                        @Override
                        public void run() {
                            cancelBtn.setBounds(150, 140, 100, 30);
                            cancelBtn.setFocusable(false);
                            cancelBtn.setBackground(Color.white);
                            cancelBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));
                            cancelBtn.setForeground(Color.decode("#0d6efd"));
                            py_Frame.add(cancelBtn);
                            cancelBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    payment_tf.setText("");
                                    py_Frame.dispose();
                                }
                            });
                        }
                    });// -------> this is the cancel button

                    SwingUtilities.invokeLater(new Runnable() {// ---> Frame start
                        @Override
                        public void run() {
                            py_Frame.setResizable(false);
                            py_Frame.setLayout(null);
                            py_Frame.setSize(300, 250);
                            py_Frame.setLocationRelativeTo(null);
                            py_Frame.setVisible(true);
                        }
                    });// end frame

                }
            });// hold end --- >> function

        });

        cancel_btn.setBounds(870, 550, 150, 30);
        cancel_btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cancel_btn.setFocusable(false);
        cancel_btn.setBackground(Color.white);
        cancel_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));
        cancel_btn.setForeground(Color.decode("#0d6efd"));

        frame.add(cancel_btn);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AccessMs accessMS = new AccessMs();
                    Connection connect = accessMS.getConnection2();

                    int message = JOptionPane.showConfirmDialog(new JFrame(),
                            "Are you sure you want to cancel this transaction", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (message == JOptionPane.YES_OPTION) {
                        PreparedStatement prepare_statement = connect.prepareStatement("DELETE FROM Table2");
                        int result = prepare_statement.executeUpdate();
                        pos_Table_reload();
                        refresh_total();
                        btn_Group.clearSelection();

                        if (result > 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Successfully removed all data from the table");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Failed to remove data from the table");
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

    }// end of button --> range

    protected void table_range() {

        pos_table.getTableHeader().setReorderingAllowed(false); // cannot move the order column
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
        // this is for the panel or the border in the frame

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

    // I will put here the refresh the data to the point of sale
    protected void pos_Table_reload() {
        int c;
        try {

            AccessMs accessMS = new AccessMs();
            Connection connection = accessMS.getConnection2();

            PreparedStatement preparedStatement = connection.prepareStatement("Select * From Table2");

            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            c = resultSetMetaData.getColumnCount();
            pos_Tablemodel.setRowCount(0);

            while (resultSet.next()) {

                Vector<Object> vector = new Vector<>();
                for (int i = 0; i <= c; i++) {

                    vector.add(resultSet.getString("Item Code"));
                    vector.add(resultSet.getString("Item Name"));
                    vector.add(resultSet.getString("Price"));
                    vector.add(resultSet.getString("Size"));
                    vector.add(resultSet.getString("Quantity"));
                    vector.add(resultSet.getString("Total"));
                }
                pos_Tablemodel.addRow(vector);

            }

        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(new JFrame(), Ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }

    }// */ end

    public void refresh_total() {// for the total amount of text field in this pos
        try {
            AccessMs accessMS = new AccessMs();

            Connection connect = accessMS.getConnection2();
            int totalValue = 0;

            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Total FROM Table2");

            while (resultSet.next()) {
                totalValue += resultSet.getInt("Total");
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
            JOptionPane.showMessageDialog(new JFrame(), E1);

        }
    }

    // --->>>>>

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
