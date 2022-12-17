package Pages;
import DataBase.Movie;
import java.util.ArrayList;
import java.util.List;

public final class MoviesPage extends Page{
    private List<Movie> movieListForPage = new ArrayList<Movie>();

    public MoviesPage(final List<Movie> movieList) {
        this.movieListForPage = movieList;
    }

    public MoviesPage(final String title, final List<Movie> movieList) {
        super(title);
        this.movieListForPage = movieList;
    }

    public List<Movie> getMovieList() {
        return movieListForPage;
    }
    public List<Movie> getMovieListNoCountry(final String country) {
        List<Movie> movieListNoBannedCountry = new ArrayList<Movie>();
        for (Movie movie : movieListForPage) {
            boolean ml = false;
            for (String banned : movie.getCountriesBanned()) {
                if (banned.equals(country)){
//                    movieListNoBannedCountry.add(movie);
                    ml = true;
                }
            }
            if(ml == false) {
                movieListNoBannedCountry.add(movie);
            }
        }
        return  movieListNoBannedCountry;

    }

    public Movie getMovieByName(String name) {
        for (Movie mv : this.movieListForPage) {
            if (mv.getName().equals(name)) {
               return mv;
            }
        }
        return null;
    }

    public void setMovieList(final List<Movie> movieList) {
        this.movieListForPage = movieList;
    }


    @Override
    public String toString() {
        return "Pages.MoviesPage{"
                + "movieList=" + movieListForPage
                + '}';
    }
}
