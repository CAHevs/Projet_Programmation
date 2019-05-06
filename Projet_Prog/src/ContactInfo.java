import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ContactInfo extends JDialog {

    private PanelTop panelTop = new PanelTop();


    public ContactInfo() throws IOException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,500);

        add(panelTop, BorderLayout.NORTH);


    }
}
