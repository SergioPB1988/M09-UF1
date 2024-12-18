public class Principal {
    public static void main(String[] args) throws InterruptedException {
        // Creamos los hilos
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        // Iniciamos los hilos
        juan.start();
        pepe.start();

        // Esperamos que ambos hilos terminen
        juan.join();
        pepe.join();

        // Mensaje final cuando el hilo principal termine
        System.out.println("Termina thread main");
    }
}
