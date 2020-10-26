package biotools.bioseq;

public class DNA extends Sequentie {

    float gcperc;

    public float getGCperc() {
        float gccount = 0;
        for (int i = 0; i < sequentie.length(); i++) {
            if (sequentie.toUpperCase().charAt(i) == 'G' || sequentie.toUpperCase().charAt(i) == 'C') {
                gccount++;
            }
        }
        gcperc = gccount / sequentie.length() * 100;
        return gcperc;
    }

    @Override
    public colors getColor(int i) {
        if (sequentie.toUpperCase().charAt(i) == 'C' || sequentie.toUpperCase().charAt(i) == 'G') {
            return colors.red;
        } else { return colors.yellow; }
    }
}
