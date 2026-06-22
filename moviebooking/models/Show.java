package moviebooking.models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Show {

    private int showId;
    private Movie movie;
    private LocalDateTime startTime;

    private int seats;

    public Show(int showId,
                Movie movie,
                LocalDateTime startTime,
                int seats) {

        this.showId = showId;
        this.movie = movie;
        this.startTime = startTime;
        this.seats = seats;
    }

    public int getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getSeats() {
        return seats;
    }
}