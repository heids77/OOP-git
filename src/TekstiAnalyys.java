import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class TekstilineAnalüüs {
    public static void main(String[] args) throws FileNotFoundException {
        String sisestatakse = JOptionPane.showInputDialog(null, "Sisesta analüüsitava teksti faili nimi ", "Teksti sisestamine",
                JOptionPane.QUESTION_MESSAGE);
        File fail = new File(sisestatakse);
        Scanner sc = new Scanner(fail, "UTF-8");
        ArrayList<Sõna> list1 = new ArrayList<>();
        ArrayList<Lause> list2 = new ArrayList<>();
        ArrayList<Võõrsõna> list3 = new ArrayList<>();
        String[] tükid = new String[0];
        String[] tükid2 = new String[0];
        String[] tükid3 = new String[0];
        double kokku = 0;
        double kokku2 = 0;
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            tükid = rida.trim().split(" ");
            tükid3 = rida.trim().split(" ");
            tükid2 = rida.trim().split("[.]");
            for (int i = 0; i < tükid.length; i++) {
                String nimi = tükid[i].replaceAll(",", "").replaceAll("!", "").replaceAll("\\.", "").replaceAll("\\?", "").replaceAll(" ", "").replaceAll("–", "");
                String strNimi = nimi.toLowerCase();
                int intNumber = tükid[i].length();
                kokku = kokku + tükid[i].length();
                if (tükid[i].length() < 2) {
                    kokku = kokku - tükid[i].length();
                }
                if (intNumber > 1) {
                    Sõna a = new Sõna(strNimi, intNumber);
                    list1.add(a);
                }
            }

            for (int i = 0; i < tükid2.length; i++) {
                String nimi2 = tükid2[i];
                int intNumber2 = Lause.mituSõnaLauses(nimi2);
                kokku2 = kokku2 + intNumber2;
                if (intNumber2 > 1) {
                    Lause b = new Lause(nimi2, intNumber2);
                    list2.add(b);
                }
            }
            for (int i = 0; i < tükid3.length; i++) {
                String nimi = tükid[i].replaceAll(",", "").replaceAll("!", "").replaceAll("\\.", "").replaceAll("\\?", "").replaceAll(" ", "").replaceAll("–", "");
                String strNimi = nimi.toLowerCase();
                Võõrsõna c = new Võõrsõna(strNimi);
                if (c.võõrsõnadeLeidja(strNimi) == true) {
                    list3.add(c);
                }
            }


            ArrayList<Lause> suvalisedLaused = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Random randomGenerator = new Random();
                int index = randomGenerator.nextInt(list2.size());
                suvalisedLaused.add(list2.get(index));
                suvalisedLaused.get(i);
                System.out.println(suvalisedLaused);
            }


            double keskminePikkus = kokku / list1.size();
            double keskmineLausePikkus = kokku2 / list2.size();
            Collections.sort(list1);
            String strList1 = list1.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
            System.out.println("Sõnu kokku: " + list1.size());
            System.out.println("Lauseid kokku: " + list2.size());
            System.out.println("Keskmine sõnepikkus: " + String.format("%.2f", keskminePikkus) + " tähte");
            System.out.println("Keskmine lausepikkus: " + String.format("%.2f", keskmineLausePikkus) + " sõna");
            System.out.println("Sõned pikkuse järjekorras: ");
            System.out.println(strList1);
            Collections.sort(list2);
            String strList2 = list2.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
            System.out.println(strList2);
            System.out.println("Tekstist leitud võõrsõnad on: " + list3);
            String suvalised_muditid = suvalisedLaused.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");

            System.out.println("Kontekstist välja rebitud kolm suvalist lauset on: " + suvalised_muditid);


        }

    }
}


