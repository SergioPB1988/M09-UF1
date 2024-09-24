/*
Programa que consiste en sustituir cadena de letras por la que este en la 13
posición
IndexOF busca un caracter a partir de un indice especifico, si no lo encuentra
devuelve -1.
*/
import java.util.Scanner;

public class Rot13 {
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String xifraRot13(String cadena) {
        String resultado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (MINUSCULAS.indexOf(c) != -1) {
                int pos = MINUSCULAS.indexOf(c);
                resultado += MINUSCULAS.charAt((pos + 13) % 26);
            } else if (MAYUSCULAS.indexOf(c) != -1) {
                int pos = MAYUSCULAS.indexOf(c);
                resultado += MAYUSCULAS.charAt((pos + 13) % 26);
            } else {
                resultado += c;
            }
        }
        return resultado;
    }

    public static String desxifraRot13(String cadena) {
        String resultado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (MINUSCULAS.indexOf(c) != -1) {
                int pos = MINUSCULAS.indexOf(c);
                resultado += MINUSCULAS.charAt((pos + 13) % 26);
            } else if (MAYUSCULAS.indexOf(c) != -1) {
                int pos = MAYUSCULAS.indexOf(c);
                resultado += MAYUSCULAS.charAt((pos + 13) % 26);
            } else {
                resultado += c;
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una palabra para codificarla");
        String texto = scanner.nextLine();
        if(texto.isBlank()) { 
            System.out.println("Texto no valido");
        } else {
            String cifrado = xifraRot13(texto);
            String descifrado = desxifraRot13(cifrado);

            System.out.println("Original: " + texto);
            System.out.println("Cifrado: " + cifrado);
            System.out.println("Descifrado: " + descifrado);
        }
    }
}
