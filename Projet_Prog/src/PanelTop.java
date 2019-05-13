/* *************************************************** */
// Réalisée par : Christopher Artero
// Le : 17.04.2019
// Cette classe permet de créer un modèl pour le JPanel
// du sommet qui sera toujours le même dans tout le projet.
// Ce dernier contient l'heure actuel de la machine ainsi
// que le niveau de batterie de cette dernière.
/* *************************************************** */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class PanelTop extends JPanel {

    private JLabel batteryDisplay = new JLabel();
    private JLabel percentDisplay = new JLabel();
    private JLabel battery = new JLabel("",SwingConstants.RIGHT);
    private JLabel time = new JLabel("", SwingConstants.CENTER);
    private JLabel fiveG = new JLabel("5G", SwingConstants.LEFT);
    private Timer timer_time;
    //private JPanel panelBattery = new JPanel();

    private String info_battery, percentBatteryText, statusBattery, percentBatteryWithoutSymbol;
    private int valuePercent;
    private BufferedImage img;

    public PanelTop() throws IOException {

        this.setLayout(new GridLayout(1,3));

        Task_timer task_timer = new Task_timer();

        timer_time = new Timer(100, task_timer);
        timer_time.setRepeats(true);
        timer_time.start();

        DisplayBatteryLevel();

        this.setBackground(Color.BLACK);
        percentDisplay.setForeground(Color.white);
        fiveG.setForeground(Color.white);
        time.setForeground(Color.white);
        this.add(fiveG);
        this.add(time);
        battery.setForeground(Color.white);
        battery.setIcon(batteryDisplay.getIcon());
        battery.setText(percentDisplay.getText());

        this.add(battery);
    }

    public class Task_timer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DisplayBatteryLevel();
                battery.setIcon(batteryDisplay.getIcon());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            time.setText(getCurrentTime());
        }
    }

    private String getCurrentTime(){
        String currentTime = "";
        Calendar time = Calendar.getInstance();
        if (time.get(Calendar.MINUTE) < 10){
            currentTime = time.get(Calendar.HOUR_OF_DAY) + "h0" + time.get(Calendar.MINUTE);
        }
        else{
            currentTime = time.get(Calendar.HOUR_OF_DAY) + "h" + time.get(Calendar.MINUTE);
        }
        return currentTime;
    }

    public void DisplayBatteryLevel() throws IOException {
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
        info_battery = batteryStatus.toString();
        String[] arrOfStr = info_battery.split("\n");
        percentBatteryText = arrOfStr[0];
        statusBattery = arrOfStr[1];
        percentBatteryWithoutSymbol = "";


        for (int i = 0; i<percentBatteryText.length(); i++){
            if (percentBatteryText.charAt(i)!= '%') {
                percentBatteryWithoutSymbol += percentBatteryText.charAt(i);
            }
        }

        valuePercent = Integer.parseInt(percentBatteryWithoutSymbol);

        if (statusBattery.equals("Online")){
            img = ImageIO.read(new File("battery_pics/battery_load.png"));
        }
        else{
            if (valuePercent <= 100 && valuePercent > 75){
                img = ImageIO.read(new File("battery_pics/battery_full.png"));
            }
            else if (valuePercent <= 75 && valuePercent > 50){
                img = ImageIO.read(new File("battery_pics/battery_3_4.png"));
            }
            else if (valuePercent <= 50 && valuePercent > 25){
                img = ImageIO.read(new File("battery_pics/battery_low.png"));
            }
            else if (valuePercent <= 25 && valuePercent > 0){
                img = ImageIO.read(new File("battery_pics/battery_alert.png"));
            }
        }

        ImageIcon icon = new ImageIcon(img);

        percentDisplay.setText(percentBatteryText);
        batteryDisplay.setIcon(icon);
    }
}
