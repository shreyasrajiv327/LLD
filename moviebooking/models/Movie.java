package moviebooking.models;

public class Movie {

    private int movieId;
    private String movieName;
    private int duration;

    public Movie(int movieId,
                 String movieName,
                 int duration) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getDuration() {
        return duration;
    }
}