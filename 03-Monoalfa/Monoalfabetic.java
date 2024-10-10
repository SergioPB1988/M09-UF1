import java.util.Random;

public class Monoalfabetic {
    private static final char[] alfabetOriginal = {
        'a', 'à', 'á', 'ä', 'â', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'ë', 'ê', 
        'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 
        'ó', 'ö', 'ô', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 
        'y', 'z', 'A', 'À', 'Á', 'Ä', 'Â', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 
        'Ë', 'Ê', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 
        'O', 'Ò', 'Ó', 'Ö', 'Ô', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 
        'W', 'X', 'Y', 'Z'
    };
    
    private static char[] alfabetPermutat;

    // Mètode per permutar l'alfabet
    public static char[] permutaAlfabet() {
        char[] permutat = alfabetOriginal.clone();
        Random rand = new Random();

        for (int i = 0; i < permutat.length; i++) {
            int j = rand.nextInt(permutat.length);
            // Intercanviar permutat[i] i permutat[j]
            char temp = permutat[i];
            permutat[i] = permutat[j];
            permutat[j] = temp;
        }
        alfabetPermutat = permutat;
        return permutat;
    }

    // Mètode per xifrar la cadena
    public static String xifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();
        
        for (char c : cadena.toCharArray()) {
            int pos = trobarPosicio(c, alfabetOriginal);
            if (pos != -1) {
                resultat.append(alfabetPermutat[pos]);
            } else {
                resultat.append(c); // Manté els caràcters que no són lletres
            }
        }
        return resultat.toString();
    }

    // Mètode per desxifrar la cadena
    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();
        
        for (char c : cadena.toCharArray()) {
            int pos = trobarPosicio(c, alfabetPermutat);
            if (pos != -1) {
                resultat.append(alfabetOriginal[pos]);
            } else {
                resultat.append(c); // Manté els caràcters que no són lletres
            }
        }
        return resultat.toString();
    }

    // Funció auxiliar per trobar la posició d'una lletra en l'alfabet
    private static int trobarPosicio(char lletra, char[] alfabet) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == lletra) {
                return i;
            }
        }
        return -1;  // Retorna -1 si no es troba la lletra
    }

    // Funció main per provar el programa
    public static void main(String[] args) {
        String textOriginal = "Hola, món! Això és una prova.";
        
        // Permutem l'alfabet
        permutaAlfabet();
        
        // Prova de xifrat
        String textXifrat = xifraMonoAlfa(textOriginal);
        System.out.println("Text xifrat: " + textXifrat);
        
        // Prova de desxifrat
        String textDesxifrat = desxifraMonoAlfa(textXifrat);
        System.out.println("Text desxifrat: " + textDesxifrat);
    }
}

