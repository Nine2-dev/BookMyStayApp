import java.io.*;
import java.util.Map;

public class FilePersistenceService {

    // Save inventory to file
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry : inventory.getRoomAvailability().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Load inventory from file
    public void loadInventory(RoomInventory inventory, String filePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.updateAvailability(roomType, count);
                }
            }

            System.out.println("Inventory loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("Data Persistence & Recovery\n");

        String filePath = "inventory.txt";

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Persistence service
        FilePersistenceService persistenceService = new FilePersistenceService();

        // Save current inventory
        persistenceService.saveInventory(inventory, filePath);

        // Modify inventory (simulate runtime changes)
        inventory.updateAvailability("Single", 5);
        inventory.updateAvailability("Double", 2);

        System.out.println("\nModified Inventory:");
        inventory.getRoomAvailability().forEach((type, count) ->
                System.out.println(type + ": " + count)
        );

        // Load inventory back from file (restore state)
        persistenceService.loadInventory(inventory, filePath);

        System.out.println("\nRestored Inventory:");
        inventory.getRoomAvailability().forEach((type, count) ->
                System.out.println(type + ": " + count)
        );
    }
}