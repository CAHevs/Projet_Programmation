import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ContactFrame extends JFrame {

    private PanelTop panelTop = new PanelTop();
    private JPanel contacts;
    private File folder = new File("contact");

    public ContactFrame() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,500);

        contacts = contactList(folder);

        add(contacts, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
    }

    private JPanel contactList(File folder){

        JPanel contacts = new JPanel();
        File[] listofContactsFile;
        JButton[] listContacts;
        JPanel[] contactWithName;

        listofContactsFile = getAllFileText(folder);

        listContacts = new JButton[listofContactsFile.length];
        contactWithName = new JPanel[listofContactsFile.length];

        for(int i = 0 ; i< listofContactsFile.length; i++){

            contactWithName[i] = new JPanel();

            listContacts[i] = new JButton();
            listContacts[i].setFont(new Font(Font.DIALOG ,Font.BOLD, 14));
            listContacts[i].setText(getNameFirstName(getContactName(listofContactsFile[i])));
            listContacts[i].setVerticalTextPosition(AbstractButton.CENTER);
            listContacts[i].setHorizontalTextPosition(AbstractButton.RIGHT);
            listContacts[i].addActionListener(new showContact(getContactName(listofContactsFile[i])));
            listContacts[i].setBorderPainted(false);
            listContacts[i].setContentAreaFilled(false);
            listContacts[i].setFocusPainted(false);
            listContacts[i].setOpaque(false);
            listContacts[i].setIcon(setIconContact(folder,listofContactsFile[i]));

            contactWithName[i].add(listContacts[i]);

            contacts.add(contactWithName[i]);
            contacts.add(new JLabel("----------------------------------------------------"));
        }
        return contacts;
    }

    private File[] getAllFileText(File folder){
        int nbTextFile = 0;
        String extension;
        int idx = 0;
        File[] allContacts = null;
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i<listOfFiles.length; i++){
            extension = getFileExtension(listOfFiles[i]);
            if(extension.equals("txt")){
                nbTextFile++;
            }
        }

        allContacts = new File[nbTextFile];

        for(int i = 0; i<listOfFiles.length; i++){
            if(getFileExtension(listOfFiles[i]).equals("txt")){
                allContacts[idx] = listOfFiles[i];
                idx++;
            }
        }

        return allContacts;
    }

    private String getFileExtension(File file){
        String name = file.getName();
        String extension = "";

        int lastIndexOf = name.lastIndexOf(".");
        if(lastIndexOf >= 0){
            extension = name.substring(lastIndexOf+1);
        }
        return extension;
    }

    private ImageIcon setIconContact(File folder, File file){
        ImageIcon img;
        String extension = "";

        String contactName = "";

        contactName = getContactName(file);

        File[] listPng = folder.listFiles();

        for (int i = 0; i<listPng.length; i++){
            extension = getFileExtension(listPng[i]);
            if(extension.equals("png")){
                if (getContactName(listPng[i]).equals(contactName)){
                    img = new ImageIcon("contact/"+listPng[i].getName());
                    return img;
                }
            }
        }

        return new ImageIcon("contact/default_icon.png");
    }

    private String getNameFirstName(String name){
        String nameFirstName;

        nameFirstName = name.substring(0, name.indexOf("_")) + " " + name.substring(name.indexOf("_") +1);

        return nameFirstName;
    }

    private String getContactName(File file){
        String contactName = "";

        contactName = file.getName().substring(file.getName().indexOf("/") + 1, file.getName().indexOf("."));

        return contactName;
    }

    public class showContact implements ActionListener{

        String name;

        public showContact(String contact){
            name = getNameFirstName(contact);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Vous avez cliquer sur le contact : " + name);
        }
    }
}
