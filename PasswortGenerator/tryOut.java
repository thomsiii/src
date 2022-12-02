//import java.io.*;
import java.util.Random;
import java.math.BigInteger;


public class tryOut {

    public static BigInteger testPinGenerator(int len) {
            final int[] numbersAllowed = {0,1,2,3,4,5,6,7,8,9};

            //Number from Array random Index
            Random random = new Random();
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


    public static void main(String[] args){

        System.out.println("PIN ist: "+testPinGenerator(10));

        //      "C:\Users\thoma\Documents\test.txt"
        //      "C:\\Users\\thoma\\Documents\\PW Manager.txt"
//        String passwortOne = "Name:     Google      "+
//                "password:     oefgubvüouwberüogbu      "+
//                "erstellt am:       "+Time.from(Instant.now());
//
//        String passwortTwo = "Name:     Facebook      "+
//                "password:     oefgubvüouwberüogbu      "+
//                "erstellt am:       "+Time.from(Instant.now());


/*          try {
            File file = new File("C:\\Users\\thoma\\Documents\\test.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for(int i =0; i< 10;i++) {
                bw.write("\nZeileninhalt "+i+"\n");
            }
            bw.close();

            BufferedReader br = new BufferedReader(new FileReader(file));
            while((br.readLine()!=null)){
                    System.out.println(br.readLine());
            }
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        } */
        
}
}
