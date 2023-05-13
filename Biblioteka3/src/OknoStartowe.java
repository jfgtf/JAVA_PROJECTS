import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class OknoStartowe extends JFrame {
    private JPanel oknoStart;
    private JButton zapraszamyButton;

    public JPanel getOknoStart() {
        return oknoStart;
    }

    public static void start() {
        JFrame frame = new JFrame();
        frame.setContentPane(new OknoStartowe().oknoStart);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
        frame.setSize(800,600);
        frame.setVisible(true);

        Daty daty = new Daty();
        HashMap<String, WypozyczoneKsiazki> hashmapaWypoKsiazki = new HashMap<String, WypozyczoneKsiazki>();
        DataBaseWypozyczoneKsiazki dataBaseWypozyczoneKsiazki = new DataBaseWypozyczoneKsiazki();
        hashmapaWypoKsiazki = dataBaseWypozyczoneKsiazki.wczytajWypozyczoneKsiazki();
        daty.weryfikacjaCzasuRezerwacjiKsiazki(hashmapaWypoKsiazki);
    }

    public OknoStartowe() {
        zapraszamyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
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
}
