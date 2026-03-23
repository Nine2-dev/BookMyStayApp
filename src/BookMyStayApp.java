import java.util.*;
public class UC6 {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check availability
        if (availability.get(roomType) == null || availability.get(roomType) <= 0) {
            System.out.println("No rooms available for " + roomType + " for guest " + reservation.getGuestName());
            return;
        }

        // Generate unique room ID
        String roomId = generateRoomId(roomType);

        // Track allocated IDs
        allocatedRoomIds.add(roomId);

        // Track rooms by type
        assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
        assignedRoomsByType.get(roomType).add(roomId);

        // Update inventory (reduce count)
        inventory.updateAvailability(roomType, availability.get(roomType) - 1);

        // Confirmation message
        System.out.println("Booking Confirmed!");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + roomType);
        System.out.println("Allocated Room ID: " + roomId);
        System.out.println("-----------------------------");
    }

    private String generateRoomId(String roomType) {
        String prefix = roomType.substring(0, 1).toUpperCase();
        int number = allocatedRoomIds.size() + 1;

        String roomId = prefix + number;

        // Ensure uniqueness
        while (allocatedRoomIds.contains(roomId)) {
            number++;
            roomId = prefix + number;
        }

        return roomId;
    }
}
public class UC6 {

    public static void main(String[] args) {

        System.out.println("Room Allocation System\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking queue (FIFO)
        BookingRequestQueue queue = new BookingRequestQueue();

        // Add booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));
        queue.addRequest(new Reservation("Kumar", "Single"));

        // Allocation service
        RoomAllocationService allocator = new RoomAllocationService();

        // Process queue
        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            allocator.allocateRoom(request, inventory);
        }

        // Final inventory status
        System.out.println("\nRemaining Room Availability:");
        inventory.getRoomAvailability().forEach((type, count) ->
                System.out.println(type + ": " + count)
        );
    }
}