package parking.models;
import java.util.*;
import java.time.LocalDateTime;

public class ticket {
    private int ticketID;
    private vehicle vehicle;
    private parkingSpot parkingSpot;
    private LocalDateTime entryDateTime;
    
    public ticket(int ticketID, LocalDateTime entryDateTime,vehicle vehicle,parkingSpot parkingSpot){
        this.ticketID = ticketID;
        this.entryDateTime = entryDateTime;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getEntryTime(){
        return entryDateTime;
    }

    public int getTicketID(){
        return ticketID;
    }

    public vehicle getVehicle(){
        return vehicle;
    }

    public parkingSpot getParkingSpot(){
        return parkingSpot;
    }
}
