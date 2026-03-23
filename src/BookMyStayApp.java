import java.util.Map;

public class UC3{

    public static void main(String[] args) {

        // Create inventory object
        RoomInventory inventory = new RoomInventory();

        // Display initial availability
        System.out.println("Initial Room Availability:");
        displayInventory(inventory.getRoomAvailability());

        // Update availability
        System.out.println("\nUpdating Room Availability...");
        inventory.updateAvailability("Single", 8);
        inventory.updateAvailability("Double", 5);
        inventory.updateAvailability("Suite", 2);

        // Display updated availability
        System.out.println("\nUpdated Room Availability:");
        displayInventory(inventory.getRoomAvailability());
    }

    // Helper method to print inventory
    private static void displayInventory(Map<String, Integer> inventoryMap) {
        for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
            System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
        }
    }
}