import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Logowanie extends JFrame{
    private JPanel logowanie;
    private JTextField wpiszEmailTXT;
    private JLabel niepoprawneDaneLabel;
    private JButton zalogujButton;
    private JPasswordField wpiszHasloTXT;
    private JButton powrotButton;

    private HashMap<String, DaneUzytkownika> hashmapa_uzytkownicy = new HashMap<String, DaneUzytkownika>();
    private DataBaseUzytkownicy dataBaseUzytkownicy = new DataBaseUzytkownicy();
    private DaneUzytkownika uzytkownik;

    public JPanel getLogowanie() {
        return logowanie;
    }

    public Logowanie(){
        setContentPane(logowanie);
        refresh();
        DanePracownika danePracownika = new DanePracownika("abc@biblioteka.pl", "123");

        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String haslo = new String(wpiszHasloTXT.getPassword());

                if(wpiszEmailTXT.getText().equals(danePracownika.getEmail()) && haslo.equals(danePracownika.getHaslo())){
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

                else {
                    hashmapa_uzytkownicy = dataBaseUzytkownicy.wczytajUzytkownikow();
                    uzytkownik = dataBaseUzytkownicy.szukajPoEmailu(hashmapa_uzytkownicy, wpiszEmailTXT.getText());
                    if (haslo.equals(uzytkownik.getHaslo())) {
                        dispose();
                        PanelUzytkownika frame = new PanelUzytkownika(uzytkownik);
                        frame.setContentPane(frame.getPanelUzytkownika());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setResizable(true);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        frame.setLocation((dim.width/4-frame.getSize().width)-80, (dim.height/5-frame.getSize().height)-25);
                        frame.setSize(1200,600);
                        frame.setVisible(true);
                    }

                    else
                        niepoprawneDaneLabel.setVisible(true);
                }
            }
        });

        powrotButton.addActionListener(new ActionListener() {
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
        niepoprawneDaneLabel.setVisible(false);
    }

}
