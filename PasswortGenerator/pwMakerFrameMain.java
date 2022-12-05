package PasswortGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.SecureRandom;
import java.io.IOException;
import java.sql.Time;
import java.time.Instant;

public class pwMakerFrameMain extends JFrame implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel titel;
    JLabel lengthText;
    JTextField password;
    JButton generate;
    JCheckBox specialChar;
    JTextField lengthWindow;
    JButton copyButton;
    JLabel textIsCopied;
    JLabel zeroLength;
    JLabel websiteInfo;
    JTextField websiteField;
    JLabel emailInfo;
    JTextField emailField;
    JLabel emailDropDown;
    JButton saveButton;
    JButton reset;
    JButton addEmail;
    JButton removeEmail;

    String[] choices =
            { " - ",
                    "thomas.schmidt@htwg-konstanz.de",
                    "thomas_schmidt76@yahoo.de",
                    "schmidtt249@gmail.com"};

    final JComboBox<String> cb = new JComboBox<String>(choices);

    int buttonsPositionY = 450;
    int passwordFieldPositionY = 400;
    int textIsCopiedPositionY = 375;

    public pwMakerFrameMain() {
        //Creating the Frame
        frame = new JFrame("Passwort Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 530);
        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        panel.setLayout(null);
        panel.setBackground(Color.black);

        titel = new JLabel("Passwort Generator");
        titel.setForeground(Color.red);
        titel.setBounds(5, 5, 400, 60);
        Font fontTitel = new Font("Verdana", Font.ITALIC, 32);
        titel.setFont(fontTitel);
        panel.add(titel);

        password = new JTextField();
        password.setBounds(10, passwordFieldPositionY, 465, 30);
        panel.add(password);

        lengthText = new JLabel("Wie viele Zeichen soll das Passwort haben? ");
        lengthText.setBounds(10, 75, 300, 30);
        lengthText.setForeground(Color.white);
        zeroLength = new JLabel();
        zeroLength.setForeground(Color.red);
        zeroLength.setBounds(10, 140, 500, 20);
        panel.add(lengthText);
        panel.add(zeroLength);

        lengthWindow = new JTextField();
        lengthWindow.setBounds(10, 110, 50, 30);
        lengthWindow.setBackground(Color.white);
        panel.add(lengthWindow);

        websiteInfo = new JLabel("Wo wird das Passwort benutzt? ");
        websiteInfo.setBounds(10, 155, 300, 30);
        websiteInfo.setForeground(Color.white);
        websiteField = new JTextField();
        websiteField.setBounds(10, 185, 300, 30);
        websiteField.setBackground(Color.white);
        panel.add(websiteField);
        panel.add(websiteInfo);

        emailInfo = new JLabel("Welche Email-Adresse oder Name wird benutzt?");
        emailInfo.setBounds(10, 220, 300, 30);
        emailInfo.setForeground(Color.white);
        emailField = new JTextField();
        emailField.setBounds(10, 245, 300, 30);
        emailField.setBackground(Color.white);
        panel.add(emailField);
        panel.add(emailInfo);

        addEmail = new JButton("Add Email");
        addEmail.setBounds(325, 245, 100, 30);
        addEmail.setBackground(Color.lightGray);
        addEmail.addActionListener(this);
        panel.add(addEmail);

        emailDropDown = new JLabel("oder wähle hier aus");
        emailDropDown.setBounds(10, 290, 300, 30);
        emailDropDown.setForeground(Color.white);
        panel.add(emailDropDown);

        cb.setVisible(true);
        cb.setBounds(10,320,300,30);
        panel.add(cb);

        removeEmail = new JButton("remove");
        removeEmail.setBounds(325, 320, 100, 30);
        removeEmail.setBackground(Color.lightGray);
        removeEmail.addActionListener(this);
        panel.add(removeEmail);

        specialChar = new JCheckBox("Special Character allowed?");
        specialChar.setBounds(65, 110, 200, 30);
        specialChar.setBackground(Color.lightGray);
        specialChar.addActionListener(this);
        panel.add(specialChar);

        //Generate Button
        generate = new JButton("Passwort erstellen");
        panel.add(generate);
        //Pos
        generate.setBounds(10, buttonsPositionY, 150, 30);
        //Color
        generate.setBackground(Color.lightGray);
        //Funktion
        generate.addActionListener(this);

        copyButton = new JButton("Copy");
        copyButton.setBackground(Color.lightGray);
        copyButton.setBounds(165, buttonsPositionY, 100, 30);
        copyButton.addActionListener(this);
        panel.add(copyButton);

        textIsCopied = new JLabel();
        textIsCopied.setBounds(20,textIsCopiedPositionY , 200, 20);
        panel.add(textIsCopied);

        saveButton = new JButton("Speichern");
        saveButton.setBackground(Color.lightGray);
        saveButton.setBounds(270, buttonsPositionY, 100, 30);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        reset = new JButton("Reset");
        reset.setBackground(Color.lightGray);
        reset.setBounds(375, buttonsPositionY, 100, 30);
        reset.addActionListener(this);
        panel.add(reset);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param ae the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        int inputLength = Integer.parseInt(lengthWindow.getText());

        if (ae.getSource() == this.generate) {
            if (inputLength < 1) {
                zeroLength.setText("Das Passwort muss mindestens 1 und max. 30 Zeichen haben!");
            } else if (inputLength > 30) {
                zeroLength.setText("Wirklich niemand braucht so ein langes Passwort!! aber 30 Zeichen sind OK");
                inputLength = 30;
            } else{
                zeroLength.setText("");
            }
        }

        if(ae.getSource() == this.addEmail) {
            cb.addItem(emailField.getText());
        }

        if(ae.getSource() == this.removeEmail) {
            cb.removeItemAt(cb.getSelectedIndex());
        }

        if(ae.getSource() == this.generate && specialChar.isSelected()){
            password.setText(pwmaker.generateRandomPasswordspecialCharacter(inputLength));
            textIsCopied.setForeground(Color.green);
            textIsCopied.setText("*** Passwort erstellt ***");

        }else if (ae.getSource() == this.generate) {
            password.setText(pwmaker.generateRandomPassword(inputLength));
            textIsCopied.setForeground(Color.green);
            textIsCopied.setText("*** Passwort erstellt ***");

        }else if (ae.getSource() == this.copyButton) {
            textIsCopied.setForeground(Color.green);
            textIsCopied.setText("*** Passwort wurde kopiert ***");

        } else if (specialChar.isSelected() || !specialChar.isSelected()) {
            textIsCopied.setText("");
        }

        if (ae.getSource() == this.saveButton) {

            File file = new File("C:\\Users\\thoma\\Documents\\PW Manager.txt");

            try {
                if (!Desktop.isDesktopSupported()) {
                    System.out.println("not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()){
                    desktop.open(file);
                }

                if (websiteField.getText().isEmpty()) {
                        websiteField.setText("N/A");
                }

                if (emailField.getText().isEmpty()) {
                    String chosenEmail = (String) cb.getSelectedItem();
                    emailField.setText((chosenEmail));
                    emailField.setForeground(Color.white);
                }else{
                    emailField.setText("N/A");
                }

                int lastRow  = 1;
                String inhaltZeile ="";
                boolean append= true;
                try {
                    
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    while((inhaltZeile = br.readLine())!=null){
                        inhaltZeile = br.readLine();
                    }br.close();

                    String passwordFormatted = String.format("Name: %-20s  E-Mail: %-35s  password: %-30s  created:  %-20s",
                            websiteField.getText(),
                            emailField.getText(),
                            password.getText(),
                            Time.from(Instant.now()));
        
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
                    for(int i =0; i<=lastRow;i++) {
                        
                        if(i == lastRow){
                        bw.write(passwordFormatted +"\r\n");
                        }
                    }
                    bw.close();                   
        
                }catch (IOException e) {
                    e.printStackTrace();
                }

        }catch (IOException e) {
            e.printStackTrace();
        }
                
        }

        if (ae.getSource() == this.reset) {
            lengthWindow.setText("");
            websiteField.setText("");
            emailField.setText("");
            textIsCopied.setText("");
            password.setText("");
            specialChar.setSelected(false);
            cb.setSelectedIndex(0);
        }
    }



    public static class pwmaker {
        // Method to generate a random alphanumeric password of a specific length
        public static String generateRandomPassword(int len) {
            // ASCII range – alphanumeric (0-9, a-z, A-Z)
            final String onlyCharsandNumbers =
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                            "abcdefghijklmnopqrstuvwxyz" +
                            "0123456789";

            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder();

            // each iteration of the loop randomly chooses a character from the given
            // ASCII range and appends it to the `StringBuilder` instance

            for (int i = 0; i < len; i++) {
                int randomIndex = random.nextInt(onlyCharsandNumbers.length());
                sb.append(onlyCharsandNumbers.charAt(randomIndex));
            }

            return sb.toString();
        }

        public static String generateRandomPasswordspecialCharacter(int len) {
            // ASCII range – alphanumeric (0-9, a-z, A-Z) and special Signs
            final String specialCharsAllowed =
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                            "abcdefghijklmnopqrstuvwxyz" +
                            "0123456789" +
                            "!~,@%#$^*()-_=+[]{,}|;:.?";

            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder();

            // each iteration of the loop randomly chooses a character from the given
            // ASCII range and appends it to the `StringBuilder` instance

            for (int i = 0; i < len; i++) {
                int randomIndex = random.nextInt(specialCharsAllowed.length());
                sb.append(specialCharsAllowed.charAt(randomIndex));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        pwMakerFrameMain testPWMaker = new pwMakerFrameMain();
    }
}