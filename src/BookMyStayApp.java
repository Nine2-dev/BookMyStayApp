import java.util.ArrayList;
import java.util.List;

public class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

public class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("Booking Report");
        System.out.println("========================");

        if (history.getConfirmedReservations().isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        int count = 0;

        for (Reservation reservation : history.getConfirmedReservations()) {
            count++;
            System.out.println("Booking #" + count);
            System.out.println("Guest: " + reservation.getGuestName());
            System.out.println("Room Type: " + reservation.getRoomType());
            System.out.println("-------------------------");
        }

        System.out.println("Total Bookings: " + count);
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Booking History & Report\n");

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Add confirmed reservations (simulate UC6 output)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}

// Reservation.java
public class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}