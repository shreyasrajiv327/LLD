package moviebooking.repository;

import moviebooking.models.Movie;
import java.util.*;

public class MovieRepository {

    private final Map<Integer, Movie> movies;

    public MovieRepository() {
        movies = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getMovieId(), movie);
    }

    public Movie getMovie(int movieId) {
        return movies.get(movieId);
    }

    public boolean movieExists(int movieId) {
        return movies.containsKey(movieId);
    }
}