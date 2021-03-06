public class Lause implements Comparable<Lause> {
    String lauseIse;
    int lausePikkus;
    // võrreldakse lausepikkuseid
    @Override
    public int compareTo(Lause võrreldav) {
        if (lausePikkus < võrreldav.lausePikkus) {
            return 1;
        }
        if (lausePikkus > võrreldav.lausePikkus) {
            return -1;
        }
        return 0;
    }

    public Lause(String lauseIse, int lausePikkus) {
        this.lauseIse = lauseIse;
        this.lausePikkus = lausePikkus;
    }
    // meetod leiab, mitu sõna on lauses
    public static int mituSõnaLauses(String lauseIse) {
        int count = 0;
        String sõnad[] = lauseIse.split(" ");
        for (int i = 0; i < sõnad.length; i++) {
            if (sõnad[i].length() < 2) {
                count = count + 1;
            }
        }
        int mituSõna = sõnad.length - count;
        return mituSõna;
    }

    public String getLauseIse() {
        return lauseIse;
    }

    public int getLausePikkus() {
        return lausePikkus;
    }

    @Override
    public String toString() {
        return lauseIse + " " + lausePikkus + "\n";
    }
}
