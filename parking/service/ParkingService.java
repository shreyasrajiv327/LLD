package parking.service;

import parking.models.*;
import parking.models.vehicle.vehicleType;
import parking.models.parkingSpot.parkingSpotType;
import parking.repository.parkingRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingService {

    private final parkingRepository parkingRepository;

    private final Map<vehicleType,
            PriorityQueue<parkingSpot>> availableSpots;

    private final Map<Integer, ticket> tickets;

    private int ticketCounter;

    public ParkingService(parkingRepository parkingRepository) {

        this.parkingRepository = parkingRepository;

        this.availableSpots = new HashMap<>();

        this.availableSpots.put(
                vehicleType.CAR,
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                parkingSpot::getParkingSpotID
                        )
                )
        );

        this.availableSpots.put(
                vehicleType.BIKE,
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                parkingSpot::getParkingSpotID
                        )
                )
        );

        this.availableSpots.put(
                vehicleType.TRUCK,
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                parkingSpot::getParkingSpotID
                        ).thenComparing(parkingSpot::getParkingAvailability)
                )
        );

        this.tickets = new HashMap<>();
        this.ticketCounter = 1;
    }

    public void addParkingSpot(
            int spotId,
            parkingSpotType spotType
    ) {

        if (parkingRepository.parkingExists(spotId)) {
            throw new IllegalArgumentException(
                    "Spot already exists"
            );
        }

        parkingSpot spot =
                new parkingSpot(
                        spotId,
                        spotType,
                        true
                );

        parkingRepository.addParkingSpot(spot);

        availableSpots
                .get(vehicleType.valueOf(
                        spotType.name()
                ))
                .offer(spot);
    }

    public ticket parkVehicle(vehicle vehicle) {

        PriorityQueue<parkingSpot> queue =
                availableSpots.get(
                        vehicle.getVehicleType()
                );

        if (queue.isEmpty()) {
            throw new RuntimeException(
                    "No parking spot available"
            );
        }

        parkingSpot allocatedSpot =
                queue.poll();

        allocatedSpot.occupySpot();

        ticket ticket =
                new ticket(
                        ticketCounter++,
                        LocalDateTime.now(),
                        vehicle,
                        allocatedSpot
                );

        tickets.put(
                ticket.getTicketID(),
                ticket
        );

        return ticket;
    }

    public double unparkVehicle(
            int ticketId
    ) {

        ticket ticket =
                tickets.get(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Invalid ticket"
            );
        }

        parkingSpot spot =
                ticket.getParkingSpot();

        spot.freeSpot();

        availableSpots
                .get(ticket.getVehicle()
                        .getVehicleType())
                .offer(spot);

        Duration duration =
                Duration.between(
                        ticket.getEntryTime(),
                        LocalDateTime.now()
                );

        long minutes =
                duration.toMinutes();

        long hours =
                Math.max(
                        1,
                        (minutes + 59) / 60
                );

        double fee =
                hours *
                getRate(
                        ticket.getVehicle()
                                .getVehicleType()
                );

        tickets.remove(ticketId);

        return fee;
    }

    public List<parkingSpot> getAvailableSpots(
            parkingSpotType type
    ) {

        List<parkingSpot> result =
                new ArrayList<>();

        for (parkingSpot spot :
                parkingRepository.getAllParkingSpots()) {

            if (spot.getParkingSpotType() == type
                    &&
                    spot.getParkingAvailability()) {

                result.add(spot);
            }
        }

        return result;
    }

    private int getRate(
            vehicleType type
    ) {

        switch (type) {

            case CAR:
                return 20;

            case BIKE:
                return 10;

            case TRUCK:
                return 50;

            default:
                throw new IllegalArgumentException();
        }
    }
}