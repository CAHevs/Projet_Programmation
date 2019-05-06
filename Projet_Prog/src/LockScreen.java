import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.io.IOException;

public class LockScreen extends JFrame {

    private Background myBG = new Background();
    private JLabel background = new JLabel();
    private JSlider unlock = new JSlider();
    private JPanel panelUnlock = new JPanel();
    private PanelTop panelTop = new PanelTop();
    Icon icon = new ImageIcon("slider_thumb.png");
    UIDefaults defaults = UIManager.getDefaults();

    // 16.04.2019 Création d'un écran de vérouillage avec un Slider
    // pour le dévérouiller
    public LockScreen() throws IOException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,500);

        defaults.put("Slider.horizontalThumbIcon", icon);

        background.setIcon(myBG.getBackground());
        add(background);

        unlock.setValue(0);
        unlock.addChangeListener(new Unlock());
        unlock.setOpaque(false);
        unlock.setUI(new MetalSliderUI());

        unlock.setBackground(Color.darkGray);
        unlock.setForeground(Color.lightGray);

        panelUnlock.add(unlock);
        panelUnlock.setBackground(Color.darkGray);

        add(panelTop, BorderLayout.NORTH);
        add(panelUnlock, BorderLayout.SOUTH);

    }

    public class Unlock implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == unlock){
                if (!unlock.getValueIsAdjusting()){
                    unlock.setValue(0);
                }
                if (unlock.getValue()== 100){
                    MainFrame test = null;
                    try {
                        test = new MainFrame();
                        test.setVisible(true);
                        disposeWindow();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    private void disposeWindow(){
        this.setVisible(false);
    }
    private void showWindow(){this.setVisible(true);}
}
