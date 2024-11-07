package iticbcn.xifratge;

import java.util.*;

public class Monoalfabetico {

    private static String alfabetoOriginal = "abcdefghijklmnopqrstuvwxyzáéíóúüç";
    private static String alfabetoPermutado;

    // Constructor para inicializar la permutación del alfabeto
    static {
        permutaAlfabeto();
    }

    private static void permutaAlfabeto() {
        Random random = new Random();
        List<Character> listaAlfabeto = new ArrayList<>();
        for (char c : alfabetoOriginal.toCharArray()) {
            listaAlfabeto.add(c);
        }
        Collections.shuffle(listaAlfabeto, random);
        StringBuilder sb = new StringBuilder();
        for (char c : listaAlfabeto) {
            sb.append(c);
        }
        alfabetoPermutado = sb.toString();
    }

    public static String cifrar(String texto) {
        return transformar(texto, alfabetoOriginal, alfabetoPermutado);
    }

    public static String descifrar(String texto) {
        return transformar(texto, alfabetoPermutado, alfabetoOriginal);
    }

    private static String transformar(String texto, String alfabetoOrigen, String alfabetoDestino) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            int index = alfabetoOrigen.indexOf(c);
            if (index != -1) {
                resultado.append(alfabetoDestino.charAt(index));
            } else {
                resultado.append(c);  // Si no está en el alfabeto, deja el carácter tal cual.
            }
        }
        return resultado.toString();
    }
}
