import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Vector;

/**
 * Created by Tycho on 24-3-2017.
 */
public class TabelPaneel extends JPanel {
    public static JPanel inputs, mainpanel, tabelpanel;
    public static JTable Tabel;
    private final JLabel Instructielabel, Voornaamlabel, Achternaamlabel;
    public static JTextField Zoekvoornaaminput, Zoekachternaaminput;
    private JScrollPane tabelScroll;
    private JButton Zoekknop, Refreshknop;


    public TabelPaneel(Connection conn, JFrame frame) {
        mainpanel = new JPanel();
        inputs = new JPanel();
        tabelpanel = new JPanel();

        setLayout(new GridBagLayout());
        GridBagConstraints cb = new GridBagConstraints();

        mainpanel.setLayout(new BorderLayout());
        tabelpanel.setLayout(new BorderLayout());
        inputs.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Instructielabel = new JLabel("Vul hieronder een Voornaam en Achternaam in!");
        c.gridx = 1;
        c.gridy = 0;
        inputs.add(Instructielabel, c);

        Voornaamlabel = new JLabel("Voornaam: ");
        c.gridx = 0;
        c.gridy = 1;
        inputs.add(Voornaamlabel, c);

        Zoekvoornaaminput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        inputs.add(Zoekvoornaaminput, c);

        Achternaamlabel = new JLabel("Achternaam: ");
        c.gridx = 0;
        c.gridy = 2;
        inputs.add(Achternaamlabel, c);

        Zoekachternaaminput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 2;
        inputs.add(Zoekachternaaminput, c);

        Zoekknop = new JButton("Zoeken");
        c.gridx = 2;
        c.gridy = 2;
        inputs.add(Zoekknop, c);
        Zoekknop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String vv = Zoekvoornaaminput.getText();
                String av = Zoekachternaaminput.getText();
                if (vv.equals("") || av.equals("")) {
                    JOptionPane.showMessageDialog(frame, "U moet wel iets invoeren");
                } else {
                    DefaultTableModel model = Zoekvenster.getTableModel(conn, vv, av);
                    Tabel.setModel(model);


                    Paneel.Idinput.setText("");
                    Paneel.Voornaaminput.setText("");
                    Paneel.Achternaaminput.setText("");
                    Paneel.Postcodeinput.setText("");
                    Paneel.Adresinput.setText("");
                    Paneel.Plaatsinput.setText("");
                    Paneel.Provincieinput.setText("");
                    Paneel.Emailinput.setText("");
                    Paneel.Telefoonnummerinput.setText("");
                }
            }
        });

        Refreshknop = new JButton("Refresh");
        c.gridx = 3;
        c.gridy = 2;
        inputs.add(Refreshknop, c);

        Refreshknop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String vv = Zoekvoornaaminput.getText();
                String av = Zoekachternaaminput.getText();

                DefaultTableModel model = Zoekvenster.getTableModel(conn, vv, av);

                Paneel.Idinput.setText("");
                Paneel.Voornaaminput.setText("");
                Paneel.Achternaaminput.setText("");
                Paneel.Postcodeinput.setText("");
                Paneel.Adresinput.setText("");
                Paneel.Plaatsinput.setText("");
                Paneel.Provincieinput.setText("");
                Paneel.Emailinput.setText("");
                Paneel.Telefoonnummerinput.setText("");

                TabelPaneel.Tabel.clearSelection();
                Tabel.setModel(model);


            }
        });


        DefaultTableModel model = Zoekvenster.getTableModel(conn, Zoekvoornaaminput.getText(), Zoekachternaaminput.getText());
        Tabel = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Tabel.setAutoResizeMode(Tabel.AUTO_RESIZE_ALL_COLUMNS);
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Geklikt op tabel");
                System.out.println("---------------------");
                Paneel.Veranderknop.setVisible(true);
                Paneel.Nieuwknop.setVisible(false);
                Vector<String> veranderData = Database.verkrijgRijData();


                Paneel.Idinput.setText(veranderData.get(0));
                Paneel.Voornaaminput.setText(veranderData.get(1));
                Paneel.Achternaaminput.setText(veranderData.get(2));
                Paneel.Postcodeinput.setText(veranderData.get(3));
                Paneel.Adresinput.setText(veranderData.get(4));
                Paneel.Plaatsinput.setText(veranderData.get(5));
                Paneel.Provincieinput.setText(veranderData.get(6));
                Paneel.Emailinput.setText(veranderData.get(7));
                Paneel.Telefoonnummerinput.setText(veranderData.get(8));
            }

        });
        tabelScroll = new JScrollPane(Tabel);


        cb.insets = new Insets(10, 10, 10, 10);
        cb.gridx = 0;
        cb.gridy = 0;

        add(inputs, cb);

        cb.gridx = 0;
        cb.gridy = 1;

        add(tabelScroll, cb);


    }
}