import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Objects;
import java.util.Vector;
import java.util.Arrays;

/**
 * Created by Tycho on 12-3-2017.
 */
public class Zoekvenster {


    public static DefaultTableModel getTableModel(Connection conn, String Voornaam, String Achternaam) {
        DefaultTableModel model = null;
        String Query = null;
        try {
            if (Voornaam == null || Objects.equals(Voornaam, "") || Achternaam == null || Objects.equals(Achternaam, "")) {
                Query = "SELECT * FROM Contact";
            } else {
                Query = "SELECT * FROM Contact WHERE Voornaam = '" + Voornaam + "' AND Achternaam ='" + Achternaam + "';";
            }

            ResultSet rs = Database.verkrijg(conn, Query);
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            model = null;


            Vector<String> headers = new Vector<>();
            for (int h = 1; h <= colCount; h++) {
                headers.add(meta.getColumnName(h));
            }
            model = new DefaultTableModel(headers, 0);

            while (rs.next()) {


                Vector<String> records = new Vector<>();
                for (int i = 0; i < colCount; i++) {
                    records.add(rs.getString(i + 1));

                }
                model.addRow(records);


            }


        } catch (SQLException se) {
            se.printStackTrace();
        }
        return model;
    }
}

