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
            return false; // Make all cells non-editable
        }
    };
    private final JTable table = new JTable(tableModel);

    private final JScrollPane scroll_pane = new JScrollPane(table);

    // -->>
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

        // range of label

    }

    private void tf_range() {
        // range of the text-field
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        itemCode_tf = new JTextField();
        itemCode_tf.setBounds(250, 50, 150, 30);
        itemCode_tf.setHorizontalAlignment(JTextField.CENTER);
        itemCode_tf.setBorder(border);
        itemCode_tf.setEnabled(false);
        add(itemCode_tf);
        itemCode_tf.setEnabled(false);
        itemCode_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number) || number == KeyEvent.VK_BACK_SPACE || number == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        itemDescription_tf = new JTextField();
        itemDescription_tf.setBounds(250, 100, 150, 30);
        itemDescription_tf.setHorizontalAlignment(JTextField.CENTER);
        itemDescription_tf.setBorder(border);
        add(itemDescription_tf);
        itemDescription_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char type = e.getKeyChar();
                if (!(Character.isLetter(type) || type == ' ')) {
                    e.consume();
                }

            }
        });

        price_tf = new JTextField();
        price_tf.setBounds(250, 150, 150, 30);
        price_tf.setHorizontalAlignment(JTextField.CENTER);
        price_tf.setBorder(border);
        add(price_tf);
        price_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number) || number == KeyEvent.VK_BACK_SPACE || number == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        size_tf = new JTextField();
        size_tf.setBounds(250, 200, 150, 30);
        size_tf.setHorizontalAlignment(JTextField.CENTER);
        size_tf.setBorder(border);
        add(size_tf);
        size_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char obj = e.getKeyChar();
                if (!(Character.isLetter(obj) || (Character.isDigit(obj)))) {
                    e.consume();
                }

            }
        });

        stocks_tf = new JTextField();
        stocks_tf.setBounds(250, 250, 150, 30);
        stocks_tf.setHorizontalAlignment(JTextField.CENTER);
        stocks_tf.setBorder(border);
        add(stocks_tf);
        stocks_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number) || number == KeyEvent.VK_BACK_SPACE || number == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        reOrderPoint_tf = new JTextField();
        reOrderPoint_tf.setBounds(250, 300, 150, 30);
        reOrderPoint_tf.setHorizontalAlignment(JTextField.CENTER);
        reOrderPoint_tf.setBorder(border);
        add(reOrderPoint_tf);
        reOrderPoint_tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number) || number == KeyEvent.VK_BACK_SPACE || number == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        item_text_field = new JTextField();
        item_text_field.setBounds(585, 15, 500, 20);
        item_text_field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(item_text_field);
        item_text_field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char number = e.getKeyChar();
                if (!(Character.isDigit(number)) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_BACK_SPACE) {
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.isActionKey();
                }

            }
        });

        // range of the text-field
    }

    // /* for the button process and function -->start
    private void button_area() {
        // stock in btn
        stock_in_btn.setBounds(70, 370, 100, 30);
        stock_in_btn.setFont(new Font("Arial", Font.BOLD, 13));
        stock_in_btn.setBackground(Color.white);
        stock_in_btn.setForeground(Color.decode("#198754"));
        stock_in_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        stock_in_btn.setFocusable(false);
        add(stock_in_btn);
        stock_in_btn.addActionListener(e -> {

            if (table.getSelectedRow() >= 0) {
                itemDescription_tf.setEnabled(false);
                price_tf.setEnabled(false);
                size_tf.setEnabled(false);
                stocks_tf.setEnabled(false);
                reOrderPoint_tf.setEnabled(false);

                String input_data = JOptionPane.showInputDialog(new JFrame(),
                        "Are you sure you want to update the stock? This action cannot be undone.", "Confirmation",
                        JOptionPane.WARNING_MESSAGE);

                try {
                    AccessMs accessMS = new AccessMs();
                    Connection connection = accessMS.getConnection1();
                    int number = Integer.parseInt(input_data);
                    // String Order_re = reOrderPoint_tf.getText();

                    String itm_C = itemCode_tf.getText();
                    String ItmD = itemDescription_tf.getText();
                    long ItmP = Long.parseLong(price_tf.getText());
                    Object ItmS = size_tf.getText();
                    long ItmStk = Long.parseLong(stocks_tf.getText());
                    long Rop = Long.parseLong(reOrderPoint_tf.getText());
                    String re_marks1 = "High Stocks";
                    String re_marks2 = "Low Stocks";

                    if (!input_data.isEmpty()) {
                        try {

                            if (number >= Rop) {
                                // tableModel.setValueAt("High Stocks", table.getSelectedRow(), 6);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "Update `table1` Set `Item Code` = ' " + itm_C + " ' ,`Item Description` = ' "
                                                + ItmD + " ', Price = ' " + ItmP + " ', Size = ' " + ItmS
                                                + " ', Stocks = ' " + (ItmStk + number) + " ',`Re-Order Point` = ' "
                                                + Rop + " ' , Remarks = '" + re_marks1 + "' Where `Item Code` = '"
                                                + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "' ");
                                int result = preparedStatement.executeUpdate();
                                data_refresh();

                                if (result > 0) {
                                    JOptionPane.showMessageDialog(new JFrame(), "Successfully updated ");

                                } else {

                                    JOptionPane.showMessageDialog(new JFrame(), result, "Message",
                                            JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "Update `table1` Set `Item Code` = ' " + itm_C + " ' ,`Item Description` = ' "
                                                + ItmD + " ', Price = ' " + ItmP + " ', Size = ' " + ItmS
                                                + " ', Stocks = ' " + (ItmStk + number) + " ',`Re-Order Point` = ' "
                                                + Rop + " ' , Remarks = '" + re_marks2 + "' Where `Item Code` = '"
                                                + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "' ");
                                int result = preparedStatement.executeUpdate();
                                data_refresh();

                                if (result > 0) {
                                    JOptionPane.showMessageDialog(new JFrame(), "Successfully updated ");

                                } else {

                                    JOptionPane.showMessageDialog(new JFrame(), result, "Message",
                                            JOptionPane.ERROR_MESSAGE);
                                }

                                // tableModel.setValueAt("Low Stock", table.getSelectedRow(), 6);
                            }

                            itemCode_tf.setText("");
                            itemDescription_tf.setText("");
                            price_tf.setText("");
                            size_tf.setText("");
                            stocks_tf.setText("");
                            reOrderPoint_tf.setText("");

                            table.clearSelection();
                            table.requestFocusInWindow();

                            itemDescription_tf.setEnabled(true);
                            price_tf.setEnabled(true);
                            size_tf.setEnabled(true);
                            stocks_tf.setEnabled(true);
                            reOrderPoint_tf.setEnabled(true);

                            add_btn.setEnabled(true);
                            add_btn.setBackground(Color.decode("#198754"));
                            add_btn.setForeground(Color.WHITE);
                            add_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));

                        } catch (NumberFormatException ignored) {
                            JOptionPane.showMessageDialog(new JFrame(), "Only number are required", "Message",
                                    JOptionPane.WARNING_MESSAGE);
                        }

                    } else {
                        itemCode_tf.setText("");
                        itemDescription_tf.setText("");
                        size_tf.setText("");
                        price_tf.setText("");
                        stocks_tf.setText("");
                        reOrderPoint_tf.setText("");
                    }

                } catch (Exception Ex) {
                    JOptionPane.showMessageDialog(new JFrame(), Ex.getMessage(), "MESSAGE", JOptionPane.ERROR_MESSAGE);
                } // to catch the connection

            } else {

                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "There are no items in the table. Please add data to perform this action.", "Message",
                            JOptionPane.ERROR_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(new JFrame(),
                            "This action requires a row in the table to be selected. Please select a row and try again.",
                            "Message", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        // add btn
        add_btn.setBounds(175, 370, 100, 30);
        add_btn.setFont(new Font("Arial", Font.BOLD, 13));
        add_btn.setFocusable(false);
        add_btn.setBackground(Color.decode("#198754"));
        add_btn.setForeground(Color.WHITE);
        add_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));
        add(add_btn);
        add_btn.addActionListener(e -> {
            // condition when you did not put in text field
            if (itemDescription_tf.getText().equals("") || price_tf.getText().equals("") || size_tf.getText().equals("")
                    || stocks_tf.getText().equals("") || reOrderPoint_tf.getText().equals("")) {

                JOptionPane.showMessageDialog(new JFrame(),
                        "I'm sorry, it seems like you have not filled in the required text field before clicking the button. Please fill in the necessary information before proceeding. Thank you.",
                        "Error", JOptionPane.ERROR_MESSAGE);

            } else {

                // condition for the remarks
                String stck = stocks_tf.getText();
                try {
                    AccessMs accessMS = new AccessMs();
                    Connection connection = accessMS.getConnection1();

                    String sql = "INSERT INTO Table1 ([Item Code], [Item Description], Price, Size, Stocks, [Re-Order Point], Remarks) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    String query = "SELECT MAX([Item Code]) FROM Table1";

                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        String highestCode = rs.getString(1);

                        String newCode = generateNextCode(highestCode);

                        itemCode_tf.setText(newCode);

                    } else {
                        // No existing codes found, generate the first code
                        String newCode = "0001";

                        itemCode_tf.setText(newCode);

                    }

                    PreparedStatement prepared_stm = connection.prepareStatement(sql);

                    if (Integer.parseInt(stck) >= 50) {
                        String[] data = { itemCode_tf.getText(), itemDescription_tf.getText(), price_tf.getText(),
                                size_tf.getText(), stocks_tf.getText(), reOrderPoint_tf.getText(), "High Stocks" };

                        prepared_stm.setString(1, data[0]);
                        prepared_stm.setString(2, data[1]);
                        prepared_stm.setString(3, data[2]);
                        prepared_stm.setString(4, data[3]);
                        prepared_stm.setString(5, data[4]);
                        prepared_stm.setString(6, data[5]);
                        prepared_stm.setString(7, data[6]);
                        prepared_stm.executeUpdate();
                        data_refresh();

                        JOptionPane.showMessageDialog(new JFrame(),
                                "Thank you! Your information has been successfully recorded.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);

                        itemCode_tf.setText("");
                        itemDescription_tf.setText("");
                        price_tf.setText("");
                        size_tf.setText("");
                        stocks_tf.setText("");
                        reOrderPoint_tf.setText("");

                    } else {
                        String[] data = { itemCode_tf.getText(), itemDescription_tf.getText(), price_tf.getText(),
                                size_tf.getText(), stocks_tf.getText(), reOrderPoint_tf.getText(), "Low Stock" };

                        prepared_stm.setString(1, data[0]);
                        prepared_stm.setString(2, data[1]);
                        prepared_stm.setString(3, data[2]);
                        prepared_stm.setString(4, data[3]);
                        prepared_stm.setString(5, data[4]);
                        prepared_stm.setString(6, data[5]);
                        prepared_stm.setString(7, data[6]);
                        prepared_stm.executeUpdate();
                        data_refresh();

                        JOptionPane.showMessageDialog(new JFrame(),
                                "Thank you! Your information has been successfully recorded.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);

                        itemCode_tf.setText("");
                        itemDescription_tf.setText("");
                        price_tf.setText("");
                        size_tf.setText("");
                        stocks_tf.setText("");
                        reOrderPoint_tf.setText("");

                    } // end of remarks

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex, "", JOptionPane.ERROR_MESSAGE);
                }

            } // the end of the condition of putting text field item and adding them in j
            // table

        });

        // edit btn --> start
        edit_btn.setBounds(280, 370, 100, 30);
        edit_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        edit_btn.setFocusable(false);
        edit_btn.setBackground(Color.white);
        edit_btn.setForeground(Color.black);
        edit_btn.setBorder(BorderFactory.createLineBorder(Color.black));
        add(edit_btn);
        edit_btn.addActionListener(e -> {
            if (table.getSelectedRow() >= 0) {

                add_btn.setEnabled(false);
                add_btn.setBackground(Color.white);
                add_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

                delete_btn.setEnabled(false);
                delete_btn.setBackground(Color.white);
                delete_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

                stock_in_btn.setEnabled(false);
                stock_in_btn.setBackground(Color.white);
                stock_in_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

                save_btn.setEnabled(true);
                save_btn.setBackground(Color.decode("#0d6efd"));
                save_btn.setForeground(Color.WHITE);
                save_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));

                cancel_btn.setEnabled(true);
                cancel_btn.setBackground(Color.white);
                cancel_btn.setForeground(Color.decode("#0d6efd"));
                cancel_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#0d6efd")));

                itemDescription_tf.setEnabled(true);
                price_tf.setEnabled(true);
                size_tf.setEnabled(true);
                stocks_tf.setEnabled(false);
                reOrderPoint_tf.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Please Select Row to perform this action", "Message",
                        JOptionPane.ERROR_MESSAGE);
            }

        });// end of edit btn

        // delete btn -->start
        delete_btn.setBounds(70, 420, 100, 30);
        delete_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        delete_btn.setFocusable(false);
        delete_btn.setBackground(Color.decode("#dc3545"));
        delete_btn.setForeground(Color.white);
        delete_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#dc3545")));
        add(delete_btn);
        delete_btn.addActionListener(e -> {

            if (table.getSelectedRow() >= 0) {
                try {
                    AccessMs accessMS = new AccessMs();
                    Connection connection = accessMS.getConnection1();

                    JOptionPane.showMessageDialog(new JFrame(), "Think twice before proceeding!",
                            "Warning: Deleting The  Data", JOptionPane.WARNING_MESSAGE);

                    int msg = JOptionPane.showConfirmDialog(new JFrame(),
                            "Deleting this item will remove it permanently. Are you sure you want to proceed?",
                            "Warning!!!", JOptionPane.YES_NO_OPTION);

                    if (msg == JOptionPane.YES_OPTION) {

                        PreparedStatement preparedStatement = connection
                                .prepareStatement("Delete From Table1 Where `Item Code` = '"
                                        + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "' ");
                        int result = preparedStatement.executeUpdate();
                        data_refresh();
                        if (result > 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Successfully Deleted");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Failed to remove the Item");

                        }

                    }
                    itemCode_tf.setText("");
                    itemDescription_tf.setText("");
                    price_tf.setText("");
                    size_tf.setText("");// clear the text in text-field
                    stocks_tf.setText("");
                    reOrderPoint_tf.setText("");

                    table.clearSelection();// to clear the selected row
                    table.requestFocusInWindow();// ^

                    itemDescription_tf.setEnabled(true);// -->
                    price_tf.setEnabled(true);// --> the function of this to activate tf
                    size_tf.setEnabled(true);// --> the function of this to activate tf
                    stocks_tf.setEnabled(true);// -->
                    reOrderPoint_tf.setEnabled(true);// -->

                    add_btn.setEnabled(true);
                    add_btn.setBackground(Color.decode("#198754"));
                    add_btn.setForeground(Color.WHITE);
                    add_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));

                } catch (Exception E) {
                    JOptionPane.showMessageDialog(new JFrame(), E.getMessage());
                }

            } else {
                if (table.getRowCount() == -1) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "There are no items in the table. Please add data to perform this action.", "Message",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "This action requires a row in the table to be selected. Please select a row and try again.",
                            "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });// end of the edit button

        // save btn -->> start
        save_btn.setBounds(175, 420, 100, 30);
        save_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        save_btn.setEnabled(false);
        save_btn.setFocusable(false);
        save_btn.setBackground(Color.white);
        save_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

        add(save_btn);
        save_btn.addActionListener(e1 -> {

            try {
                AccessMs accessMS = new AccessMs();
                Connection connection = accessMS.getConnection1();

                String itm_C = itemCode_tf.getText();
                String ItmD = itemDescription_tf.getText();
                long ItmP = Long.parseLong(price_tf.getText());
                Object ItmS = size_tf.getText();
                long ItmStk = Long.parseLong(stocks_tf.getText());
                long Rop = Long.parseLong(reOrderPoint_tf.getText());
                String re_marks1 = "High Stocks";
                String re_marks2 = "Low Stocks";

                if (Integer.parseInt(stocks_tf.getText()) >= Integer.parseInt(reOrderPoint_tf.getText())) {
                    // tableModel.setValueAt("High Stocks", table.getSelectedRow(), 6);

                    PreparedStatement preparedStatement = connection
                            .prepareStatement("Update `table1` Set `Item Code` = ' " + itm_C
                                    + " ' ,`Item Description` = ' " + ItmD + " ', Price = ' " + ItmP + " ', Size = ' "
                                    + ItmS + " ', Stocks = ' " + ItmStk + " ',`Re-Order Point` = ' " + Rop
                                    + " ' , Remarks = '" + re_marks1 + "' Where `Item Code` = '"
                                    + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "' ");

                    int affect = preparedStatement.executeUpdate();

                    if (affect > 0) {
                        JOptionPane.showMessageDialog(new JFrame(), "Row Successfully Updated");




                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "No Row Updated", "Message",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    // tableModel.setValueAt("Low Stock", table.getSelectedRow(), 6);
                    PreparedStatement preparedStatement = connection
                            .prepareStatement("Update `table1` Set `Item Code` = ' " + itm_C
                                    + " ' ,`Item Description` = ' " + ItmD + " ', Price = ' " + ItmP + " ', Size = ' "
                                    + ItmS.toString() + " ', Stocks = ' " + ItmStk + " ',`Re-Order Point` = ' " + Rop
                                    + " ' , Remarks = '" + re_marks2 + "' Where `Item Code` = '"
                                    + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "' ");

                    int affect = preparedStatement.executeUpdate();

                    if (affect > 0) {
                        JOptionPane.showMessageDialog(new JFrame(), "Row Successfully Updated");

                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "No Row Updated", "Message",
                                JOptionPane.WARNING_MESSAGE);

                    }

                }

                data_refresh();// --->refresh after confirming the save button
                // /*
                itemCode_tf.setText("");
                itemDescription_tf.setText("");
                price_tf.setText("");
                size_tf.setText("");
                stocks_tf.setText("");
                reOrderPoint_tf.setText("");
                // */ clear the text in press the yes
                // /*
                table.clearSelection();
                table.requestFocusInWindow();
                // */ clear the selected row in table and will focus in frame
                stocks_tf.setEnabled(true);// the disable stock tf will enable

                delete_btn.setEnabled(true); // --->
                delete_btn.setBackground(Color.decode("#dc3545"));
                delete_btn.setForeground(Color.white);
                delete_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#dc3545")));


                stock_in_btn.setEnabled(true);// ----> activate the again
                stock_in_btn.setBackground(Color.white);
                stock_in_btn.setForeground(Color.decode("#198754"));
                stock_in_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));


                add_btn.setEnabled(true);// ------>
                add_btn.setBackground(Color.decode("#198754"));
                add_btn.setForeground(Color.WHITE);
                add_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));


                save_btn.setEnabled(false);// ------>
                save_btn.setBackground(Color.white);
                save_btn.setBorder(BorderFactory.createLineBorder(Color.gray));


                cancel_btn.setEnabled(false);// ------> will disable the button again
                cancel_btn.setBackground(Color.white);
                cancel_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

            } catch (Exception Ex) {
                Ex.printStackTrace();
            }

        });// --->end of the save button

        // cancel btn
        cancel_btn.setBounds(280, 420, 100, 30);
        cancel_btn.setFont(new Font("JetBrains", Font.BOLD, 13));
        cancel_btn.setEnabled(false);
        cancel_btn.setFocusable(false);
        cancel_btn.setBackground(Color.white);
        add(cancel_btn);

        cancel_btn.addActionListener(e12 -> {

            int msg = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to cancel? Your changes will not be saved.", "", JOptionPane.YES_NO_OPTION);
            if (msg == JOptionPane.YES_OPTION) {
                // /*
                itemCode_tf.setText("");
                itemDescription_tf.setText("");
                price_tf.setText("");
                size_tf.setText("");
                stocks_tf.setText("");
                reOrderPoint_tf.setText("");
                // */ clear the text in press the yes
                // /*
                table.clearSelection();
                table.requestFocusInWindow();
                // */ clear the selected row in table and will focus in frame
                stocks_tf.setEnabled(true);// the disable stock tf will enable

                delete_btn.setEnabled(true); // --->
                delete_btn.setBackground(Color.decode("#dc3545"));
                delete_btn.setForeground(Color.white);
                delete_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#dc3545")));


                stock_in_btn.setEnabled(true);// ----> activate the again
                stock_in_btn.setBackground(Color.white);
                stock_in_btn.setForeground(Color.decode("#198754"));
                stock_in_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));


                add_btn.setEnabled(true);// ------>
                add_btn.setBackground(Color.decode("#198754"));
                add_btn.setForeground(Color.WHITE);
                add_btn.setBorder(BorderFactory.createLineBorder(Color.decode("#198754")));


                save_btn.setEnabled(false);// ------>
                save_btn.setBackground(Color.white);
                save_btn.setBorder(BorderFactory.createLineBorder(Color.gray));


                cancel_btn.setEnabled(false);// ------> will disable the button again
                cancel_btn.setBackground(Color.white);
                cancel_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

            }

        });// end to btn cancel

    }// this is the end of range of button area

    // /* for the table and panel and the scroll pane default table model --->Start
    private void table_panel() {

        table.getTableHeader().setReorderingAllowed(false); // cannot move the order column
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // /* start
                String IC_column = tableModel.getValueAt(table.getSelectedRow(), 0).toString();// --->
                String ID_column = tableModel.getValueAt(table.getSelectedRow(), 1).toString();// --->
                String P_column = tableModel.getValueAt(table.getSelectedRow(), 2).toString();// --->
                String S_column = tableModel.getValueAt(table.getSelectedRow(), 3).toString();// ---> the function of
                // this is to get the
                // all data you click in
                // Jtable
                String ST_column = tableModel.getValueAt(table.getSelectedRow(), 4).toString();// ---> ^
                String RO_column = tableModel.getValueAt(table.getSelectedRow(), 5).toString();// ---> ^
                // */ end
                //
                itemCode_tf.setText(IC_column);// --->
                itemDescription_tf.setText(ID_column);// --->
                price_tf.setText(P_column);// --->
                size_tf.setText(S_column);// ---> this function is to set text the selected row in the table
                stocks_tf.setText(ST_column);// --->
                reOrderPoint_tf.setText(RO_column);// ---> ^
                //
                itemCode_tf.setEnabled(false);// --->
                itemDescription_tf.setEnabled(false);// --->
                price_tf.setEnabled(false);// --->
                size_tf.setEnabled(false);// this function is to disable all text field
                stocks_tf.setEnabled(false);//
                reOrderPoint_tf.setEnabled(false);// ^^^
                //
                add_btn.setEnabled(false);
                add_btn.setBackground(Color.white);
                add_btn.setBorder(BorderFactory.createLineBorder(Color.gray));

            }
        });// for the table condition


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        panel.setLayout(new BorderLayout());
        panel.setBounds(460, 50, 660, 430);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(scroll_pane);
        add(panel);

    }// */end

    // /* The function of this is to re fresh the add-in table from database to
    // table like real time -----> Start
    private void data_refresh() {
        int c;
        try {

            AccessMs accessMS = new AccessMs();
            Connection connection = accessMS.getConnection1();

            PreparedStatement preparedStatement = connection.prepareStatement("Select * From Table1");

            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            c = resultSetMetaData.getColumnCount();
            tableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<Object> vector = new Vector<>();
                for (int i = 0; i <= c; i++) {

                    vector.add(resultSet.getString("Item Code"));
                    vector.add(resultSet.getString("Item Description"));
                    vector.add(resultSet.getString("Price"));
                    vector.add(resultSet.getString("Size"));
                    vector.add(resultSet.getString("Stocks"));
                    vector.add(resultSet.getString("Re-Order Point"));
                    vector.add(resultSet.getString("Remarks"));
                }
                tableModel.addRow(vector);

            }

        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(new JFrame(), Ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }

    }// */ end

    // /* Start
    private static String generateNextCode(String currentCode) {
        if (currentCode == null) {
            return "0001";
        }

        // Remove leading zeros and convert to integer
        int code = Integer.parseInt(currentCode);

        // Increment the code by 1
        code++;

        // Convert back to string and add leading zeros if necessary

        return String.format("%0" + currentCode.length() + "d", code);
    }// */ End

    // ---->>

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
