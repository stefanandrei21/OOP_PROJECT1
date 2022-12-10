import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private List<String> genres;

    private List<String> countriesBanned;
    private List<String> actors;

    public Movie() {
        this.name = null;
        this.year = null;
        this.duration = null;
        this.genres = null;
        this.countriesBanned = null;
        this.actors = null;
    }

    public Movie(String name, Integer year, Integer duration, List<String> genres, List<String> countriesBanned, List<String> actors) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.countriesBanned = countriesBanned;
        this.actors = actors;
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
                ", countriesBanned=" + countriesBanned +
                ", actors=" + actors +
                '}';
    }
}
