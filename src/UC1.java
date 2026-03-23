public class UC1 {

    public static void main(String[] args) {

        printWelcomeMessage();
        confirmStartup();

    }

    // Displays welcome message
    private static void printWelcomeMessage() {
        System.out.println("======================================");
        System.out.println("   WELCOME TO HOTEL BOOKING SYSTEM   ");
        System.out.println("======================================");
    }

    // Confirms system startup
    private static void confirmStartup() {
        System.out.println("System is starting...");
        System.out.println("Initialization complete.");
        System.out.println("Application started successfully!");
    }
}