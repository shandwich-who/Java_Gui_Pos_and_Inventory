package Package;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AccessMs {

    private Connection connect_1;
    private Connection connect_2;

    public AccessMs() {
        // TODO Auto-generated constructor stub

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connect_1 = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Andrei Program/Documents/Project  Gui java/Project_system/Database1.accdb");
            connect_2 = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Andrei Program/Documents/Project  Gui java/Project_system/Database2.accdb");

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getStackTrace());

        }

    }

    public Connection getConnection1() {
        return connect_1;
    }

    public Connection getConnection2() {
        return connect_2;
    }

}
