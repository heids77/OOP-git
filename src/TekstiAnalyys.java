//Antud programmi eesmärgiks on teha suvalise sisestatud teksti analüüs seal olevate lauste ja sõnade põhjal. Eraldi klassides toimub töö ka võõrsõnadega.
// Programm leiab sõnade ja lausete koguarvu, keskmise sõne- ja lausepikkuse, tekstis olevad võõrsõnad ja 10 pikimat sõna ning lauset.

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class TekstiAnalyys {
    public static void main(String[] args) throws FileNotFoundException {
        //Kasutajalt küsitakse, millist faili ta soovib analüüsida
        String sisestatakse = JOptionPane.showInputDialog(null, "Sisesta analüüsitava teksti faili nimi ", "Teksti sisestamine",
                JOptionPane.QUESTION_MESSAGE);
        //Sisestatud fail läheb analüüsimisele
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
            //Esimene tsükkel klassi Sõna sisendi saamiseks tekstist
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
//Teine tsükkel, kus saadakse klassi Lause sisend
            for (int i = 0; i < tükid2.length; i++) {
                String nimi2 = tükid2[i];
                int intNumber2 = Lause.mituSõnaLauses(nimi2);
                kokku2 = kokku2 + intNumber2;
                if (intNumber2 > 1) {
                    Lause b = new Lause(nimi2, intNumber2);
                    list2.add(b);
                }
            }
            //Kolmas tsükkel, kus saadakse klassi Võõrsõna sisend
            for (int i = 0; i < tükid3.length; i++) {
                String nimi3 = tükid3[i].replaceAll(",", "").replaceAll("!", "").replaceAll("\\.", "").replaceAll("\\?", "").replaceAll(" ", "").replaceAll("–", "");
                String strNimi3 = nimi3.toLowerCase();
                Võõrsõna c = new Võõrsõna(strNimi3);
                if (c.võõrsõnadeLeidja(strNimi3) == true) {
                    list3.add(c);
                }
            }

        }
        //luuakse uus list, kuhu sisestatakse kolm suvaliselt genereeritud lauset. Lauset saadakse teises tsüklis loodud listiga, mis annab omakorda listi kõikide lausetega.
        //Nendest lausetest võetaksegi kolm suvalist
        ArrayList<Lause> suvalisedLaused = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(list2.size());
            suvalisedLaused.add(list2.get(index));
            suvalisedLaused.get(i);
        }
        //Leitakse sõnade ja lausete keskmised pikkused jagades listi, kuhu on salvestatud kas kõik sõnad või laused, pikkusega seal olevate elementide arvu
        double keskminePikkus = kokku / list1.size();
        double keskmineLausePikkus = kokku2 / list2.size();
        //Statistikast sorteeritakse sõnade list
        Collections.sort(list1);
        String strList1 = list1.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
        System.out.println("Sõnu kokku: " + list1.size());
        System.out.println("Lauseid kokku: " + list2.size());
        System.out.println("Keskmine sõnepikkus: " + String.format("%.2f", keskminePikkus) + " tähte");
        System.out.println("Keskmine lausepikkus: " + String.format("%.2f", keskmineLausePikkus) + " sõna");
        //Prinditakse välja tekstist leitud võõrsõnad
        System.out.println("\n Tekstist leitud võõrsõnad on: " + list3);
        String suvalised_muditid = suvalisedLaused.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
        //Kuvatakse varasemalt randomiga saadud kolm suvalist lauset
        System.out.println("\n Vahel rebitakse ka parimate tekstide puhul asju kontekstist välja. Kontekstist välja rebitud kolm suvalist lauset on: \n" + suvalised_muditid);
        //Väike edetabel: kümme kõige pikemat sõna, mis leitakse võttes varasemalt sorteeritud list1 ehk sõnade listi 10 esimest elementi.
        System.out.println("10 kõige pikemat sõna: ");
        //System.out.println(strList1);
        for (int i = 0; i < 10; i++){
            System.out.print(list1.get(i));
        }
        //Statistika jaoks sorteeritakse lausete list
        Collections.sort(list2);
        //String strList2 = list2.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
        //System.out.println(strList2);
        //Siin samamoodi nagu sõnede puhul, võetakse eelnevalt sorteeritud lausete list ja leitakse 10 esimest elementi
        System.out.println("\n 10 kõige pikemat lauset: ");
        for (int i = 0; i < 10; i++){
            System.out.print(list2.get(i));
        }

    }

}



