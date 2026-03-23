import java.util.Map;
public class UC4 {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check and display Single Room availability
        if (availability.get("Single") > 0) {
            System.out.println("Single Room Available:");
            displayRoom(singleRoom, availability.get("Single"));
        }

        // Check and display Double Room availability
        if (availability.get("Double") > 0) {
            System.out.println("\nDouble Room Available:");
            displayRoom(doubleRoom, availability.get("Double"));
        }

        // Check and display Suite Room availability
        if (availability.get("Suite") > 0) {
            System.out.println("\nSuite Room Available:");
            displayRoom(suiteRoom, availability.get("Suite"));
        }
    }

    private void displayRoom(Room room, int count) {
        System.out.println("Type: " + room.getType());
        System.out.println("Price: " + room.getPrice());
        System.out.println("Features: " + room.getFeatures());
        System.out.println("Available Count: " + count);
    }
}