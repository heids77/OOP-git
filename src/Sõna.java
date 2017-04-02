

/**
 * Created by heids on 31.03.2017.
 */
public class Sõna implements Comparable<Sõna> {
    String nimetus;
    int pikkus;

    @Override
    public int compareTo(Sõna võrreldav) {
        if (pikkus < võrreldav.pikkus) {
            return 1;
        }
        if (pikkus > võrreldav.pikkus) {
            return -1;
        }
        return 0;
    }

    public Sõna(String nimetus, int pikkus) {
        this.nimetus = nimetus;
        this.pikkus = pikkus;
    }

    public int getPikkus() {
        return pikkus;
    }

    public String getNimetus() {
        return nimetus;
    }

    @Override
    public String toString() {
        return nimetus + " " + pikkus + "\n";
    }
}
