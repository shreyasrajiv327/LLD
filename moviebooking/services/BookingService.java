package moviebooking.services;

import moviebooking.models.*;
import moviebooking.repository.*;

import java.time.LocalDateTime;
import java.util.*;

public class BookingService {

    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    // showId -> (seatId -> Seat)
    private final HashMap<Integer, HashMap<Integer, Seat>> seatRecord;

    // userId -> (bookingId -> Booking)
    private final HashMap<Integer, HashMap<Integer, Booking>> userBooking;

    private int userId;
    private int movieId;
    private int showId;
    private int bookingId;

    public BookingService() {

        this.bookingRepository = new BookingRepository();
        this.movieRepository = new MovieRepository();
        this.showRepository = new ShowRepository();
        this.userRepository = new UserRepository();

        this.seatRecord = new HashMap<>();
        this.userBooking = new HashMap<>();

        this.userId = 1;
        this.movieId = 1;
        this.showId = 1;
        this.bookingId = 1;
    }

    // ==========================
    // USER APIs
    // ==========================

    public User addUser(String name) {

        User user =
                new User(
                        userId++,
                        name
                );

        userRepository.addUser(user);

        return user;
    }

    // ==========================
    // MOVIE APIs
    // ==========================

    public Movie addMovie(
            String movieName,
            int duration
    ) {

        Movie movie =
                new Movie(
                        movieId++,
                        movieName,
                        duration
                );

        movieRepository.addMovie(movie);

        return movie;
    }

    // ==========================
    // SHOW APIs
    // ==========================

    public Show createShow(
            Movie movie,
            LocalDateTime startTime,
            int totalSeats
    ) {

        Show show =
                new Show(
                        showId,
                        movie,
                        startTime,
                        totalSeats
                );

        showRepository.addShow(show);

        seatRecord.put(
                showId,
                new HashMap<>()
        );

        for (int i = 1; i <= totalSeats; i++) {

            Seat seat =
                    new Seat(
                            i,
                            "S" + i
                    );

            seatRecord
                    .get(showId)
                    .put(i, seat);
        }

        showId++;

        return show;
    }

    // ==========================
    // BOOKING APIs
    // ==========================

    public synchronized Booking bookSeats(
            User user,
            Show show,
            List<Integer> seatNumbers
    ) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "User cannot be null"
            );
        }

        if (show == null) {
            throw new IllegalArgumentException(
                    "Show cannot be null"
            );
        }

        synchronized (show) {

            HashMap<Integer, Seat> seatsForShow =
                    seatRecord.get(
                            show.getShowId()
                    );

            if (seatsForShow == null) {
                throw new IllegalArgumentException(
                        "Show does not exist"
                );
            }

            // Validate all seats

            for (int seatNo : seatNumbers) {

                Seat seat =
                        seatsForShow.get(
                                seatNo
                        );

                if (seat == null) {
                    throw new IllegalArgumentException(
                            "Seat " + seatNo +
                            " does not exist"
                    );
                }

                if (!seat.isAvailable()) {

                    throw new RuntimeException(
                            "Seat "
                            + seatNo
                            + " already booked"
                    );
                }
            }

            List<Seat> bookedSeats =
                    new ArrayList<>();

            for (int seatNo : seatNumbers) {

                Seat seat =
                        seatsForShow.get(
                                seatNo
                        );

                seat.bookSeat();

                bookedSeats.add(seat);
            }

            Booking booking =
                    new Booking(
                            bookingId,
                            user,
                            show,
                            bookedSeats
                    );

            bookingRepository.addBooking(
                    booking
            );

            userBooking
                    .computeIfAbsent(
                            user.getUserId(),
                            k -> new HashMap<>()
                    )
                    .put(
                            bookingId,
                            booking
                    );

            bookingId++;

            return booking;
        }
    }

    // ==========================
    // CANCEL BOOKING
    // ==========================

    public void cancelBooking(
            int bookingId
    ) {

        Booking booking =
                bookingRepository.getBooking(
                        bookingId
                );

        if (booking == null) {
            throw new IllegalArgumentException(
                    "Invalid booking id"
            );
        }

        Show show =
                booking.getShow();

        synchronized (show) {

            List<Seat> bookedSeats =
                    booking.getSeats();

            for (Seat seat : bookedSeats) {
                seat.freeSeat();
            }

            bookingRepository.removeBooking(
                    bookingId
            );

            HashMap<Integer, Booking> bookings =
                    userBooking.get(
                            booking.getUser()
                                   .getUserId()
                    );

            if (bookings != null) {
                bookings.remove(
                        bookingId
                );
            }
        }
    }

    // ==========================
    // QUERY APIs
    // ==========================

    public List<Seat> getAvailableSeats(
            Show show
    ) {

        List<Seat> availableSeats =
                new ArrayList<>();

        HashMap<Integer, Seat> seats =
                seatRecord.get(
                        show.getShowId()
                );

        if (seats == null) {
            return availableSeats;
        }

        for (Seat seat : seats.values()) {

            if (seat.isAvailable()) {
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

    public List<Booking> getUserBookings(
            int userId
    ) {

        if (!userBooking.containsKey(
                userId
        )) {

            return new ArrayList<>();
        }

        return new ArrayList<>(
                userBooking
                        .get(userId)
                        .values()
        );
    }
}