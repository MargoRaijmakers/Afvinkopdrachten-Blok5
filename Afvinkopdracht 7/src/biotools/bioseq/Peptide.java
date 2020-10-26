package biotools.bioseq;

public class Peptide extends Sequentie {

    String polair = "NCQSTY";
    String apolair = "AGILMFPWV";

    @Override
    public colors getColor(int i) {
        if (polair.contains(String.valueOf(sequentie.toUpperCase().charAt(i)))) {
            return colors.blue;
        } else if (apolair.contains(String.valueOf(sequentie.toUpperCase().charAt(i)))) {
            return colors.red;
        } else { return colors.gray; }
    }
}
