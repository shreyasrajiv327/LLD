package moviebooking.repository;

import moviebooking.models.Show;
import java.util.*;

public class ShowRepository {

    private final Map<Integer, Show> shows;

    public ShowRepository() {
        shows = new HashMap<>();
    }

    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
    }

    public Show getShow(int showId) {
        return shows.get(showId);
    }

    public boolean showExists(int showId) {
        return shows.containsKey(showId);
    }
}