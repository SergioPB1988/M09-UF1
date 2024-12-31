public class MainDemoFil {
    public static void main(String[] args) {
        // Obtenim el fil actual
        Thread filActual = Thread.currentThread();

        // Mostrem les propietats del fil
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + filActual.getPriority());
        System.out.println("Nom -> " + filActual.getName());
        System.out.println("toString() -> " + filActual.toString());
    }
}

