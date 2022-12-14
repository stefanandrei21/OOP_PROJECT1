import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoviesPage extends Page{
    private List<Movie> movieListForPage = new ArrayList<Movie>();

    public MoviesPage(List<Movie> movieList) {
        this.movieListForPage = movieList;
    }

    public MoviesPage(String title, List<Movie> movieList) {
        super(title);
        this.movieListForPage = movieList;
    }

    public List<Movie> getMovieList() {
        return movieListForPage;
    }
    public void setMovieListNoCountry(String country) {
        List<Movie> movieListNoBannedCountry = new ArrayList<Movie>();
        for (Movie movie : movieListForPage) {
            for(String banned : movie.getCountriesBanned()) {

                if(!banned.equals(country)){
                    movieListNoBannedCountry.add(movie);
                }
            }

        }
        movieListForPage = movieListNoBannedCountry;

    }

    public void setMovieList(List<Movie> movieList) {
        this.movieListForPage = movieList;
    }

    public void sortByDuration(String duration, String rating) {
        if (duration.equals("deacresing")) {
            Collections.sort(this.movieListForPage, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getDuration() - o2.getDuration();
                }
            });
        } else if (duration.equals("increasing")) {
            Collections.sort(this.movieListForPage, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getDuration() - o1.getDuration();
                }
            });
        }
    }

    @Override
    public String toString() {
        return "MoviesPage{" +
                "movieList=" + movieListForPage +
                '}';
    }
}
