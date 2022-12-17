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
    public List<Movie> getMovieListNoCountry(String country) {
        List<Movie> movieListNoBannedCountry = new ArrayList<Movie>();
        for (Movie movie : movieListForPage) {
            for(String banned : movie.getCountriesBanned()) {
                //System.out.println(banned + " si " + country);
                if(!banned.equals(country)){

                    movieListNoBannedCountry.add(movie);
                }
            }

        }
        return  movieListNoBannedCountry;

    }

    public void setMovieList(List<Movie> movieList) {
        this.movieListForPage = movieList;
    }



    public void checkActor(String actor) {}
    @Override
    public String toString() {
        return "MoviesPage{" +
                "movieList=" + movieListForPage +
                '}';
    }
}
