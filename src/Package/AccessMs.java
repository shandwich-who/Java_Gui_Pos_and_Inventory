package Package;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AccessMs {

    private Connection connect_1;
    private Connection connect_2;

    public AccessMs() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // Absolute path calculated dynamically relative to the current working directory
            String projectPath = new File("").getAbsolutePath();
            
            // Assuming your databases are located in a 'database' folder inside your project root
            String db1Path = "jdbc:ucanaccess://" + projectPath + "/database/Database1.accdb";
            String db2Path = "jdbc:ucanaccess://" + projectPath + "/database/Database2.accdb";

            connect_1 = DriverManager.getConnection(db1Path);
            connect_2 = DriverManager.getConnection(db2Path);

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "UCanAccess JDBC Driver not found.\nError: " + e.getMessage(), 
                "Driver Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Database Connection Error:\n" + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
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
            if (connect_1 != null && !connect_1.isClosed()) connect_1.close();
            if (connect_2 != null && !connect_2.isClosed()) connect_2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}