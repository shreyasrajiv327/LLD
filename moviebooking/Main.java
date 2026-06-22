package moviebooking;

import moviebooking.models.*;
import moviebooking.services.BookingService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookingService bookingService =
                new BookingService();

        try {

            // Add Users
            User shreyas =
                    bookingService.addUser(
                            "Shreyas"
                    );

            User rahul =
                    bookingService.addUser(
                            "Rahul"
                    );

            // Add Movie
            Movie movie =
                    bookingService.addMovie(
                            "Interstellar",
                            169
                    );

            // Create Show
            Show show =
                    bookingService.createShow(
                            movie,
                            LocalDateTime.now()
                                    .plusHours(2),
                            10
                    );

            // Available Seats Before Booking
            System.out.println(
                    "Available Seats Before Booking:"
            );

            List<Seat> availableSeats =
                    bookingService
                            .getAvailableSeats(show);

            for (Seat seat : availableSeats) {
                System.out.println(
                        seat.getSeatNumber()
                );
            }

            // Book Seats
            Booking booking =
                    bookingService.bookSeats(
                            shreyas,
                            show,
                            Arrays.asList(
                                    1,
                                    2,
                                    3
                            )
                    );

            System.out.println(
                    "\nBooking Successful!"
            );

            System.out.println(
                    "Booking Id: "
                    + booking.getBookingId()
            );

            // Available Seats After Booking
            System.out.println(
                    "\nAvailable Seats After Booking:"
            );

            availableSeats =
                    bookingService
                            .getAvailableSeats(show);

            for (Seat seat : availableSeats) {
                System.out.println(
                        seat.getSeatNumber()
                );
            }

            // User Bookings
            System.out.println(
                    "\nShreyas Bookings:"
            );

            List<Booking> bookings =
                    bookingService
                            .getUserBookings(
                                    shreyas.getUserId()
                            );

            for (Booking b : bookings) {

                System.out.println(
                        "Booking Id: "
                        + b.getBookingId()
                );
            }

            // Cancel Booking
            bookingService.cancelBooking(
                    booking.getBookingId()
            );

            System.out.println(
                    "\nBooking Cancelled!"
            );

            // Available Seats After Cancellation
            System.out.println(
                    "\nAvailable Seats After Cancellation:"
            );

            availableSeats =
                    bookingService
                            .getAvailableSeats(show);

            for (Seat seat : availableSeats) {
                System.out.println(
                        seat.getSeatNumber()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: "
                    + e.getMessage()
            );
        }
    }
}