import javax.swing.*;
import java.awt.*;

public class TestFromOpen extends JDialog {
    private JLabel label1 = new JLabel("It works !");

    public TestFromOpen(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Main");
        setSize(300,200);

        add(label1, BorderLayout.CENTER);
    }

    public void showModal(){
        setModal(true);
        setVisible(true);
    }
}
