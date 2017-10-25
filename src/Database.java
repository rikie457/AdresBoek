import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Vector;

/**
 * Created by Tycho on 10-3-2017.
 */
public class Database extends JFrame {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/contacten";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER

            );

            System.out.println("Verbinden met database...");
            System.out.println("---------------------");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Connectie overgegeven!");
        System.out.println("---------------------");
        return conn;

    }

    public static ResultSet verkrijg(Connection conn, String sql) throws SQLException {
        Statement stmt = null;

        System.out.println("Statement uitvoeren...");
        System.out.println("---------------------");
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);


        return rs;

    }

    public static void verzend(Connection conn, String sql) throws SQLException {
        Statement stmt = null;

        System.out.println("Statement uitvoeren...");
        System.out.println("---------------------");
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("Waardes verzonden!");
        System.out.println("---------------------");
    }

    public static void deconnect(Connection conn) throws SQLException {
        conn.close();
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            throw se;
        }
        System.out.println("Connectie afgesloten!");
    }

    public static Vector<String> verkrijgRijData() {
        Vector<String> Data = null;
        int Rij = TabelPaneel.Tabel.getSelectedRow();
        if (Rij != -1) {
            int aantalKolommen = TabelPaneel.Tabel.getColumnCount();
            Data = new Vector<String>();
            for (int i = 0; i < aantalKolommen; i++) {
                Data.add(TabelPaneel.Tabel.getValueAt(Rij, i).toString());
            }

        } else {
            Data = null;
        }
        return Data;
    }

    public static void verzendMetQuery(JFrame frame, Connection conn, String Id, String Voornaam, String Achternaam, String Postcode, String Adres, String Plaats, String Provincie, String Email, String Telefoonnummer) {
        if (Id.isEmpty()) {
            try {
                String sql = "INSERT INTO contact(Voornaam, Achternaam, Postcode, Adres, Plaats, Provincie, EMail, Telefoonnummer) VALUES ('" + Voornaam + "','" + Achternaam + "','" + Postcode + "','" + Adres + "','" + Plaats + "','" + Provincie + "','" + Email + "','" + Telefoonnummer + "');";
                Database.verzend(conn, sql);
                JOptionPane.showMessageDialog(frame, "Gegevens zijn verzonden!");

                Paneel.Idinput.setText("");
                Paneel.Voornaaminput.setText("");
                Paneel.Achternaaminput.setText("");
                Paneel.Postcodeinput.setText("");
                Paneel.Adresinput.setText("");
                Paneel.Plaatsinput.setText("");
                Paneel.Provincieinput.setText("");
                Paneel.Emailinput.setText("");
                Paneel.Telefoonnummerinput.setText("");

            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else {
            try {
                String sql = "UPDATE contact SET Voornaam = '" + Voornaam + "', Achternaam = '" + Achternaam + "', Postcode = '" + Postcode + "', Adres = '" + Adres + "', Plaats = '" + Plaats + "', Provincie =  '" + Provincie + "', Email = '" + Email + "', Telefoonnummer = '" + Telefoonnummer + "' WHERE contactid = '" + Id + "';";
                Database.verzend(conn, sql);
                JOptionPane.showMessageDialog(frame, "Gegevens zijn aangepast!");

                Paneel.Idinput.setText("");
                Paneel.Voornaaminput.setText("");
                Paneel.Achternaaminput.setText("");
                Paneel.Postcodeinput.setText("");
                Paneel.Adresinput.setText("");
                Paneel.Plaatsinput.setText("");
                Paneel.Provincieinput.setText("");
                Paneel.Emailinput.setText("");
                Paneel.Telefoonnummerinput.setText("");
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static boolean verwijder(Connection conn, int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM contact WHERE Contactid =" + id + ";");
            stmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return false;
        }
        return true;
    }

}

