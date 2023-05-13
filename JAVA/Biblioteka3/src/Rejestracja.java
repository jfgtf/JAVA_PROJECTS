import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Rejestracja extends JFrame {
    private JPanel rejestracja;
    private JTextField wpiszEmailTXT;
    private JTextField wpiszImieTXT;
    private JTextField wpiszKodTXT;
    private JTextField wpiszNazwiskoTXT;
    private JTextField wpiszAdresTXT;
    private JTextField wpiszTelefonTXT;
    private JPasswordField wpiszHasloTXT;
    private JPasswordField wpiszPowtorzHasloTXT;
    private JButton dolaczButton;
    private JLabel blad;
    private JButton powrotButton;

    private HashMap<String, DaneUzytkownika> hashmapa_uzytkownicy = new HashMap<String, DaneUzytkownika>();
    private DataBaseUzytkownicy dataBaseUzytkownicy = new DataBaseUzytkownicy();
    private DaneUzytkownika uzytkownik;

    public JPanel getRejestracja() {
        return rejestracja;
    }

    public Rejestracja() {
        setContentPane(rejestracja);
        refresh();

        dolaczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String haslo1 = new String(wpiszHasloTXT.getPassword());
                String haslo2 = new String(wpiszPowtorzHasloTXT.getPassword());

                if (!wpiszEmailTXT.getText().contains("@") || wpiszEmailTXT.getText().contains("@biblioteka")) {
                    blad.setText("Wpisz poprawny email!");
                    blad.setVisible(true);
                }
                //else if sprawdzenie z Database uzytkownikow czy juz zostal uzyty
                //blad.setText("Podany email jest już w użyciu!");
                //blad.setVisible(true);

                else if(wpiszTelefonTXT.getText().isEmpty() || wpiszAdresTXT.getText().isEmpty() || wpiszNazwiskoTXT.getText().isEmpty() ||
                        wpiszImieTXT.getText().isEmpty() || wpiszKodTXT.getText().isEmpty()){
                    blad.setText("Wpisz brakujące dane!");
                    blad.setVisible(true);
                }

                else if (haslo1.isEmpty()){
                    blad.setText("Wpisz haslo!");
                    blad.setVisible(true);
                }

                else if(haslo2.isEmpty()){
                    blad.setText("Wpisz ponownie haslo!");
                    blad.setVisible(true);
                }

                else if (!haslo1.equals(haslo2)) {
                    blad.setText("Hasła muszą być takie same!");
                    blad.setVisible(true);
                }

                else{
                    hashmapa_uzytkownicy = dataBaseUzytkownicy.wczytajUzytkownikow();
                    uzytkownik = dataBaseUzytkownicy.szukajPoEmailu(hashmapa_uzytkownicy, wpiszEmailTXT.getText());
                    if(uzytkownik.getIdUzytkownika().equals("")){
                        uzytkownik = new DaneUzytkownika(uzytkownik.generujIDUzytkownika(hashmapa_uzytkownicy, dataBaseUzytkownicy), wpiszEmailTXT.getText(),
                                wpiszImieTXT.getText(), wpiszNazwiskoTXT.getText(), wpiszAdresTXT.getText(), wpiszKodTXT.getText(), haslo1,
                                wpiszTelefonTXT.getText());
                        try {
                            dataBaseUzytkownicy.dodajDoBazyUzytkownikowtxt(uzytkownik.wszystkoDoStringa());
                        } catch (Exception blad) {
                            System.out.println(blad);
                        }
                        dispose();
                        Menu frame = new Menu();
                        frame.setContentPane(frame.getMenu());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setResizable(false);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                        frame.setSize(800,600);
                        frame.setVisible(true);
                    }
                    else {
                        blad.setText("Istnieje juz taki uzytkownik");
                        blad.setVisible(true);
                    }
                }

                //else
                //  refresh();
            }
        });

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu frame = new Menu();
                frame.setContentPane(frame.getMenu());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });
    }

    public void refresh(){
        blad.setVisible(false);
    }
}
