public class Võõrsõna {
    String nimetus;


    public Võõrsõna(String nimetus) {
        this.nimetus = nimetus;
    }

    // uuritakse, kas sõna on võõrsõna või mitte
    public boolean võõrsõnadeLeidja(String nimetus) {
        String[] fšzž = {"f", "š", "z", "ž"};
        for (String täht : fšzž) {
            if (nimetus.toLowerCase().contains(täht)) {
                return true;
            } else if (nimetus.toLowerCase().startsWith("g") || nimetus.toLowerCase().startsWith("b") || nimetus.toLowerCase().startsWith("d")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nimetus;
    }
}
