public class Ablage_Alt {

/*
    public static void main(String[] args) throws FileNotFoundException {

        Path p = Path.of("C:\\Users\\thoma\\Documents\\PWGenerator.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gebe die Länge des PW ein: ");
        int length = scanner.nextInt();

        String sonderZeichenAllowed = "";
        String allSigns = "";


        while ((!sonderZeichenAllowed.equals("y") && (!sonderZeichenAllowed.equals("n")))) {

            System.out.println("Darf das PW Sonderzeichen haben (y/n)?: ");
            sonderZeichenAllowed = scanner.next();

            if ((!sonderZeichenAllowed.equals("y") && (!sonderZeichenAllowed.equals("n")))) {
                System.out.println("Die Eingabe war falsch! Für Ja \"y\" und Nein \"n\" eingeben! ");
            } else {


                if (sonderZeichenAllowed.equals("n")) {
                    String passwordNoSpecialChar = generateRandomPassword(length);

                    System.out.println("Dein Passwort ohne Sonderzeichen mit Länge " + length +
                            " ist: \n --------------------\n" +
                            passwordNoSpecialChar +
                            "\n --------------------\n");
                    try {
                        Path filePath = Files.writeString(p, passwordNoSpecialChar);
                        String s = Files.readString(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (sonderZeichenAllowed.equals("y")) {


                    while ((!allSigns.equals("y") && (!allSigns.equals("n")))) {

                        System.out.println("dürfen alle Sonderzeichen enthalten sein (y/n)?");
                        allSigns = scanner.next();

                        if ((!allSigns.equals("y") && (!allSigns.equals("n")))) {
                            System.out.println("Die Eingabe war falsch! Für Ja \"y\" und Nein \"n\" eingeben! ");
                        } else {

                            if (allSigns.equals("y")) {

                                String passwordAllChar = generateRandomPasswordspecialCharacter(length);

                                System.out.println("Dein Passwort mit Sonderzeichen und Länge " + length +
                                        " ist: \n --------------------\n" +
                                        passwordAllChar +
                                        "\n --------------------\n");
                                try {
                                    Path filePath = Files.writeString(p, passwordAllChar);
                                    String s = Files.readString(filePath);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                System.out.println("gebe die Zeichen ein die NICHT dabei sein dürfen:");
                                String signsNotAllowed = scanner.next();
                                String fewSpecialCharsAllowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!~,@%#$^*()-_=+[]{,}|;:.?";

                                for (int i = 1; i <= signsNotAllowed.length(); i++) {
                                    String signAt = signsNotAllowed.substring(i - 1, i);
                                    String newREGEX = "";
                                    if (fewSpecialCharsAllowed.contains(signAt)) {
                                        newREGEX = fewSpecialCharsAllowed.replace(signAt, "");
                                        fewSpecialCharsAllowed = newREGEX;
                                    }

                                }

                                String passwordFewChar = generateRandomPasswordFewspecialCharacter(length, fewSpecialCharsAllowed);

                                System.out.println("Dein Passwort ohne die Zeichen: " + signsNotAllowed + " und Länge " + length + " ist: \n" +
                                        "\n --------------------\n" +
                                        passwordFewChar +
                                        "\n --------------------\n");

                                try {
                                    Path filePath = Files.writeString(p, passwordFewChar);
                                    String s = Files.readString(filePath);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/
}
