import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PanelKsiazkiPracownik extends JFrame {
    private JPanel panelKsiPra;
    private JButton zwrotButton;
    private JButton wypozyczButton;
    private JButton wrocButton;
    private JLabel nazwaKsiazki;
    private JLabel autorKsiazki;
    private JLabel dataWydania;
    private JLabel Stan;
    private JTextField wypozyczPrzezID;
    private JLabel StanPrzezID;
    private JLabel dataWypozycz;
    private JLabel dataZwrotu;
    private JLabel blad;

    private HashMap<String, Ksiazki> hashmapaKsiazki = new HashMap<String, Ksiazki>();
    private DataBaseKsiazki dataBaseKsiazki = new DataBaseKsiazki();
    private Ksiazki ksiazka;

    private HashMap<String, WypozyczoneKsiazki> hashmapaWypozyczoneKsiazki = new HashMap<String, WypozyczoneKsiazki>();
    private DataBaseWypozyczoneKsiazki dataBaseWypozyczoneKsiazki = new DataBaseWypozyczoneKsiazki();
    private WypozyczoneKsiazki wypozyczoneKsiazki;

    private HashMap<String, DaneUzytkownika> hashmapa_uzytkownicy = new HashMap<String, DaneUzytkownika>();
    private DataBaseUzytkownicy dataBaseUzytkownicy = new DataBaseUzytkownicy();
    private DaneUzytkownika uzytkownik;

    KomunikatyBledow komunikatyBledow;

    private Daty daty = new Daty();

    public PanelKsiazkiPracownik(String ksiazkaID) {
        refresh(ksiazkaID);
        nazwaKsiazki.setText(ksiazka.getNazwaKs());
        autorKsiazki.setText(ksiazka.getImieAutora() + " " + ksiazka.getNazwAutora());
        dataWydania.setText(ksiazka.getDataWydania());

        hashmapa_uzytkownicy = dataBaseUzytkownicy.wczytajUzytkownikow();

        zwrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ksiazka.getStan().equals("wypozyczona") || ksiazka.getStan().equals("zarezerwowana")) {
                    dataBaseWypozyczoneKsiazki.fixPusteLinie();
                    dataBaseKsiazki.zmianaStanuKsiazki(ksiazka.getIdKsiazki(), "dostepna");
                    dataBaseWypozyczoneKsiazki.usunZWypozyczonych(ksiazka.getIdKsiazki());
                }
                else
                    blad.setVisible(true);

                refresh(ksiazkaID);
            }
        });
        wypozyczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ksiazka.getStan().equals("dostepna")) {
                    uzytkownik = dataBaseUzytkownicy.szukajPoID(hashmapa_uzytkownicy, wypozyczPrzezID.getText());
                    if (!uzytkownik.getIdUzytkownika().equals("")) {
                        dataBaseKsiazki.zmianaStanuKsiazki(ksiazka.getIdKsiazki(), "wypozyczona");
                        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        //Date data = new Date();
                        //Date dataOddania = new Date(new Date().getTime() + (14 * 86400000));
                        WypozyczoneKsiazki wypozyczoneKsiazki = new WypozyczoneKsiazki(ksiazka.getIdKsiazki(), ksiazka.getNazwaKs(), ksiazka.getImieAutora(),
                                ksiazka.getNazwAutora(), ksiazka.getDataWydania(), "wypozyczona", daty.getObecnaData(), daty.getDataKoncaWypozyczenia(),
                                wypozyczPrzezID.getText());
                        try {
                            dataBaseWypozyczoneKsiazki.dodajDoBazyWypozyczonychKsiazektxt(wypozyczoneKsiazki.wszystkoDoStringa());
                        } catch (Exception blad) {
                            komunikatyBledow.loggerIn(String.valueOf(blad));
                        }
                        refresh(ksiazkaID);
                    } else
                        blad.setVisible(true);
                }else
                    blad.setVisible(true);


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
                //frame.pack();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });
    }

    public void refresh(String ksiazkaID){
        blad.setVisible(false);

        hashmapaWypozyczoneKsiazki = dataBaseWypozyczoneKsiazki.wczytajWypozyczoneKsiazki();
        wypozyczoneKsiazki = dataBaseWypozyczoneKsiazki.szukajPoID(hashmapaWypozyczoneKsiazki, ksiazkaID);

        hashmapaKsiazki = dataBaseKsiazki.wczytajKsiazki();
        ksiazka = dataBaseKsiazki.szukajPoID(hashmapaKsiazki, ksiazkaID);

        if(ksiazka.getStan().equals("dostepna")){
            Stan.setText("Dostępna");
            StanPrzezID.setText("----");
            dataWypozycz.setText("----");
            dataZwrotu.setText("----");
        }
        else if(ksiazka.getStan().equals("wypozyczona")){
            Stan.setText("Wypożyczona");
            StanPrzezID.setText(wypozyczoneKsiazki.getIdUzytkownika());
            dataWypozycz.setText(wypozyczoneKsiazki.getDataBiezaca());
            dataZwrotu.setText(wypozyczoneKsiazki.getDataOddania());
        }
        else{
            Stan.setText("Zarezerwowana");
            StanPrzezID.setText(wypozyczoneKsiazki.getIdUzytkownika());
            dataWypozycz.setText(wypozyczoneKsiazki.getDataBiezaca());
            dataZwrotu.setText(wypozyczoneKsiazki.getDataOddania());
        }
    }

    public JPanel getPanelKsiPra() {
        return panelKsiPra;
    }
}
