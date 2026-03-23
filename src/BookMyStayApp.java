import java.util.*;

public class CancellationService {

    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    // Register confirmed booking
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    // Cancel booking and restore inventory
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID. Cannot cancel.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Restore inventory
        Map<String, Integer> availability = inventory.getRoomAvailability();
        inventory.updateAvailability(roomType, availability.get(roomType) + 1);

        // Track rollback using stack
        releasedRoomIds.push(reservationId);

        // Remove from active bookings
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully for ID: " + reservationId);
    }

    // Show rollback history
    public void showRollbackHistory() {
        System.out.println("\nRollback History (Last Cancelled First):");

        if (releasedRoomIds.isEmpty()) {
            System.out.println("No cancellations yet.");
            return;
        }

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Cancelled Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation System\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize cancellation service
        CancellationService cancellationService = new CancellationService();

        // Simulate confirmed bookings (from UC6)
        cancellationService.registerBooking("S1", "Single");
        cancellationService.registerBooking("D2", "Double");
        cancellationService.registerBooking("S3", "Suite");

        // Cancel bookings
        cancellationService.cancelBooking("D2", inventory);
        cancellationService.cancelBooking("S1", inventory);

        // Show rollback history
        cancellationService.showRollbackHistory();

        // Display updated inventory
        System.out.println("\nUpdated Room Availability:");
        inventory.getRoomAvailability().forEach((type, count) ->
                System.out.println(type + ": " + count)
        );
    }
}