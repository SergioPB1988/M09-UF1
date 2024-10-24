/*
Aquest mètode xifraAES és el que s'encarrega de xifrar el missatge:

Convertir el missatge a bytes: Primer, el missatge (que és un String) es converteix en un array de bytes amb msg.getBytes("UTF-8").

Generar l'IV: Fem servir l'algorisme SHA-256 per generar un hash de la clau, i en seleccionem els primers 16 bytes per crear l'IV. Això es fa amb Arrays.copyOfRange(...).

Especificar l'IV: Creem una instància d'IvParameterSpec amb aquest IV.

Generar el hash de la clau: Cridem el mètode generaHash per obtenir el hash de la clau amb SHA-256.

Crear la clau AES: Utilitzem SecretKeySpec per crear una clau AES basada en el hash.

Inicialitzar el xifrat: Amb Cipher.getInstance(FORMAT_AES) creem una instància del xifrat AES en mode CBC amb padding, i l'inicialitzem en mode xifrat amb la clau i l'IV.

Xifrar el missatge: El missatge es xifra amb cipher.doFinal(msgBytes).

Combinar l'IV amb el missatge xifrat: Finalment, combinem l'IV i el missatge xifrat en un únic array de bytes perquè sigui més fàcil desxifrar-lo després.
***************************************************************************************************************************************************************************
Aquest mètode desxifraAES fa el procés invers per desxifrar el missatge:

Separar l'IV del missatge xifrat: Extraiem els primers 16 bytes (l'IV) i la resta és el missatge xifrat.

Crear l'objecte IvParameterSpec: Creem de nou una instància d'IvParameterSpec amb l'IV extret.

Obtenir la clau hash: Generem el hash de la clau de la mateixa manera que vam fer en el xifrat.

Crear la clau AES: Utilitzem el hash de la clau per crear una instància de SecretKeySpec.

Inicialitzar el desxifrat: Inicialitzem el Cipher en mode desxifrat i utilitzem la clau i l'IV.

Desxifrar el missatge: Utilitzem cipher.doFinal(msgXifrat) per desxifrar el missatge.

Convertir a String: Finalment, convertim els bytes desxifrats a una cadena (String) utilitzant UTF-8.
*/

// Clase principal para cifrar y descifrar AES
import javax.crypto.Cipher;
// Se encarga de gestionar el vector de inicializacion (IV) que se necesita para el modo CBC
import javax.crypto.spec.IvParameterSpec;
// Define la clave AES
import javax.crypto.spec.SecretKeySpec;
// Se utiliza para crear un hash de la clave con algoritmo SHA-256
import java.security.MessageDigest;
import java.util.Arrays;
// Se usa para manipular arrays y codificar en Base64(para mostrar el cifrado de fomra legible)
import java.util.Base64;

public class AES {
    // Define el argoritmo de cifrado, en este caso AES
    public static final String ALGORISME_XIFRAT = "AES";
    // Define el algoritmo de hash que usaremos para usar un hash de la clave (SHA-256)
    public static final String ALGORISME_HASH = "SHA-256";
    // Especifica el formato del cifrado, que es AES con CBC i padding PKCS5
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    // Es lammedida del vector de inicializacion (IV) es de 16 bytes.
    private static final int MIDA_IV = 16;
    // Declaracion del arry de bytes 16 bits
    private static byte[] iv = new byte[MIDA_IV];
    // Clave secreta que se utiliza para cifrar y descifrar el mensaje 
    private static final String CLAU = "LaClauSecretaQueVulguis";

    // Mètode per generar hash (SHA-256) de la clau
    private static byte[] generaHash(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        return digest.digest(clau.getBytes("UTF-8"));
    }

    // Mètode per xifrar el missatge
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l'String del missatge
        byte[] msgBytes = msg.getBytes("UTF-8");

        // Generar l'IV a partir del hash de la clau
        iv = Arrays.copyOfRange(MessageDigest.getInstance(ALGORISME_HASH).digest(clau.getBytes("UTF-8")), 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Fer hash de la clau (SHA-256)
        byte[] clauHash = generaHash(clau);

        // Crear la clau secreta AES
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Crear l'objecte Cipher per xifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Xifrar el missatge
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        // Combinar l'IV i la part xifrada
        byte[] ivIMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, ivIMsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, ivIMsgXifrat, MIDA_IV, msgXifrat.length);

        return ivIMsgXifrat;
    }

    // Mètode per desxifrar el missatge
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extreure l'IV
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure la part xifrada
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Fer hash de la clau
        byte[] clauHash = generaHash(clau);

        // Crear la clau secreta AES
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Crear l'objecte Cipher per desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Desxifrar el missatge
        byte[] msgDesxifratBytes = cipher.doFinal(msgXifrat);

        // Retornar el missatge desxifrat
        return new String(msgDesxifratBytes, "UTF-8");
    }

    // Mètode principal per provar el codi
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                // Xifrar el missatge
                bXifrats = xifraAES(msg, CLAU);

                // Desxifrar el missatge
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            // Mostrar resultats
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + Base64.getEncoder().encodeToString(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}


