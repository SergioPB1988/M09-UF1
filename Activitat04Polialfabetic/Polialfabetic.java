/*
Consejos
    1. inicialitza la aleatorietat amb la mateixa llavor random (contrasenya).
    2. Llegeix una lletra del missatge xifrat
    3. permuta l’alfabet
    4. substitueix la lletra
    5. si queden lletres ves al pas 2 sinó acaba
Exemple
    Missatge = "Do"
    a b c d e f g h i j k l m n o p q r s t u v w x y z (Alfabet original)
    lletra = D
    permuta l’alfabet: Permutació1: j p z a y n b v e q u w s f o i k l m v c d g h r
    lletraXifrada = A

    lletra = o
    permuta l’alfabet: Permutació2: i k l m w s f o v c d g n b v e q u h r j p z a y
    LletraXifrada = q
    Missatge xifrat = "Aq"
*/

import java.util.Random;

public class Polialfabetic {
    private static final char[] alfabetOriginal = {
        'a', 'à', 'á', 'ä', 'â', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'ë', 'ê', 
        'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 
        'ó', 'ö', 'ô', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 
        'y', 'z', 'A', 'À', 'Á', 'Ä', 'Â', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 
        'Ë', 'Ê', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 
        'O', 'Ò', 'Ó', 'Ö', 'Ô', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 
        'W', 'X', 'Y', 'Z'
    };
    
    private static Random rand;
    private static char[] alfabetPermutat;

    // Inicializa el random con la clave secreta
    public static void initRandom(String clauSecreta) {
        rand = new Random(clauSecreta.hashCode());
    }

    // Método para permutar el alfabeto
    public static void permutaAlfabet() {
        alfabetPermutat = alfabetOriginal.clone();
        for (int i = 0; i < alfabetPermutat.length; i++) {
            int j = rand.nextInt(alfabetPermutat.length);
            // Intercambiar alfabetPermutat[i] con alfabetPermutat[j]
            char temp = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[j];
            alfabetPermutat[j] = temp;
        }
    }

    // Método para cifrar el mensaje polialfabético
    public static String xifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();
        for (char c : msg.toCharArray()) {
            permutaAlfabet();  // Permutamos el alfabeto para cada letra
            int pos = trobarPosicio(c, alfabetOriginal);
            if (pos != -1) {
                resultat.append(alfabetPermutat[pos]);
            } else {
                resultat.append(c); // Mantiene caracteres que no son letras
            }
        }
        return resultat.toString();
    }

    // Método para descifrar el mensaje polialfabético
    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder resultat = new StringBuilder();
        for (char c : msgXifrat.toCharArray()) {
            permutaAlfabet();  // Permutamos el alfabeto para cada letra
            int pos = trobarPosicio(c, alfabetPermutat);
            if (pos != -1) {
                resultat.append(alfabetOriginal[pos]);
            } else {
                resultat.append(c); // Mantiene caracteres que no son letras
            }
        }
        return resultat.toString();
    }

    // Función auxiliar para encontrar la posición de una letra en el alfabeto
    private static int trobarPosicio(char lletra, char[] alfabet) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == lletra) {
                return i;
            }
        }
        return -1;  // Retorna -1 si no se encuentra la letra
    }

    // Método main
    public static void main(String[] args) {
        String msgs[] = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String clauSecreta = "secreta123";  // clave secreta
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);  // Inicializamos el random con la clave
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);  // Inicializamos el random con la clave
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}

