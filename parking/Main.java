package parking;

import parking.models.*;
import parking.models.vehicle.vehicleType;
import parking.models.parkingSpot.parkingSpotType;
import parking.repository.parkingRepository;
import parking.service.ParkingService;

public class Main {

    public static void main(String[] args) {

        parkingRepository repository =
                new parkingRepository();

        ParkingService parkingService =
                new ParkingService(repository);

        // Add spots
        parkingService.addParkingSpot(
                1,
                parkingSpotType.CAR
        );

        parkingService.addParkingSpot(
                2,
                parkingSpotType.CAR
        );

        parkingService.addParkingSpot(
                3,
                parkingSpotType.BIKE
        );

        parkingService.addParkingSpot(
                4,
                parkingSpotType.TRUCK
        );

        // Create vehicles
        vehicle car1 =
                new vehicle(
                        101,
                        vehicleType.CAR
                );

        vehicle car2 =
                new vehicle(
                        102,
                        vehicleType.CAR
                );

        vehicle bike1 =
                new vehicle(
                        201,
                        vehicleType.BIKE
                );

        // Park vehicles
        ticket t1 =
                parkingService.parkVehicle(car1);

        System.out.println(
                "Car1 parked. Ticket ID: "
                + t1.getTicketID()
        );

        ticket t2 =
                parkingService.parkVehicle(car2);

        System.out.println(
                "Car2 parked. Ticket ID: "
                + t2.getTicketID()
        );

        ticket t3 =
                parkingService.parkVehicle(bike1);

        System.out.println(
                "Bike parked. Ticket ID: "
                + t3.getTicketID()
        );

        // Available CAR spots
        System.out.println(
                "Available CAR spots: "
                + parkingService
                        .getAvailableSpots(
                                parkingSpotType.CAR
                        )
                        .size()
        );

        // Unpark car1
        double fee =
                parkingService.unparkVehicle(
                        t1.getTicketID()
                );

        System.out.println(
                "Parking fee: ₹" + fee
        );

        System.out.println(
                "Available CAR spots after unparking: "
                + parkingService
                        .getAvailableSpots(
                                parkingSpotType.CAR
                        )
                        .size()
        );
    }
}