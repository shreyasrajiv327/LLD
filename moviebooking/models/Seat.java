package moviebooking.models;

public class Seat {

    private int seatId;
    private String seatNumber;
    private boolean available;

    public Seat(int seatId,
                String seatNumber) {

        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.available = true;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void bookSeat() {
        available = false;
    }

    public void freeSeat() {
        available = true;
    }
}