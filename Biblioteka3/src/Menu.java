import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel menu;
    private JLabel Biblioteka;
    private JButton zalogujButton;
    private JButton rejestracjaButton;
    private JButton kontaktButton;

    public JPanel getMenu() {
        return menu;
    }

    public Menu() {
        setContentPane(menu);
        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Logowanie frame = new Logowanie();
                frame.setContentPane(frame.getLogowanie());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        rejestracjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Rejestracja frame = new Rejestracja();
                frame.setContentPane(frame.getRejestracja());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((dim.width/3-frame.getSize().width)-40, (dim.height/5-frame.getSize().height)-25);
                frame.setSize(800,600);
                frame.setVisible(true);
            }
        });

        kontaktButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Kontakt frame = new Kontakt();
                frame.setContentPane(frame.getKontakt());
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
