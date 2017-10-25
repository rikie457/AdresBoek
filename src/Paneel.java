/**
 * Created by Tycho on 10-3-2017.
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.util.Vector;

public class Paneel extends JPanel {
    private static JLabel Id, Voornaam, Achternaam, Postcode, Adres, Plaats, Provincie, Email, Telefoonnummer, Voornaamlabel, Achternaamlabel, Instructielabel;
    public static JButton Veranderknop, Verwijderknop, Nieuwknop, Leegknop;
    public static JTextField Idinput, Voornaaminput, Achternaaminput, Postcodeinput, Adresinput, Plaatsinput, Provincieinput, Emailinput, Telefoonnummerinput, Zoekvoornaaminput, Zoekachternaaminput;
    private static TabelPaneel Middenstuk;

    private static JPanel knopjes, formulier;

    public Paneel(Connection conn, JFrame frame) {

        setLayout(new BorderLayout());

        formulier = new JPanel();
        knopjes = new JPanel();

        formulier.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Id = new JLabel("Id:");
        c.gridx = 0;
        c.gridy = 0;
        formulier.add(Id, c);

        Voornaam = new JLabel("Voornaam: ");
        c.gridx = 0;
        c.gridy = 1;
        formulier.add(Voornaam, c);

        Achternaam = new JLabel("Achternaam: ");
        c.gridx = 0;
        c.gridy = 2;
        formulier.add(Achternaam, c);

        Postcode = new JLabel("Postcode: ");
        c.gridx = 0;
        c.gridy = 3;
        formulier.add(Postcode, c);

        Adres = new JLabel("Adres: ");
        c.gridx = 0;
        c.gridy = 4;
        formulier.add(Adres, c);

        Plaats = new JLabel("Plaats: ");
        c.gridx = 0;
        c.gridy = 5;
        formulier.add(Plaats, c);

        Provincie = new JLabel("Provincie: ");
        c.gridx = 0;
        c.gridy = 6;
        formulier.add(Provincie, c);

        Email = new JLabel("Emailadres: ");
        c.gridx = 0;
        c.gridy = 7;
        formulier.add(Email, c);

        Telefoonnummer = new JLabel("Telefoonummer: ");
        c.gridx = 0;
        c.gridy = 8;
        formulier.add(Telefoonnummer, c);


        Idinput = new JTextField(15);
        Idinput.setEditable(false);
        c.gridx = 1;
        c.gridy = 0;
        formulier.add(Idinput, c);

        Voornaaminput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        formulier.add(Voornaaminput, c);

        Achternaaminput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 2;
        formulier.add(Achternaaminput, c);

        Postcodeinput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 3;
        formulier.add(Postcodeinput, c);

        Adresinput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 4;
        formulier.add(Adresinput, c);

        Plaatsinput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 5;
        formulier.add(Plaatsinput, c);

        Provincieinput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 6;
        formulier.add(Provincieinput, c);

        Emailinput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 7;
        formulier.add(Emailinput, c);

        Telefoonnummerinput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 8;
        formulier.add(Telefoonnummerinput, c);


        knopjes.setLayout(new GridLayout(1, 4));

        Nieuwknop = new JButton("Toevoegen");
        knopjes.add(Nieuwknop);
        Nieuwknop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String idvalue = Idinput.getText();
                String voornaamvalue = Voornaaminput.getText();
                String achternaamvalue = Achternaaminput.getText();
                String postcodevalue = Postcodeinput.getText();
                String adresvalue = Adresinput.getText();
                String plaatsvalue = Plaatsinput.getText();
                String provincievalue = Provincieinput.getText();
                String emailvalue = Emailinput.getText();
                String telefoonnummervalue = Telefoonnummerinput.getText();

                if (voornaamvalue.equals("") || achternaamvalue.equals("") || postcodevalue.equals("") || adresvalue.equals("") || plaatsvalue.equals("") || provincievalue.equals("") || emailvalue.equals("") || telefoonnummervalue.equals("")) {
                    JOptionPane.showMessageDialog(frame, "U moet wel alle velden invullen");
                } else {
                    Database.verzendMetQuery(frame, conn, idvalue, voornaamvalue, achternaamvalue, postcodevalue, adresvalue, plaatsvalue, provincievalue, emailvalue, telefoonnummervalue);
                    DefaultTableModel model = Zoekvenster.getTableModel(conn, "", "");
                    TabelPaneel.Tabel.setModel(model);
                }
            }
        });

        Veranderknop = new

                JButton("Verander ");
        knopjes.add(Veranderknop);
        Veranderknop.setVisible(false);
        Veranderknop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idvalue = Idinput.getText();
                String voornaamvalue = Voornaaminput.getText();
                String achternaamvalue = Achternaaminput.getText();
                String postcodevalue = Postcodeinput.getText();
                String adresvalue = Adresinput.getText();
                String plaatsvalue = Plaatsinput.getText();
                String provincievalue = Provincieinput.getText();
                String emailvalue = Emailinput.getText();
                String telefoonnummervalue = Telefoonnummerinput.getText();

                if (idvalue.equals("") || voornaamvalue.equals("") || achternaamvalue.equals("") || postcodevalue.equals("") || adresvalue.equals("") || plaatsvalue.equals("") || provincievalue.equals("") || emailvalue.equals("") || telefoonnummervalue.equals("")) {
                    JOptionPane.showMessageDialog(frame, "U moet wel alle velden invullen");
                } else {
                    Veranderknop.setVisible(false);
                    Nieuwknop.setVisible(true);

                    Database.verzendMetQuery(frame, conn, idvalue, voornaamvalue, achternaamvalue, postcodevalue, adresvalue, plaatsvalue, provincievalue, emailvalue, telefoonnummervalue);
                    DefaultTableModel model = Zoekvenster.getTableModel(conn, "", "");
                    TabelPaneel.Tabel.setModel(model);

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

        Leegknop = new

                JButton("Leegmaken ");
        knopjes.add(Leegknop);
        Leegknop.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Veranderknop.setVisible(false);
                Nieuwknop.setVisible(true);
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

            }
        });

        Verwijderknop = new

                JButton("Verwijder");
        knopjes.add(Verwijderknop);
        Verwijderknop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> verwijderData = Database.verkrijgRijData();
                Veranderknop.setVisible(false);
                Nieuwknop.setVisible(true);

                if (verwijderData != null) {
                    Database.verwijder(conn, Integer.parseInt(verwijderData.get(0)));
                    DefaultTableModel model = Zoekvenster.getTableModel(conn, TabelPaneel.Zoekvoornaaminput.getText(), TabelPaneel.Zoekachternaaminput.getText());
                    TabelPaneel.Tabel.setModel(model);

                    Paneel.Idinput.setText("");
                    Paneel.Voornaaminput.setText("");
                    Paneel.Achternaaminput.setText("");
                    Paneel.Postcodeinput.setText("");
                    Paneel.Adresinput.setText("");
                    Paneel.Plaatsinput.setText("");
                    Paneel.Provincieinput.setText("");
                    Paneel.Emailinput.setText("");
                    Paneel.Telefoonnummerinput.setText("");

                } else {
                    JOptionPane.showMessageDialog(frame, "U heeft niks geselecteerd");
                }
            }
        });


        Middenstuk = new

                TabelPaneel(conn, frame);

        add(formulier, BorderLayout.CENTER);

        add(Middenstuk, BorderLayout.WEST);

        add(knopjes, BorderLayout.SOUTH);
    }
}