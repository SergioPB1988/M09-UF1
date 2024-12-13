import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public static int npass = 0; // Contador de passwords probados

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz"; //valor aleatorio añadido a la contraseña antes de hacer el hash para aumentar la seguridad.
        String pw = "aaabF!";
        Hashes h = new Hashes();

        // Generamos los hashes con SHA-512 y PBKDF2
        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }

    // Método para calcular el tiempo transcurrido
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        long days = (millis / (1000 * 60 * 60 * 24));

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis % 1000);
    }

    // Método para generar el hash con SHA-512
    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    // Método para generar el hash con PBKDF2
    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    // Método de fuerza bruta con bucles anidados
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0; // Reiniciar el contador de passwords probados
        String charset = "abcdefABCDEF1234567890!";
        char[] chars = charset.toCharArray();
        
        // Bucle de longitud 1
        for (char c1 : chars) {
            String pw = "" + c1;
            if (tryPassword(pw, alg, hash, salt)) return pw;
            
            // Bucle de longitud 2
            for (char c2 : chars) {
                pw = "" + c1 + c2;
                if (tryPassword(pw, alg, hash, salt)) return pw;
                
                // Bucle de longitud 3
                for (char c3 : chars) {
                    pw = "" + c1 + c2 + c3;
                    if (tryPassword(pw, alg, hash, salt)) return pw;
                    
                    // Bucle de longitud 4
                    for (char c4 : chars) {
                        pw = "" + c1 + c2 + c3 + c4;
                        if (tryPassword(pw, alg, hash, salt)) return pw;
                        
                        // Bucle de longitud 5
                        for (char c5 : chars) {
                            pw = "" + c1 + c2 + c3 + c4 + c5;
                            if (tryPassword(pw, alg, hash, salt)) return pw;
                            
                            // Bucle de longitud 6
                            for (char c6 : chars) {
                                pw = "" + c1 + c2 + c3 + c4 + c5 + c6;
                                if (tryPassword(pw, alg, hash, salt)) return pw;
                            }
                        }
                    }
                }
            }
        }

        return null;  // Si no se encuentra ninguna coincidencia
    }

    // Método auxiliar para intentar un password y compararlo con el hash
    private boolean tryPassword(String pw, String alg, String hash, String salt) throws Exception {
        npass++;  // Incrementar el número de passwords probados
        String generatedHash = generateHash(pw, alg, salt);
        return generatedHash.equals(hash);
    }

    // Método auxiliar para generar el hash con el algoritmo especificado
    private String generateHash(String pw, String alg, String salt) throws Exception {
        if (alg.equals("SHA-512")) {
            return getSHA512AmbSalt(pw, salt);
        } else if (alg.equals("PBKDF2")) {
            return getPBKDF2AmbSalt(pw, salt);
        }
        return null;
    }
}
