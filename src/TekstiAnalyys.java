//Programmi eesmärk on analüüsida teksti, mille kasutaja programmile ette annab.
// Programm leiab sõnade ja lausete koguarvu, keskmise sõne- ja lausepikkuse,
// tekstis olevad võõrsõnad ja 10 pikimat sõna ning lauset.

import javafx.scene.paint.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TekstiAnalyys extends JPanel {

    public static void main(String[] args) throws FileNotFoundException {
       /* JFrame window = new JFrame();
        window.setSize(640,480);
        window.setTitle("Tekstianalüüs");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

       // Graafika komponent = new Graafika();
       // window.add(komponent);*/
        // Luuakse listid sõnade, lausete ja võõrsõnade isendite jaoks
        ArrayList<Sõna> list1 = new ArrayList<>();
        ArrayList<Lause> list2 = new ArrayList<>();
        ArrayList<Võõrsõna> list3 = new ArrayList<>();
        //Kasutajalt küsitakse, millist faili ta soovib analüüsida
        String sisestatakse = JOptionPane.showInputDialog(null, "Sisesta analüüsitava tekstifaili nimi (kujul failinimi.txt) ", "Teksti sisestamine",
                JOptionPane.QUESTION_MESSAGE);
        File fail = new File(sisestatakse);
        // Tekstifaili sisu loetakse sisse
        Scanner sc = new Scanner(fail, "UTF-8");
        // Luuakse massiivid sõnade ja lausete jaoks, mis loetakse tekstifailist
        String[] tükid = new String[0];
        String[] tükid2 = new String[0];
        String[] tükid3 = new String[0];
        double kokku = 0;
        double kokku2 = 0;
        // Sõnad ja laused pannakse massiividesse
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            tükid = rida.trim().split(" ");
            tükid3 = rida.trim().split(" ");
            tükid2 = rida.trim().split("[.?!]");
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
        //luuakse uus list, kuhu sisestatakse kolm juhuslikult genereeritud lauset.
        // Laused saadakse teises tsüklis loodud listiga, mis annab omakorda listi kõikide lausetega.
        //Nendest lausetest võetaksegi kolm juhuslikku
        // See on tesktianalüüsi seisukohalt vähem oluline osa, aga proovisime randomi ka lisada :)
        ArrayList<Lause> suvalisedLaused = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(list2.size());
            suvalisedLaused.add(list2.get(index));
            suvalisedLaused.get(i);
        }
        //Leitakse sõnade ja lausete keskmised pikkused
        double keskminePikkus = kokku / list1.size();
        double keskmineLausePikkus = kokku2 / list2.size();
        String keskmSõnaLühi = String.format("%.2f", keskminePikkus);
        String keskmLauseLühi = String.format("%.2f", keskmineLausePikkus);
        //Sorteeritakse sõnade list. Vastavas klassis on CompareTo meetod, mis reastab sõnad pikkuse järjekorras
        Collections.sort(list1);
        // Trükitakse välja sõnade ja lausete arv, keskmine sõna- ja lausepikkus

       /* System.out.println("Sõnu kokku: " + list1.size());
        System.out.println("Lauseid kokku: " + list2.size());
        System.out.println("Keskmine sõnepikkus: " + String.format("%.2f", keskminePikkus) + " tähte");
        System.out.println("Keskmine lausepikkus: " + String.format("%.2f", keskmineLausePikkus) + " sõna");
        //Trükitakse välja tekstist leitud võõrsõnad
        System.out.println("\n Tekstist leitud võõrsõnad on: " + list3);
        String suvalised_muditid = suvalisedLaused.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
        //Kuvatakse varasemalt randomiga saadud kolm juhuslikku lauset
        System.out.println("\n Vahel rebitakse ka parimate tekstide puhul asju kontekstist välja. Kontekstist välja rebitud kolm juhuslikku lauset on: \n" + suvalised_muditid);
        //Väike edetabel: kümme kõige pikemat sõna, mis leitakse võttes varasemalt sorteeritud list1-st
        // ehk sõnade listi 10 esimest elementi.
        System.out.println("10 kõige pikemat sõna: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(list1.get(i));
        }
        //Sorteeritakse lausete list. Vastavas klassis on CompareTo meetod, mis reastab laused pikkuse järjekorras
        Collections.sort(list2);
        //Võetakse eelnevalt sorteeritud lausete list ja leitakse 10 esimest elementi
        System.out.println("\n 10 kõige pikemat lauset: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(list2.get(i));
        }*/

        JFrame frame = new JFrame("Tekstianalüüs");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        GridBagConstraints c = new GridBagConstraints();
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Label label1 = new Label("ANALÜÜSI TULEMUSED");
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 10, 0, 10);
        label1.setFont(f1);
        label1.setBackground(Color.LIGHT_GRAY);
        panel.add(label1, c);
        Label label2 = new Label("Sõnu kokku: " + list1.size());
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 0, 10);
        panel.add(label2, c);
        Label label3 = new Label("Lauseid kokku: " + list2.size());
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 10, 0, 10);
        panel.add(label3, c);
        Label label4 = new Label("Keskmine sõnepikkus: " + keskmSõnaLühi + " tähte");
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 10, 0, 10);
        panel.add(label4, c);
        Label label5 = new Label("Keskmine lausepikkus: " + keskmLauseLühi + " sõna");
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5, 10, 0, 10);
        panel.add(label5, c);
        Label label6 = new Label("Tekstist leitud võõrsõnad on: ");
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(5, 10, 0, 10);
        panel.add(label6, c);
        Label label7 = new Label(list3.toString());
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(5, 10, 0, 10);
        panel.add(label7, c);

    }
}



