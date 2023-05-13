import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelUzytkownika extends JFrame {
    private JPanel panelUzytkownika;
    private JLabel imie_nazwiskoTXT;
    private JComboBox comboBoxDostepne;
    private JButton zarezerwujButton;
    private JComboBox comboBoxZare;
    private JButton zwolnijButton;
    private JLabel dataOdZare;
    private JLabel dataDoZare;
    private JComboBox comboBoxWypo;
    private JLabel dataOdWypo;
    private JLabel dataDoWypo;
    private JButton wylogujButton;
    private JTextField szukajPoNazwieTXT;
    private JButton szukajButton;
    private JComboBox comboBoxKsiazkiPoNazwie;
    private JButton zarezerwujPoNazwieButton;
    private JLabel stanWybranej;
    private JLabel dataStanDo;

    private HashMap<String, Ksiazki> hashmapaKsiazki = new HashMap<String, Ksiazki>();
    private DataBaseKsiazki dataBaseKsiazki = new DataBaseKsiazki();
    private Ksiazki ksiazka;
    KomunikatyBledow komunikatyBledow;

    private HashMap<String, WypozyczoneKsiazki> hashmapaWypozyczoneKsiazki = new HashMap<String, WypozyczoneKsiazki>();
    private DataBaseWypozyczoneKsiazki dataBaseWypozyczoneKsiazki = new DataBaseWypozyczoneKsiazki();
    private WypozyczoneKsiazki wypozyczoneKsiazki;

    private ArrayList<WypozyczoneKsiazki> wypozyczoneKsiazkiLista;
    private ArrayList<WypozyczoneKsiazki> zarezerwowaneKsiazkiLista;
    private ArrayList<Ksiazki> listaKsiazek;

    private Daty daty = new Daty();

    public JPanel getPanelUzytkownika() {
        return panelUzytkownika;
    }

    public PanelUzytkownika(DaneUzytkownika uzytkownik){
        setContentPane(panelUzytkownika);
        imie_nazwiskoTXT.setText(uzytkownik.getImie() + " " + uzytkownik.getNazwisko());
        refresh(uzytkownik);

        zarezerwujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ksiazki ksiazkax = (Ksiazki) comboBoxDostepne.getSelectedItem();
                dataBaseKsiazki.zmianaStanuKsiazki(ksiazkax.getIdKsiazki(), "zarezerwowana");
                WypozyczoneKsiazki wypozyczoneKsiazki = new WypozyczoneKsiazki(ksiazkax.getIdKsiazki(), ksiazkax.getNazwaKs(), ksiazkax.getImieAutora(),
                        ksiazkax.getNazwAutora(), ksiazkax.getDataWydania(), "zarezerwowana", daty.getObecnaData(), daty.getDataKoncaWypozyczenia(),
                        uzytkownik.getIdUzytkownika());
                try {
                    dataBaseWypozyczoneKsiazki.dodajDoBazyWypozyczonychKsiazektxt(wypozyczoneKsiazki.wszystkoDoStringa());
                } catch (Exception blad) {
                    komunikatyBledow.loggerIn(String.valueOf(blad));
                }
                refresh(uzytkownik);
                refreshWyszukiwanie();
            }
        });
            zwolnijButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!comboBoxZare.equals("Zarezerwowane książki...")) {
                        try {
                            WypozyczoneKsiazki wypozyczonaKsiazka = (WypozyczoneKsiazki) comboBoxZare.getSelectedItem();
                            dataBaseWypozyczoneKsiazki.fixPusteLinie();
                            dataBaseKsiazki.zmianaStanuKsiazki(wypozyczonaKsiazka.getIdKsiazki(), "dostepna");
                            dataBaseWypozyczoneKsiazki.usunZWypozyczonych(wypozyczonaKsiazka.getIdKsiazki());
                        } catch (Exception blad) {
                            komunikatyBledow.loggerIn(String.valueOf(blad));
                        }
                    }
                    refresh(uzytkownik);
                    refreshWyszukiwanie();
                }
            });
        szukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshWyszukiwanie();
            }
        });
        zarezerwujPoNazwieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!comboBoxKsiazkiPoNazwie.getSelectedItem().equals("Wyszukiwanie...")) {
                    Ksiazki ksiazkaWyszukana = (Ksiazki) comboBoxKsiazkiPoNazwie.getSelectedItem();
                    if(ksiazkaWyszukana.getStan().equals("dostepna")) {
                        dataBaseKsiazki.zmianaStanuKsiazki(ksiazkaWyszukana.getIdKsiazki(), "zarezerwowana");
                        WypozyczoneKsiazki wypozyczoneKsiazki = new WypozyczoneKsiazki(ksiazkaWyszukana.getIdKsiazki(), ksiazkaWyszukana.getNazwaKs(),
                                ksiazkaWyszukana.getImieAutora(), ksiazkaWyszukana.getNazwAutora(), ksiazkaWyszukana.getDataWydania(), "zarezerwowana",
                                daty.getObecnaData(), daty.getDataKoncaWypozyczenia(), uzytkownik.getIdUzytkownika());
                        try {
                            dataBaseWypozyczoneKsiazki.dodajDoBazyWypozyczonychKsiazektxt(wypozyczoneKsiazki.wszystkoDoStringa());
                        } catch (Exception blad) {
                            komunikatyBledow.loggerIn(String.valueOf(blad));
                        }
                    }
                    refresh(uzytkownik);
                    refreshWyszukiwanie();
                }
            }
        });
        comboBoxKsiazkiPoNazwie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!comboBoxKsiazkiPoNazwie.getSelectedItem().equals("Wyszukiwanie...")){
                    Ksiazki ksiazkaWyszukana = (Ksiazki) comboBoxKsiazkiPoNazwie.getSelectedItem();
                    WypozyczoneKsiazki wypoKsiazkaWyszukana = dataBaseWypozyczoneKsiazki.szukajPoID(hashmapaWypozyczoneKsiazki, ksiazkaWyszukana.getIdKsiazki());
                    if(ksiazkaWyszukana.getStan().equals("dostepna")) {
                        stanWybranej.setText("Dostępna");
                        dataStanDo.setText("Data końca");
                    }
                    else if (ksiazkaWyszukana.getStan().equals("zarezerwowana")) {
                        stanWybranej.setText("Zarezerwowana");
                        dataStanDo.setText(wypoKsiazkaWyszukana.getDataOddania());
                    }
                    else if(ksiazkaWyszukana.getStan().equals("wypozyczona")) {
                        stanWybranej.setText("Wypożyczona");
                        dataStanDo.setText(wypoKsiazkaWyszukana.getDataOddania());
                    }
                }
                else {
                    stanWybranej.setText("Stan");
                    dataStanDo.setText("Data końca");
                }
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
        comboBoxZare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!comboBoxZare.getSelectedItem().equals("Zarezerwowane książki...")) {
                    WypozyczoneKsiazki zareKsiazka = (WypozyczoneKsiazki) comboBoxZare.getSelectedItem();
                    dataOdZare.setText(zareKsiazka.getDataBiezaca());
                    dataDoZare.setText(zareKsiazka.getDataOddania());
                }
                else{
                    dataOdZare.setText("Data rezerwacji");
                    dataDoZare.setText("Data końca rezerwacji");
                }
            }
        });
        comboBoxWypo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!comboBoxWypo.getSelectedItem().equals("Wypożyczone książki...")) {
                    WypozyczoneKsiazki wypoKsiazka = (WypozyczoneKsiazki) comboBoxWypo.getSelectedItem();
                    dataOdWypo.setText(wypoKsiazka.getDataBiezaca());
                    dataDoWypo.setText(wypoKsiazka.getDataOddania());
                }
                else{
                    dataOdWypo.setText("Data wypożyczenia");
                    dataDoWypo.setText("Data końca wypożyczenia");
                }
            }
        });
    }

    public void refresh(DaneUzytkownika uzytkownik) {
        stanWybranej.setText("Stan");
        comboBoxDostepne.removeAllItems();

        for(int i = comboBoxWypo.getItemCount() - 1; i >= 1; i--){
            comboBoxWypo.removeItemAt(i);
        }

        for(int i = comboBoxZare.getItemCount() - 1; i >= 1; i--){
            comboBoxZare.removeItemAt(i);
        }

        hashmapaWypozyczoneKsiazki = dataBaseWypozyczoneKsiazki.wczytajWypozyczoneKsiazki();
        hashmapaKsiazki = dataBaseKsiazki.wczytajKsiazki();

        for(Ksiazki value: hashmapaKsiazki.values()) {
            if(value.getStan().equals("dostepna")) {
                comboBoxDostepne.addItem(value);
            }
        }

        wypozyczoneKsiazkiLista = dataBaseWypozyczoneKsiazki.szukajWypoPoIdUzytkownika(hashmapaWypozyczoneKsiazki, uzytkownik.getIdUzytkownika());
        zarezerwowaneKsiazkiLista = dataBaseWypozyczoneKsiazki.szukajZarePoIdUzytkownika(hashmapaWypozyczoneKsiazki, uzytkownik.getIdUzytkownika());

        for(WypozyczoneKsiazki wypoks : wypozyczoneKsiazkiLista)
            comboBoxWypo.addItem(wypoks);

        for(WypozyczoneKsiazki zareks : zarezerwowaneKsiazkiLista)
            comboBoxZare.addItem(zareks);
    }

    public void refreshWyszukiwanie() {
        for(int i = comboBoxKsiazkiPoNazwie.getItemCount() - 1; i >= 1; i--){
            comboBoxKsiazkiPoNazwie.removeItemAt(i);
        }

        if(!szukajPoNazwieTXT.getText().equals("")) {
            listaKsiazek = dataBaseKsiazki.szukajListyKsiazekPoNazwie(hashmapaKsiazki, szukajPoNazwieTXT.getText());
            for(Ksiazki ksiazki : listaKsiazek){
                comboBoxKsiazkiPoNazwie.addItem(ksiazki);
            }
        }

    }
}
