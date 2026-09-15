package Package;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.extend_Login();

                // Home home = new Home();
                // home.extend_Home();


            }
        });

    }
}
