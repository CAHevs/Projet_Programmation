import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.event.*;
import java.text.*;
import java.awt.image.*;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Timer;

public class MainFrame extends JFrame {

    private PanelTop panelTop = new PanelTop();
    private JPanel apps = new JPanel();
    private JPanel buttons = new JPanel();

    ImageIcon contactIcon = new ImageIcon("contactIcon.png");
    ImageIcon galleryIcon = new ImageIcon("galleryIcon.png");
    ImageIcon homeIcon = new ImageIcon("HomeIcon.png");
    ImageIcon backIcon = new ImageIcon("backIcon.png");
    ImageIcon gameIcon = new ImageIcon("gameIcon.png");

    private JButton home = new JButton(homeIcon);
    private JButton back = new JButton(backIcon);
    private JButton contacts = new JButton(contactIcon);
    private JButton gallery = new JButton(galleryIcon);
    private JButton game = new JButton(gameIcon);

    public MainFrame() throws IOException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(300, 500);


        panelTop.setLayout(new GridLayout(1, 3));

        apps.setLayout(new GridLayout(1, 3));

        contacts.setBorderPainted(false);
        contacts.setContentAreaFilled(false);
        contacts.setFocusPainted(false);
        contacts.setOpaque(false);

        gallery.setBorderPainted(false);
        gallery.setContentAreaFilled(false);
        gallery.setFocusPainted(false);
        gallery.setOpaque(false);

        game.setBorderPainted(false);
        game.setContentAreaFilled(false);
        game.setFocusPainted(false);
        game.setOpaque(false);

        apps.add(contacts);
        apps.add(gallery);
        apps.add(game);

        home.setBorderPainted(false);
        home.setContentAreaFilled(false);
        home.setFocusPainted(false);
        home.setOpaque(false);

        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setOpaque(false);

        buttons.add(home);
        buttons.setBackground(Color.GRAY);

        add(panelTop, BorderLayout.NORTH);
        add(apps);
        add(buttons, BorderLayout.SOUTH);

    }
}