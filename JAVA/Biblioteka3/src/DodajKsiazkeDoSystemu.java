import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DodajKsiazkeDoSystemu extends JFrame {
    private JTextField nazwaTXT;
    private JPanel dodajKsiazke;
    private JTextField imieTXT;
    private JTextField nazwiskoTXT;
    private JTextField dataTXT;
    private JButton dodajButton;
    private JButton powrotButton;
    private JLabel brakDanych;

    private HashMap<String, Ksiazki> hashmapaKsiazki = new HashMap<String, Ksiazki>();
    private DataBaseKsiazki dataBaseKsiazki = new DataBaseKsiazki();
    private Ksiazki nowaKsiazka;
    KomunikatyBledow komunikatyBledow;

    public DodajKsiazkeDoSystemu() {
        hashmapaKsiazki = dataBaseKsiazki.wczytajKsiazki();
        refresh();
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nowaKsiazka = new Ksiazki("","" ,"", "" ,"", "");
                if(nazwaTXT.getText().isEmpty() || imieTXT.getText().isEmpty() || nazwiskoTXT.getText().isEmpty() ||
                        dataTXT.getText().isEmpty()){
                    brakDanych.setVisible(true);
                }

                else{
                    nowaKsiazka = new Ksiazki(nowaKsiazka.generujIDKsiazki(hashmapaKsiazki, dataBaseKsiazki), nazwaTXT.getText(), imieTXT.getText(), nazwiskoTXT.getText(),
                            dataTXT.getText(), "dostepna");

                    try {
                        dataBaseKsiazki.dodajDoBazyKsiazektxt(nowaKsiazka.wszystkoDoStringa());
                    } catch (Exception blad) {
                        komunikatyBledow.loggerIn(String.valueOf(blad));

                    }
                }
            }
        });

        powrotButton.addActionListener(new ActionListener() {
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

    public void refresh(){
        brakDanych.setVisible(false);
    }

    public JPanel getDodajKsiazke() {
        return dodajKsiazke;
    }
}
