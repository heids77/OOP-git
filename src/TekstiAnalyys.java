

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TekstiAnalyys {
    public static void main(String[] args) throws FileNotFoundException {
        File fail = new File("president.txt");
        Scanner sc = new Scanner(fail, "UTF-8");
        ArrayList<Sõna> list1 = new ArrayList<>();
        String[] tükid = new String[0];
        double kokku = 0;
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            tükid = rida.split(" ");
            for (int i = 0; i < tükid.length; i++ ){
                String nimi = tükid[i].replaceAll(",", "").replaceAll("!", "").replaceAll("\\.", "").replaceAll("\\?", "").replaceAll(" ", "").replaceAll("–", "");
                String strNimi = nimi.toLowerCase();
                int intNumber = tükid[i].length();
                kokku = kokku + tükid[i].length();
                Sõna a = new Sõna(strNimi, intNumber);
                list1.add(a);
            }
        }
        double keskminePikkus = kokku / list1.size();
        Collections.sort(list1);
        String strList1 = list1.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
        System.out.println("Keskmine sõnepikkus: " + String.format("%.2f", keskminePikkus));
        System.out.println("Sõned pikkuse järjekorras: ");
        System.out.println(strList1);
    }
}