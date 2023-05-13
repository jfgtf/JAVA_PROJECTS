import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SamochodGUI extends Thread {
    private JPanel GUI;

    private JButton samochodWłączButton;
    private JButton samochodWyłączButton;
    private JTextField nazwaSamochodTXT;
    private JTextField modelSamochodTXT;
    private JTextField nrrejSamocjodTXT;
    private JTextField wagaSamochodTXT;
    private JTextField biegSkrzyniaTXT;
    private JTextField obrotySilnikTXT;
    private JTextField stanSprzegloTXT;
    private JComboBox comboBoxSamochody;
    private JButton dodajSamochodButton;
    private JButton zwiększBiegButton;
    private JButton zmniejszBiegButton;
    private JButton dodajGazuButton;
    private JButton ujmijGazuButton;
    private JButton naciśnijSprzęgłoButton;
    private JButton zwolnijSprzęgłoButton;
    private JTextField predkSamochodTXT;
    private JTextField nazwaSkrzyniaTXT;
    private JTextField cenaSkrzyniaTXT;
    private JTextField wagaSkrzyniaTXT;
    private JTextField nazwaSilnikTXT;
    private JTextField cenaSilnikTXT;
    private JTextField wagaSilnikTXT;
    private JTextField nazwaSprzegloTXT;
    private JTextField cenaSprzegloTXT;
    private JTextField wagaSprzegloTXT;
    private JButton UsunSamochodButton;

    private JPanel mapa;
    private JLabel auto;

    private Samochod samochod;
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;

    public SamochodGUI() {
        silnik = new Silnik("benzyna", 300, 300, 9000);
        sprzeglo = new Sprzeglo("sprzeglo", 300, 300);
        skrzynia = new SkrzyniaBiegow("skrzynia", 300, 300, 6, sprzeglo);
        pozycja = new Pozycja(0, 0);
        samochod = new Samochod("Audi", "KR 123", "A4", 294, 100, 1, pozycja, silnik, skrzynia);
        comboBoxSamochody.addItem(samochod);
        refresh(samochod);

        samochodWłączButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.wlacz();
                refresh(samochod);
            }
        });

        samochodWyłączButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.wylacz();
                refresh(samochod);
            }
        });

        zwiększBiegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.getSkrzynia().zwiekszBieg();
                refresh(samochod);
            }
        });

        zmniejszBiegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.getSkrzynia().zmniejszBieg();
                refresh(samochod);
            }
        });

        naciśnijSprzęgłoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.getSkrzynia().getSprzeglo().wcisnij();
                refresh(samochod);
            }
        });

        zwolnijSprzęgłoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.getSkrzynia().getSprzeglo().zwolnij();
                refresh(samochod);
            }
        });

        dodajGazuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.zwiekszObroty(500);
                refresh(samochod);
            }
        });

        ujmijGazuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.zmniejszObroty(500);
                refresh(samochod);
            }
        });


        mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(samochod == null)
                    System.out.println("Dodaj samochód");

                else if (!samochod.getSkrzynia().getSprzeglo().getStanSprzegla())
                    samochod.jedzDo(new Pozycja(e.getX(), e.getY()));

                else
                    System.out.println("Wyłącz sprzęgło");
            }
        });
        start();

        dodajSamochodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new NowySamochodGUI(comboBoxSamochody);
                f.pack();
                f.setVisible(true);
                refresh(samochod);
            }
        });

        UsunSamochodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (samochod != null) {
                    samochod.interrupt();
                    comboBoxSamochody.removeItem(samochod);
                }
                refresh(samochod);
            }
        });

        comboBoxSamochody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod = (Samochod) comboBoxSamochody.getSelectedItem();
                refresh(samochod);
            }
        });
    }



    public void refresh(Samochod samochod){
        if(samochod != null) {
            nazwaSamochodTXT.setText(samochod.getNazwa());
            modelSamochodTXT.setText(samochod.getModel());
            nrrejSamocjodTXT.setText(samochod.getNrRejest());
            wagaSamochodTXT.setText(Double.toString(samochod.getWaga()));
            predkSamochodTXT.setText(Double.toString(samochod.getAktualnaPredkosc()));

            nazwaSkrzyniaTXT.setText(samochod.getSkrzynia().getNazwa());
            wagaSkrzyniaTXT.setText(Double.toString(samochod.getSkrzynia().getWaga()));
            cenaSkrzyniaTXT.setText(Double.toString(samochod.getSkrzynia().getCena()));
            biegSkrzyniaTXT.setText(Integer.toString(samochod.getSkrzynia().getAktaulnyBieg()));

            nazwaSilnikTXT.setText(samochod.getSilnik().getNazwa());
            wagaSilnikTXT.setText(Double.toString(samochod.getSilnik().getWaga()));
            cenaSilnikTXT.setText(Double.toString(samochod.getSilnik().getCena()));
            obrotySilnikTXT.setText(Double.toString(samochod.getSilnik().getObroty()));

            nazwaSprzegloTXT.setText(samochod.getSprzeglo().getNazwa());
            cenaSprzegloTXT.setText(Double.toString(samochod.getSprzeglo().getCena()));
            wagaSprzegloTXT.setText(Double.toString(samochod.getSprzeglo().getWaga()));
            stanSprzegloTXT.setText(samochod.getSprzeglo().getStanSprzeglaWyraz());

            auto.setVisible(true);
            auto.setLocation(((int) samochod.getObecnaPozycja().getX()), (int) samochod.getObecnaPozycja().getY());
        }
        else {
            nazwaSamochodTXT.setText("");
            modelSamochodTXT.setText("");
            nrrejSamocjodTXT.setText("");
            wagaSamochodTXT.setText("");
            predkSamochodTXT.setText("");

            nazwaSkrzyniaTXT.setText("");
            wagaSkrzyniaTXT.setText("");
            cenaSkrzyniaTXT.setText("");
            biegSkrzyniaTXT.setText("");

            nazwaSilnikTXT.setText("");
            wagaSilnikTXT.setText("");
            cenaSilnikTXT.setText("");
            obrotySilnikTXT.setText("");

            nazwaSprzegloTXT.setText("");
            cenaSprzegloTXT.setText("");
            wagaSprzegloTXT.setText("");
            stanSprzegloTXT.setText("");

            auto.setVisible(false);
        }
    }

    public void run(){
        super.run();
        while (true){
            if (samochod != null) {
                refresh(samochod);
            }
            try{
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SamochodGUI");
        frame.setContentPane(new SamochodGUI().GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
