import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KsiazkaPoNazwie extends JFrame {
    private JComboBox comboBoxKsiazki;
    private JPanel ksiazkaPoNazwie;
    private JButton wybierzButton;
    private JButton wrocButton;

    private Ksiazki ksiazka;

    public KsiazkaPoNazwie(ArrayList<Ksiazki> listaKsiazek) {

        for(Ksiazki ksiazki : listaKsiazek){
            comboBoxKsiazki.addItem(ksiazki);
        }

        wybierzButton.addActionListener(new ActionListener() {
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

    public JPanel getKsiazkaPoNazwie() {
        return ksiazkaPoNazwie;
    }


}
