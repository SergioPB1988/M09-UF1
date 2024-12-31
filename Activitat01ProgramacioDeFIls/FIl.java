public class Fil extends Thread {
    private String nombre;

    public Fil(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nombre + " " + i);
            try {
                // Simulamos una pequeña espera para permitir que el otro hilo también ejecute
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Termina el fil " + nombre);
    }
}
