package PasswortGenerator;

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
import java.math.*;

public class PWMakerPIN extends JFrame implements ActionListener {

    JFrame pinFrame;
    JPanel pinPanel;
    JLabel pinTitel;
    JLabel lengthText;
    JTextField pin;
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
    JMenuItem pinMenu;
    JMenuItem pwMaker;

    public PWMakerPIN() {
        //Creating the Frame
        pinFrame = new JFrame("PIN Generator");
        pinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pinFrame.setSize(550, 450);
        //Creating the panel at bottom and adding components
        pinPanel = new JPanel(); // the panel is not visible in output
        pinPanel.setLayout(null);
        pinPanel.setBackground(Color.black);

        pinTitel = new JLabel("PIN Generator");
        pinTitel.setForeground(Color.white);
        pinTitel.setBounds(5, 5, 400, 60);
        Font fontTitel = new Font("Verdana", Font.ITALIC, 32);
        pinTitel.setFont(fontTitel);
        pinPanel.add(pinTitel);

        pinMenu = new JMenuItem("PIN Generator");
        pinFrame.add(pinMenu);

        //Generate Button
        generate = new JButton("PIN erstellen");
        pinPanel.add(generate);
        //Pos
        generate.setBounds(10, 285, 150, 30);
        //Color
        generate.setBackground(Color.lightGray);
        //Funktion
        generate.addActionListener(this);

        pin = new JTextField();
        pin.setBounds(10, 250, 465, 30);
        pinPanel.add(pin);

        lengthText = new JLabel("Wie viele Zahlen soll die PIN haben? ");
        lengthText.setBounds(10, 75, 300, 30);
        lengthText.setForeground(Color.white);
        zeroLength = new JLabel();
        zeroLength.setForeground(Color.red);
        zeroLength.setBounds(10, 140, 500, 20);
        pinPanel.add(lengthText);
        pinPanel.add(zeroLength);

        lengthWindow = new JTextField();
        lengthWindow.setBounds(10, 110, 50, 30);
        lengthWindow.setBackground(Color.white);
        pinPanel.add(lengthWindow);

        websiteInfo = new JLabel("Wo wird die PIN benutzt? ");
        websiteInfo.setBounds(10, 155, 300, 30);
        websiteInfo.setForeground(Color.white);
        websiteField = new JTextField();
        websiteField.setBounds(10, 185, 300, 30);
        websiteField.setBackground(Color.white);
        pinPanel.add(websiteField);
        pinPanel.add(websiteInfo);

        copyButton = new JButton("Copy");
        copyButton.setBackground(Color.lightGray);
        copyButton.setBounds(165, 285, 100, 30);
        copyButton.addActionListener(this);
        pinPanel.add(copyButton);
        textIsCopied = new JLabel();
        textIsCopied.setForeground(Color.red);
        textIsCopied.setBounds(10, 230, 200, 20);
        pinPanel.add(textIsCopied);

        saveButton = new JButton("Speichern");
        saveButton.setBackground(Color.lightGray);
        saveButton.setBounds(270, 285, 100, 30);
        saveButton.addActionListener(this);
        pinPanel.add(saveButton);

        reset = new JButton("Reset");
        reset.setBackground(Color.lightGray);
        reset.setBounds(375, 285, 100, 30);
        reset.addActionListener(this);
        pinPanel.add(reset);

        //Adding Components to the frame.
        pinFrame.getContentPane().add(BorderLayout.CENTER, pinPanel);
        pinFrame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param ae the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        File file = new File("C:\\Users\\thoma\\Documents\\pinTestDoc.txt");

        int inputLength = Integer.parseInt(lengthWindow.getText());

        if (ae.getSource() == this.generate) {
            if (inputLength < 1) {
                zeroLength.setText("Die PIN muss mindestens die Länge 1 haben!");
            } else {
                zeroLength.setText("");
                lengthWindow.setBackground(Color.white);
            }
        }
        if (ae.getSource() == this.generate) {
            pin.setText(String.valueOf(PinGenerator(inputLength)));
            textIsCopied.setText("");
        }
        if (ae.getSource() == this.copyButton) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new StringSelection(pin.getText()), null);
            textIsCopied.setText("*** PIN wurde kopiert ***");
        } 
        if (ae.getSource() == this.saveButton) {

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

                        FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String passwortAusgabe = "Name: " + websiteField.getText() +
                            "     PIN:  " + pin.getText() +
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
            pin.setText("");
            specialChar.setSelected(false);
        }
    }

    public static BigInteger PinGenerator(int len) {
        final int[] numbersAllowed = {0,1,2,3,4,5,6,7,8,9};

        //Number from Array random Index
        SecureRandom random = new SecureRandom();
        int randomIndex = 0; 
        int temp =0;
        String tempString ="";
        String finalPin ="";
        int finalPinInt =0;

        for(int i =0; i<len;i++){
            randomIndex = random.nextInt(numbersAllowed.length);
            System.out.println("random Index: "+randomIndex+" i: "+i);
            temp = numbersAllowed[randomIndex];
            tempString = String.valueOf(temp);
            System.out.println("temp: "+temp);
            finalPin+= tempString;
            System.out.println("final Pin: "+finalPin);
        }
        BigInteger pin = new BigInteger(finalPin);
        return pin;
}

    public static void main(String[] args) {
        PWMakerPIN pinGenerator = new PWMakerPIN();
        }
    }
