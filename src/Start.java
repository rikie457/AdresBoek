import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;


public class Start extends JFrame {

    public static JFrame startScherm;

    public static void main(String[] args) {
        Database db = new Database();
        Connection conn = db.connect();

        startScherm = new JFrame("Adresboek");
        startScherm.setSize(800, 700);
        startScherm.setDefaultCloseOperation(startScherm.DO_NOTHING_ON_CLOSE);
        startScherm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopprocedure(startScherm, conn);
            }
        });
        startScherm.setContentPane(new Paneel(conn, startScherm));
        startScherm.setLocationRelativeTo(null);
        startScherm.setVisible(true);
        startScherm.setResizable(false);

    }

    private static void stopprocedure(JFrame mv, Connection conn) {
        try {
            Database.deconnect(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        mv.dispose();
        System.exit(0);

    }
}
