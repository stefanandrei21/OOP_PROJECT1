import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private List<String> genres;
    private List<String> actors;
    private List<String> countriesBanned;


    private Integer numLikes;
    private Double rating;
    private Integer numRatings;

    public Movie() {
        this.name = null;
        this.year = null;
        this.duration = null;
        this.genres = null;
        this.actors = null;
        this.countriesBanned = null;

        this.numRatings = 0;
        this.numLikes = 0;
        this.rating = 0.00;
    }

    public Movie(String name, Integer year, Integer duration,
                 List<String> genres, List<String> countriesBanned,
                 List<String> actors) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;

    }
    public Movie(String name, Integer year, Integer duration,
                 List<String> genres, List<String> countriesBanned,
                 List<String> actors, Integer numLikes, Double rating,
                 Integer numRatings) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        this.numLikes = numLikes;
        this.rating = rating;
        this.numRatings = numRatings;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating= rating;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                ", numLikes=" + numLikes +
                ", rating=" + rating +
                ", numRatings=" + numRatings +
                '}';
    }
}
