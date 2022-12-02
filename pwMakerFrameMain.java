import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
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
    JButton saveButton;
    JButton reset;

    public pwMakerFrameMain() {
        //Creating the Frame
        frame = new JFrame("Passwort Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        titel = new JLabel("Passwort Generator");
        titel.setForeground(Color.black);
        titel.setBounds(5, 5, 400, 60);
        Font fontTitel = new Font("Verdana", Font.ITALIC, 32);
        titel.setFont(fontTitel);
        panel.add(titel);

        //Generate Button
        generate = new JButton("Passwort erstellen");
        panel.add(generate);
        //Pos
        generate.setBounds(10, 285, 150, 30);
        //Color
        generate.setBackground(Color.lightGray);
        //Funktion
        generate.addActionListener(this);

        password = new JTextField();
        password.setBounds(10, 250, 465, 30);
        panel.add(password);

        lengthText = new JLabel("Wie viele Zeichen soll das Passwort haben? ");
        lengthText.setBounds(10, 75, 300, 30);
        lengthText.setForeground(Color.black);
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
        websiteInfo.setForeground(Color.black);
        websiteField = new JTextField();
        websiteField.setBounds(10, 185, 300, 30);
        websiteField.setBackground(Color.white);
        panel.add(websiteField);
        panel.add(websiteInfo);

        specialChar = new JCheckBox("Special Character allowed?");
        specialChar.setBounds(65, 110, 200, 30);
        specialChar.setBackground(Color.lightGray);
        specialChar.addActionListener(this);
        panel.add(specialChar);

        copyButton = new JButton("Copy");
        copyButton.setBackground(Color.lightGray);
        copyButton.setBounds(165, 285, 100, 30);
        copyButton.addActionListener(this);
        panel.add(copyButton);
        textIsCopied = new JLabel();
        textIsCopied.setForeground(Color.red);
        textIsCopied.setBounds(10, 230, 200, 20);
        panel.add(textIsCopied);

        saveButton = new JButton("Speichern");
        saveButton.setBackground(Color.lightGray);
        saveButton.setBounds(270, 285, 100, 30);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        reset = new JButton("Reset");
        reset.setBackground(Color.lightGray);
        reset.setBounds(375, 285, 100, 30);
        reset.addActionListener(this);
        panel.add(reset);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);

        Dialog meinJDialog = new JDialog();
        meinJDialog.setTitle("JPanel Beispiel");
        meinJDialog.setSize(450,300);

        JPanel panelRot = new JPanel();
        panelRot.setBackground(Color.RED);

        JTabbedPane tabpane = new JTabbedPane
                (JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
        tabpane.addTab("Ich bin rot", panelRot);
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
                zeroLength.setText("Das Passwort muss mindestens die Länge 1 haben!");
            } else if (inputLength >= 50) {
                zeroLength.setText("Wirklich niemand braucht so ein langes Passwort!!" +
                        " aber 50 Zeichen bekommst du :) ");
                inputLength = 50;
            } else {
                zeroLength.setText("");
                lengthWindow.setBackground(Color.white);
            }
        }

        if (ae.getSource() == this.generate && specialChar.isSelected()) {
            password.setText(pwmaker.generateRandomPasswordspecialCharacter(inputLength));
            textIsCopied.setText("");
        } else if (ae.getSource() == this.generate) {
            password.setText(pwmaker.generateRandomPassword(inputLength));
            textIsCopied.setText("");
        } else if (ae.getSource() == this.copyButton) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new StringSelection(password.getText()), null);
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
                if (file.exists())
                    desktop.open(file);
                try {


                    if (websiteField.getText().isEmpty()) {
                        websiteField.setText("N/A");
                    }
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    while((br.readLine()!= null)){

                    }
                        FileWriter fw = new FileWriter("C:\\Users\\thoma\\Documents\\PW Manager.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    String passwortAusgabe = "Name: " + websiteField.getText() +
                            "     Passwort:  " + password.getText() +
                            "     Erstellt am: " + Time.from(Instant.now());

                    bw.newLine();
                    bw.write(passwortAusgabe);
                    bw.close();
                } catch (IOException ioe) {
                    System.err.println(ioe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == this.reset) {
            lengthWindow.setText("");
            websiteField.setText("");
            textIsCopied.setText("");
            password.setText("");
            specialChar.setSelected(false);
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
        pwMakerFrameMain test = new pwMakerFrameMain();
    }
}