import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ContactInfo extends JDialog {

    private boolean newUser = false;

    private PanelTop panelTop = new PanelTop();

    private File contact;

    private JPanel panelInfo = new JPanel(new GridLayout(0,1));
    private JPanel panelName = new JPanel();
    private JPanel panelFirstName = new JPanel();
    private JPanel panelTel = new JPanel();

    private JLabel lblname = new JLabel("Nom : ", SwingConstants.LEFT);
    private JTextField txtName;

    private JLabel lblfirstName = new JLabel("Prénom : ", SwingConstants.LEFT);
    private JTextField txtFirstName;

    private JLabel lblTel = new JLabel("Téléphone : ", SwingConstants.LEFT);
    private JTextField txtTel;

    private JButton modify = new JButton("Edit");
    private JButton save = new JButton("Save");

    private JLabel imgContact = new JLabel();



    public ContactInfo(File contact) throws IOException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,500);

        modifiyContact action = new modifiyContact();

        modify.addActionListener(action);
        save.addActionListener(action);
        save.setVisible(false);

        panelInfo.setLayout(new FlowLayout());

        this.contact = contact;

        txtName = new JTextField(getCName(contact), 20);
        txtName.setEditable(false);
        txtName.setBorder(null);

        txtFirstName = new JTextField(getCFirstName(contact), 20);
        txtFirstName.setEditable(false);
        txtFirstName.setBorder(null);

        txtTel = new JTextField(getCTel(contact), 10);
        txtTel.setEditable(false);
        txtTel.setBorder(null);

        if (getImageContact(contact).equals("")){
            addDefaultIcon();
        }
        imgContact.setIcon(new ImageIcon(getImageContact(contact)));

        panelName.add(lblname);
        panelName.add(txtName);
        panelFirstName.add(lblfirstName);
        panelFirstName.add(txtFirstName);
        panelTel.add(lblTel);
        panelTel.add(txtTel);


        panelInfo.add(imgContact, BorderLayout.WEST);
        panelInfo.add(panelName, BorderLayout.WEST);
        panelInfo.add(panelFirstName, BorderLayout.WEST);
        panelInfo.add(panelTel, BorderLayout.WEST);
        panelInfo.add(modify, BorderLayout.SOUTH);
        panelInfo.add(save, BorderLayout.SOUTH);

        add(panelInfo, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
    }

    public ContactInfo() throws IOException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,500);

        newUser = true;

        modifiyContact action = new modifiyContact();

        save.addActionListener(action);
        save.setVisible(true);
        modify.setVisible(false);

        panelInfo.setLayout(new FlowLayout());

        txtName = new JTextField(20);
        txtName.setBorder(null);

        txtFirstName = new JTextField(20);
        txtFirstName.setBorder(null);

        txtTel = new JTextField(10);
        txtTel.setBorder(null);

        panelName.add(lblname);
        panelName.add(txtName);
        panelFirstName.add(lblfirstName);
        panelFirstName.add(txtFirstName);
        panelTel.add(lblTel);
        panelTel.add(txtTel);


        panelInfo.add(imgContact, BorderLayout.WEST);
        panelInfo.add(panelName, BorderLayout.WEST);
        panelInfo.add(panelFirstName, BorderLayout.WEST);
        panelInfo.add(panelTel, BorderLayout.WEST);
        panelInfo.add(modify, BorderLayout.SOUTH);
        panelInfo.add(save, BorderLayout.SOUTH);

        add(panelInfo, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
    }

    private String getCName(File contact) throws FileNotFoundException {
        String contactName = "";

        contactName = getPartContact(contact, 2);

        return contactName;
    }

    private String getCFirstName(File contact) throws FileNotFoundException {

        String contactFirstName = "";

        contactFirstName = getPartContact(contact, 5);

        return contactFirstName;

    }

    private String getCTel(File contact) throws FileNotFoundException {
        String numTel = "";

        numTel = getPartContact(contact, 8);

        return numTel;
    }

    private String getImageContact(File contact) throws FileNotFoundException {
        String path = "";

        path = getPartContact(contact, 11);

        return path;
    }

    private String getPartContact(File contact, int idx) throws FileNotFoundException {
        String part = "";
        String next;
        int i = 0;

        Scanner s = new Scanner(contact);

        while(s.hasNext()){
            next = s.next();
            if (i == idx){
                part = next;
            }
            i++;
        }

        s.close();

        return part;
    }

    private class modifiyContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == modify){
                txtFirstName.setEditable(true);
                txtName.setEditable(true);
                txtTel.setEditable(true);
                modify.setVisible(false);
                save.setVisible(true);
            }
            if(e.getSource() == save){
                txtName.setEditable(false);
                txtFirstName.setEditable(false);
                txtTel.setEditable(false);
                modify.setVisible(true);
                save.setVisible(false);

                if(newUser == true){
                    String pathname = "contact/"+txtFirstName.getText().toLowerCase()+"_"+txtName.getText().toLowerCase()+".txt";

                    File file = new File(pathname);
                    try {
                        file.createNewFile();
                        contact = file;
                        addDefaultIcon();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        }
                }
                try {
                    FileWriter fw = new FileWriter(contact);
                    fw.write(getInfoContactModified());
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void addDefaultIcon() throws IOException {
        FileWriter fw = new FileWriter(contact);
        String info ="";
        info += "Nom : " + txtName.getText() + "\n";
        info += "Prenom : " + txtFirstName.getText() + "\n";
        info += "Téléphone : " + txtTel.getText() + "\n";
        info += "Image : " + "contact/default_icon.png";

        fw.write(info);
    }

    private String getInfoContactModified(){
        String info ="";
        info += "Nom : " + txtName.getText() + "\n";
        info += "Prenom : " + txtFirstName.getText() + "\n";
        info += "Téléphone : " + txtTel.getText() + "\n";
        info += "Image : " + imgContact.getIcon();

        return info;
    }
}
