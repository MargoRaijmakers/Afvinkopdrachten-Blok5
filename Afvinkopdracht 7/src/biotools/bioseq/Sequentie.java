package biotools.bioseq;

public abstract class Sequentie {

    String sequentie;

    public enum colors {red, yellow, blue, gray}

    public String getSeq() { return sequentie; }

    public void setSeq(String sequentie){ this.sequentie = sequentie; }

    public abstract colors getColor(int i);

    public int getLength() { return sequentie.length(); }

}

