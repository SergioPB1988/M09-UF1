package iticbcn.xifratge;

public class XifradorAES implements Xifrador {
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // Implementación del cifrado AES (esto es solo un ejemplo, necesitarás integrar la librería AES)
        try {
            byte[] cipherText = msg.getBytes(); // Aquí debe ir la lógica real de cifrado
            return new TextXifrat(cipherText);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al cifrar el mensaje con AES");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // Implementación del descifrado AES (esto es solo un ejemplo, necesitarás integrar la librería AES)
        try {
            return new String(xifrat.getBytes()); // Aquí debe ir la lógica real de descifrado
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al descifrar el mensaje con AES");
        }
    }
}

