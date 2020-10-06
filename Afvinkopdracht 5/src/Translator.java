/** Translator voor de opdracht One2Three
 * Gebruik deze code ongewijzigd om een
 * vertaler te maken van de eenletterige code
 * voor een aminozuur naar de drieletterige
 * code van een aminozuur.
 * @Author Martijn van der Bruggen
 * @Date  1-september-2019
 */


public class Translator {

    static final String[] ONE = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K"
            , "M", "F", "P", "S", "T", "W", "Y", "V"};
    static final String[] THREE = {"ALA", "ARG", "ASN", "ASP", "CYS", "GLN", "GLU", "GLY"
            ,"HIS", "ILE", "LEU", "LYS", "MET", "PHE", "PRO", "SER"
            ,"THR", "TRP", "TYR", "VAL"
    };

    public static String one2three(String symbol) throws NotAnAA {
        String threeCode = "";
        for (int i = 0; i < ONE.length; i++) {
            if (ONE[i].equals(symbol)) {
                threeCode = THREE[i];
            }
        }
        if (threeCode.equals("")) {
            throw new NotAnAA("Dit is een niet bestaand aminozuur: " + symbol);
        }
        return threeCode;
    }
}

class NotAnAA extends Exception {

    public NotAnAA() {
        super();
    }

    public NotAnAA(String err) {
        super(err);
    }
}