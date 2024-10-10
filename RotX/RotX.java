/*
Programa que consiste en sustituir cadena de letras por la que este en la 13
posición
IndexOF busca un caracter a partir de un indice especifico, si no lo encuentra
devuelve -1.
Mirar stringbuffer
arrylist
*/
import java.util.Scanner;

public class RotX {
    // Arrays de lletres minúscules i majúscules, incloent-hi accents i lletres especials
    private static final char[] abecedariMinuscules = {
        'a', 'à', 'á', 'ä', 'â', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'ë', 'ê', 
        'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 
        'ó', 'ö', 'ô', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 
        'y', 'z'
    };
    
    private static final char[] abecedariMajuscules = {
        'A', 'À', 'Á', 'Ä', 'Â', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'Ë', 'Ê', 
        'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 
        'Ó', 'Ö', 'Ô', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 
        'Y', 'Z'
    };

    // Funció per xifrar amb el desplaçament donat
    public static String xifraRotX(String cadena, int desplaçament) {
        StringBuilder resultat = new StringBuilder();
        
        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resultat.append(desplaçar(c, desplaçament, abecedariMinuscules));
            } else if (Character.isUpperCase(c)) {
                resultat.append(desplaçar(c, desplaçament, abecedariMajuscules));
            } else {
                resultat.append(c);  // Manté els espais i els signes de puntuació
            }
        }
        return resultat.toString();
    }

    // Funció per desxifrar amb el desplaçament donat
    public static String desxifraRotX(String cadena, int desplaçament) {
        return xifraRotX(cadena, -desplaçament);  // Desplaça en la direcció inversa
    }

    // Funció per realitzar la força bruta, provant tots els desplaçaments
    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 1; i <= abecedariMinuscules.length; i++) {
            System.out.println("Desplaçament " + i + ": " + desxifraRotX(cadenaXifrada, i));
        }
    }

    // Funció auxiliar per desplaçar caràcters dins de l'abecedari proporcionat
    private static char desplaçar(char lletra, int desplaçament, char[] abecedari) {
        int posicióOriginal = trobarPosicio(lletra, abecedari);
        if (posicióOriginal == -1) return lletra;  // No canviar si no està a l'abecedari
        
        int novaPosició = (posicióOriginal + desplaçament + abecedari.length) % abecedari.length;
        return abecedari[novaPosició];
    }

    // Funció auxiliar per trobar la posició d'una lletra en l'abecedari
    private static int trobarPosicio(char lletra, char[] abecedari) {
        for (int i = 0; i < abecedari.length; i++) {
            if (abecedari[i] == lletra) {
                return i;
            }
        }
        return -1;  // Retorna -1 si no es troba la lletra
    }

    // Funció main per provar el programa
    public static void main(String[] args) {
        String textOriginal = "Hola, món! Això és una prova.";
        int desplaçament = 3;
        
        // Prova de xifrat
        String textXifrat = xifraRotX(textOriginal, desplaçament);
        System.out.println("Text xifrat: " + textXifrat);
        
        // Prova de desxifrat
        String textDesxifrat = desxifraRotX(textXifrat, desplaçament);
        System.out.println("Text desxifrat: " + textDesxifrat);
        
        // Prova de força bruta
        System.out.println("\nForça bruta:");
        forcaBrutaRotX(textXifrat);
    }
}
