import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelPracownika extends JFrame{
    private JPanel panelPracownika;
    private JComboBox comboBoxKsiazki;
    private JTextField wyszukajKsiazkeIDTXT;
    private JLabel brakKsiazki;
    private JButton wyszukajKsiazkeIDButton;
    private JComboBox comboBoxUzytkownicy;
    private JTextField wyszukajUzytkownikaIDTXT;
    private JButton wyszukajuzytkownikaIDButton;
    private JButton dodajKsiazkeButton;
    private JButton wylogujButton;
    private JButton wybierzKsiazkeButton;
    private JButton wybierzUzytkownikaButton;
    private JTextField wyszukajUzytkownikaEmailTXT;
    private JButton wyszukajEmailButton;
    private JTextField wyszukajKsiazkeNazwaTXT;
    private JButton wyszukajKsiazkeNazwaButton;
    private JLabel Nazwa;
    private JLabel brakUzytkownika;

    private HashMap<String, DaneUzytkownika> hashmapa_uzytkownicy = new HashMap<String, DaneUzytkownika>();
    private DataBaseUzytkownicy dataBaseUzytkownicy = new DataBaseUzytkownicy();
    private DaneUzytkownika uzytkownik;

    private HashMap<String, Ksiazki> hashmapaKsiazki = new HashMap<String, Ksiazki>();
    private DataBaseKsiazki dataBaseKsiazki = new DataBaseKsiazki();
    private Ksiazki ksiazka;

    public JPanel getPanelPracownika() {
        return panelPracownika;
    }

    public PanelPracownika(){
        hashmapa_uzytkownicy = dataBaseUzytkownicy.wczytajUzytkownikow();
        for(DaneUzytkownika value: hashmapa_uzytkownicy.values())
            comboBoxUzytkownicy.addItem(value);

        hashmapaKsiazki = dataBaseKsiazki.wczytajKsiazki();
        for(Ksiazki value: hashmapaKsiazki.values())
            comboBoxKsiazki.addItem(value);

        refresh();

        wybierzKsiazkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ksiazka = (Ksiazki) comboBoxKsiazki.getSelectedItem();
                dispose();
                PanelKsiazkiPracownik frame = new PanelKsiazkiPracownik(ksiazka.getIdKsiazki());
                frame.setContentPane(frame.getPanelKsiPra());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        wyszukajKsiazkeIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ksiazka = dataBaseKsiazki.szukajPoID(hashmapaKsiazki, wyszukajKsiazkeIDTXT.getText());
                if(ksiazka.getIdKsiazki().equals("")) {
                    brakKsiazki.setVisible(true);
                }
                else {
                    dispose();
                    PanelKsiazkiPracownik frame = new PanelKsiazkiPracownik(ksiazka.getIdKsiazki());
                    frame.setContentPane(frame.getPanelKsiPra());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(true);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                    frame.setSize(800,600);
                    frame.setVisible(true);
                }
            }
        });

        wyszukajKsiazkeNazwaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!wyszukajKsiazkeNazwaTXT.getText().equals("")) {
                    ArrayList<Ksiazki> listaKsiazekx = dataBaseKsiazki.szukajListyKsiazekPoNazwie(hashmapaKsiazki, wyszukajKsiazkeNazwaTXT.getText());
                    if (listaKsiazekx != null) {
                        dispose();
                        KsiazkaPoNazwie frame = new KsiazkaPoNazwie(listaKsiazekx);
                        frame.setContentPane(frame.getKsiazkaPoNazwie());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setResizable(true);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                        frame.setSize(800,600);
                        frame.setVisible(true);
                    } else
                        brakKsiazki.setVisible(true);
                }
                else
                    brakKsiazki.setVisible(true);
            }
        });

        wybierzUzytkownikaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uzytkownik = (DaneUzytkownika) comboBoxUzytkownicy.getSelectedItem();
                dispose();
                PanelUzytkownikaPracownik frame = new PanelUzytkownikaPracownik(uzytkownik);
                frame.setContentPane(frame.getPanelUzyPraco());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        wyszukajuzytkownikaIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uzytkownik = dataBaseUzytkownicy.szukajPoID(hashmapa_uzytkownicy, wyszukajUzytkownikaIDTXT.getText());
                if(uzytkownik.getIdUzytkownika().equals("")) {
                    brakUzytkownika.setVisible(true);
                }
                else{
                    dispose();
                    PanelUzytkownikaPracownik frame = new PanelUzytkownikaPracownik(uzytkownik);
                    frame.setContentPane(frame.getPanelUzyPraco());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(true);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                    frame.setSize(800,600);
                    frame.setVisible(true);
                }
            }
        });

        wyszukajEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uzytkownik = dataBaseUzytkownicy.szukajPoEmailu(hashmapa_uzytkownicy, wyszukajUzytkownikaEmailTXT.getText());
                if(uzytkownik.getIdUzytkownika().equals("")) {
                    brakUzytkownika.setVisible(true);
                }
                else{
                    dispose();
                    PanelUzytkownikaPracownik frame = new PanelUzytkownikaPracownik(uzytkownik);
                    frame.setContentPane(frame.getPanelUzyPraco());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(true);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                    frame.setSize(800,600);
                    frame.setVisible(true);
                }
            }
        });

        dodajKsiazkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DodajKsiazkeDoSystemu frame = new DodajKsiazkeDoSystemu();
                frame.setContentPane(frame.getDodajKsiazke());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        wylogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu frame = new Menu();
                frame.setContentPane(frame.getMenu());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });
    }

    public void refresh(){
        brakKsiazki.setVisible(false);
        brakUzytkownika.setVisible(false);
    }
}
