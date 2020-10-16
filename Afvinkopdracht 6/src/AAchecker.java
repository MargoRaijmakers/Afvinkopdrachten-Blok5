/**
 * @Author Martijn van der Bruggen
 * @Date  1-september-2019
 */

public class AAchecker {

    public static final String aminoacids = "ARNDCFQEGHILKMPSTWYV";

    /**
     * Deze functie checkt of het teken een aminozuur is
     *
     * @param aa het teken dat wel of niet een aminozuur is
     * @throws notanaa error voor als het teken geen aminozuur is
     */
    public static void aachecker(String aa) throws notanaa {
        if (!aminoacids.contains(aa)) {
            throw new notanaa("Dit is een niet bestaand aminozuur: " + aa);
        }
    }
}

class notanaa extends Exception {

    public notanaa(String error) {
        super(error);
    }
}

