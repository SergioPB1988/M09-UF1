package iticbcn.xifratge;

import java.util.Random;

public class XifradorMonoalfabetico    {

    private static Random random;
    private static String clave;

    public static void initRandom(String claveSecreta) {
        clave = claveSecreta;
        random = new Random(clave.hashCode());  // Inicializa el random con el hash de la clave
    }

    public static String cifrar(String texto) {
        return transformar(texto, true);
    }

    public static String descifrar(String texto) {
        return transformar(texto, false);
    }

    private static String transformar(String texto, boolean cifrado) {
        StringBuilder resultado = new StringBuilder();
        int j = 0;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int offset = (clave.charAt(j % clave.length()) - base) % 26;
                if (!cifrado) offset = -offset;  // Invertir la operación para el descifrado
                resultado.append((char) ((c - base + offset + 26) % 26 + base));
                j++;
            } else {
                resultado.append(c);  // Si no es letra, no se modifica
            }
        }
        return resultado.toString();
    }
}
