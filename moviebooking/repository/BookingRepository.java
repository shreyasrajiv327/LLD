package moviebooking.repository;

import moviebooking.models.Booking;
import java.util.*;

public class BookingRepository {

    private final Map<Integer, Booking> bookings;

    public BookingRepository() {
        bookings = new HashMap<>();
    }

    public void addBooking(Booking booking) {
        bookings.put(
                booking.getBookingId(),
                booking
        );
    }

    public Booking getBooking(int bookingId) {
        return bookings.get(bookingId);
    }

    public boolean bookingExists(
            int bookingId) {

        return bookings.containsKey(
                bookingId
        );
    }

    public void removeBooking(
            int bookingId) {

        bookings.remove(bookingId);
    }
}