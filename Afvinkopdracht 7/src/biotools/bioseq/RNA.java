package biotools.bioseq;

public class RNA extends Sequentie {

    @Override
    public colors getColor(int i) {
        if (sequentie.toUpperCase().charAt(i) == 'C' || sequentie.toUpperCase().charAt(i) == 'G') {
            return colors.red;
        } else if (sequentie.toUpperCase().charAt(i) == 'A') {
            return colors.yellow;
        } else { return colors.blue; }
    }
}
