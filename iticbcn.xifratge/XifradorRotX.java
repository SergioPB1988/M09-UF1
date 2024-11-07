package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        if (desplaçament < 0 || desplaçament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        StringBuilder resultat = new StringBuilder();
        for (char c : msg.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resultat.append(desplaçar(c, desplaçament, abecedariMinuscules));
            } else if (Character.isUpperCase(c)) {
                resultat.append(desplaçar(c, desplaçament, abecedariMajuscules));
            } else {
                resultat.append(c);
            }
        }

        return new TextXifrat(resultat.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        if (desplaçament < 0 || desplaçament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        String cadenaXifrada = new String(xifrat.getBytes());
        return xifra(cadenaXifrada, String.valueOf(-desplaçament)).toString();
    }

    private char desplaçar(char lletra, int desplaçament, char[] abecedari) {
        int posicióOriginal = trobarPosicio(lletra, abecedari);
        if (posicióOriginal == -1) return lletra;
        
        int novaPosició = (posicióOriginal + desplaçament + abecedari.length) % abecedari.length;
        return abecedari[novaPosició];
    }

    private int trobarPosicio(char lletra, char[] abecedari) {
        for (int i = 0; i < abecedari.length; i++) {
            if (abecedari[i] == lletra) {
                return i;
            }
        }
        return -1;
    }
}
