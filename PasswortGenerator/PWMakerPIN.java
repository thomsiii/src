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

    JFrame frame;
    JPanel panel;
    JLabel titel;
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

    public PWMakerPIN() {
        //Creating the Frame
        frame = new JFrame("PIN Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        panel.setLayout(null);
        panel.setBackground(Color.black);

        titel = new JLabel("PIN Generator");
        titel.setForeground(Color.white);
        titel.setBounds(5, 5, 400, 60);
        Font fontTitel = new Font("Verdana", Font.ITALIC, 32);
        titel.setFont(fontTitel);
        panel.add(titel);

        //Generate Button
        generate = new JButton("PIN erstellen");
        panel.add(generate);
        //Pos
        generate.setBounds(10, 285, 150, 30);
        //Color
        generate.setBackground(Color.lightGray);
        //Funktion
        generate.addActionListener(this);

        pin = new JTextField();
        pin.setBounds(10, 250, 465, 30);
        panel.add(pin);

        lengthText = new JLabel("Wie viele Zahlen soll die PIN haben? ");
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

        websiteInfo = new JLabel("Wo wird die PIN benutzt? ");
        websiteInfo.setBounds(10, 155, 300, 30);
        websiteInfo.setForeground(Color.white);
        websiteField = new JTextField();
        websiteField.setBounds(10, 185, 300, 30);
        websiteField.setBackground(Color.white);
        panel.add(websiteField);
        panel.add(websiteInfo);

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
/* 
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    while((br.readLine()!= null)){

                    }
*/
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
