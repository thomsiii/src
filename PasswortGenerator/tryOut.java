import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.time.Instant;

public class tryOut {

    public static void main(String[] args){

        //      "C:\Users\thoma\Documents\test.txt"
        //      "C:\\Users\\thoma\\Documents\\PW Manager.txt"
//        String passwortOne = "Name:     Google      "+
//                "password:     oefgubvüouwberüogbu      "+
//                "erstellt am:       "+Time.from(Instant.now());
//
//        String passwortTwo = "Name:     Facebook      "+
//                "password:     oefgubvüouwberüogbu      "+
//                "erstellt am:       "+Time.from(Instant.now());
        try {
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
        }
    }
}
