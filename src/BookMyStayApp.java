public class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

import java.util.*;

public class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) {
            return 0.0;
        }

        double total = 0;
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation ID: " + reservationId);
        for (AddOnService service : services) {
            System.out.println("- " + service.getServiceName() + " : " + service.getCost());
        }
    }
}

import java.util.*;

public class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) {
            return 0.0;
        }

        double total = 0;
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation ID: " + reservationId);
        for (AddOnService service : services) {
            System.out.println("- " + service.getServiceName() + " : " + service.getCost());
        }
    }
}