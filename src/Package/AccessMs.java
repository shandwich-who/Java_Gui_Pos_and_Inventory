package Package;

import java.sql.*;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AccessMs {

    private Connection connect_1;
    private Connection connect_2;

    public AccessMs() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            // Use relative paths instead of hardcoded absolute paths
            String dbPath1 = new File("Database1.accdb").getAbsolutePath();
            String dbPath2 = new File("Database2.accdb").getAbsolutePath();
            
            connect_1 = DriverManager.getConnection("jdbc:ucanaccess://" + dbPath1);
            connect_2 = DriverManager.getConnection("jdbc:ucanaccess://" + dbPath2);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Database Connection Error: " + e.getMessage());
        }
    }

    public Connection getConnection1() {
        return connect_1;
    }

    public Connection getConnection2() {
        return connect_2;
    }
    
    public void closeConnections() {
        try {
            if (connect_1 != null && !connect_1.isClosed()) {
                connect_1.close();
            }
            if (connect_2 != null && !connect_2.isClosed()) {
                connect_2.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
