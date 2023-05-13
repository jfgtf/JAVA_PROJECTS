import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class NowySamochodGUI extends JFrame {
    private JTextField wpiszNrRejest;
    private JTextField wpiszModel;
    private JTextField wpiszMarka;
    private JCheckBox a5BiegowManualnaCheckBox;
    private JCheckBox a6BiegowManualnaCheckBox;
    private JCheckBox benzynaCheckBox;
    private JCheckBox dieselCheckBox;
    private JTextField wpiszPredMax;
    private JButton dodajButton;
    private JButton anulujButton;
    private JPanel Tworzenie;
    private JTextField wpiszMaxObrotyTXT;

    private Samochod nowySamochod;
    private SkrzyniaBiegow nowaSkrzynia;
    private Silnik nowySilnik;
    private Pozycja nowaPozycja;
    private Sprzeglo noweSprzeglo;

    public NowySamochodGUI(JComboBox comboBoxSamochody) {
        setContentPane(Tworzenie);
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxObroty = 0;
                int iloscBiegow = 0;
                String nazwa_silnik = "xd";

                if(benzynaCheckBox.isSelected()){
                    nazwa_silnik = "benzyna";
                }
                else if (dieselCheckBox.isSelected()){
                    nazwa_silnik = "diesel";
                }

                if (a6BiegowManualnaCheckBox.isSelected()){
                    iloscBiegow = 6;
                }
                else if (a5BiegowManualnaCheckBox.isSelected()) {
                    iloscBiegow = 5;
                }

                Silnik nowySilnik = new Silnik(nazwa_silnik , 200 , 9000 , Integer.parseInt(wpiszMaxObrotyTXT.getText()));
                Sprzeglo noweSprzeglo = new Sprzeglo("sprzeglo_zwyczajny" , 100 , 1000);
                SkrzyniaBiegow nowaSkrzynia = new SkrzyniaBiegow("skrzynia_zwyczajny" , 1000 , 5000 , iloscBiegow , noweSprzeglo);
                nowaPozycja = new Pozycja(0,0);
                nowySamochod = new Samochod(wpiszMarka.getText(), wpiszNrRejest.getText(), wpiszModel.getText(), Integer.parseInt(wpiszPredMax.getText()), 100, 0.8, nowaPozycja, nowySilnik, nowaSkrzynia);
                comboBoxSamochody.addItem(nowySamochod);
            }
        });


        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
