package moviebooking.models;

import java.util.List;

public class Booking {

    private int bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;

    public Booking(int bookingId,
                   User user,
                   Show show,
                   List<Seat> seats) {

        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = seats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}