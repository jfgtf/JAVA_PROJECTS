import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelUzytkownikaPracownik extends JFrame{
    private JPanel PanelUzyPraco;
    private JComboBox comboBoxWypozyczone;
    private JComboBox comboBoxZarezerwowane;
    private JButton przejdzDoWypoButton;
    private JButton przejdzDoZareButton;
    private JLabel imie;
    private JLabel nazwisko;
    private JLabel email;
    private JLabel adres;
    private JLabel kodPocztowy;
    private JLabel telefon;
    private JButton wrocButton;

    private HashMap<String, WypozyczoneKsiazki> hashmapaWypozyczoneKsiazki = new HashMap<String, WypozyczoneKsiazki>();
    private DataBaseWypozyczoneKsiazki dataBaseWypozyczoneKsiazki = new DataBaseWypozyczoneKsiazki();
    private WypozyczoneKsiazki wypozyczoneKsiazki;

    private ArrayList<WypozyczoneKsiazki> wypozyczoneKsiazkiLista;
    private ArrayList<WypozyczoneKsiazki> zarezerwowaneKsiazkiLista;

    public PanelUzytkownikaPracownik(DaneUzytkownika uzytkownik) {
        imie.setText(uzytkownik.getImie());
        nazwisko.setText(uzytkownik.getNazwisko());
        email.setText(uzytkownik.getEmail());
        adres.setText(uzytkownik.getAdres());
        kodPocztowy.setText(uzytkownik.getKodPocztowy());
        telefon.setText(uzytkownik.getTelefon());

        hashmapaWypozyczoneKsiazki = dataBaseWypozyczoneKsiazki.wczytajWypozyczoneKsiazki();

        wypozyczoneKsiazkiLista = dataBaseWypozyczoneKsiazki.szukajWypoPoIdUzytkownika(hashmapaWypozyczoneKsiazki, uzytkownik.getIdUzytkownika());
        zarezerwowaneKsiazkiLista = dataBaseWypozyczoneKsiazki.szukajZarePoIdUzytkownika(hashmapaWypozyczoneKsiazki, uzytkownik.getIdUzytkownika());

        for(WypozyczoneKsiazki wypozyczoneKsiazki : wypozyczoneKsiazkiLista)
            comboBoxWypozyczone.addItem(wypozyczoneKsiazki);

        for(WypozyczoneKsiazki wypozyczoneKsiazki : zarezerwowaneKsiazkiLista)
            comboBoxZarezerwowane.addItem(wypozyczoneKsiazki);

        przejdzDoWypoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wypozyczoneKsiazki = (WypozyczoneKsiazki) comboBoxWypozyczone.getSelectedItem();
                dispose();
                PanelKsiazkiPracownik frame = new PanelKsiazkiPracownik(wypozyczoneKsiazki.getIdKsiazki());
                frame.setContentPane(frame.getPanelKsiPra());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        przejdzDoZareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wypozyczoneKsiazki = (WypozyczoneKsiazki) comboBoxZarezerwowane.getSelectedItem();
                dispose();
                PanelKsiazkiPracownik frame = new PanelKsiazkiPracownik(wypozyczoneKsiazki.getIdKsiazki());
                frame.setContentPane(frame.getPanelKsiPra());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        wrocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PanelPracownika frame = new PanelPracownika();
                frame.setContentPane(frame.getPanelPracownika());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanelUzyPraco() {
        return PanelUzyPraco;
    }
}
