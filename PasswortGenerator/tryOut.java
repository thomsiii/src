import java.io.*;
import java.sql.Time;
import java.util.Random;
import java.util.*;
import java.time.*;


public class tryOut {

    public static void main(String[] args){

        File file = new File("C:\\Users\\thoma\\Documents\\test.txt");

        //      "C:\Users\thoma\Documents\test.txt"
        //      "C:\\Users\\thoma\\Documents\\PW Manager.txt"
        String passwortOne = "Name:     Google      "+
                "password:     oefgubvüouwberüogbu      "+
                "erstellt am:       "+Time.from(Instant.now());
            
        String passwortTwo = "Name:     Facebook      "+
                "password:     oefgubvüouwberüogbu      "+
                "erstellt am:       "+Time.from(Instant.now());


                int lastRow  = 1;
                String inhaltZeile ="";
                boolean append= true;
          try {
            /*
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for(int i =1; i<= 10;i++) {
                bw.write(passwortOne+ " Zeile: " + i + "\r\n");
            }
            bw.close();*/

            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((inhaltZeile = br.readLine())!=null){
                    System.out.println(inhaltZeile);
                    ++lastRow;
            }
            System.out.println("Letzte Reihe: "+lastRow);
            br.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((inhaltZeile = br.readLine())!=null){
                inhaltZeile = br.readLine();
            }
            System.out.println("Letzte Reihe: "+lastRow);
            br.close();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
            for(int i =0; i<=lastRow;i++) {
                
                if(i == lastRow){
                bw.write(passwortTwo+"\r\n");
                }else{
                }
            }
            bw.close();

            br = new BufferedReader(new FileReader(file));
            lastRow=1;
            while((inhaltZeile = br.readLine())!=null){
                    System.out.println(inhaltZeile);
                    ++lastRow;
            }
            System.out.println("Letzte Reihe: "+lastRow);
            br.close();
            

        }catch (IOException e) {
            e.printStackTrace();
        }


}
}
