public class Futbolista extends Thread {
    private int ngols;
    private int ntirades;

    // Constants
    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    public Futbolista(String nom) {
        super(nom);  // Ús del constructor de la classe Thread per donar-li el nom al jugador
        this.ngols = 0;
        this.ntirades = 0;
    }

    // Mètode run que s'executa quan el fil comença
    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (Math.random() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }

    public int getNTirades() {
        return ntirades;
    }

    public static void main(String[] args) {
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        String[] noms = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"};

        // Creació i inici dels fils
        System.out.println("Inici dels xuts --------------------");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
            jugadors[i].start();  // Inicia el fil
        }

        // Espera a que tots els fils acabin
        for (int i = 0; i < NUM_JUGADORS; i++) {
            try {
                jugadors[i].join();  // Espera que acabi el fil
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostra les estadístiques
        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");

        for (int i = 0; i < NUM_JUGADORS; i++) {
            System.out.println(jugadors[i].getName() + " -> " + jugadors[i].getNgols() + " gols");
        }
    }
}

