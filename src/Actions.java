import org.json.simple.JSONArray;

import java.util.List;

public class Actions {
    private String type;
    private String page;
    private String feature;
    private User user;

    private String startsWith;

    private String rating;

    private String duration;

    private List<String> actors;
    private List<String> genre;

    public Actions() {
        this.type = null;
        this.page = null;
        this.feature = null;
        this.user = null;
        this.startsWith = null;
        this.rating = null;
        this.duration = null;
        this.actors = null;
        this.genre = null;
    }

    public Actions(String type, String page, String feature, User user, String startsWith,
                   String rating, String duration, List<String> actors, List<String> genre) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.user = user;
        this.startsWith = startsWith;
        this.rating = rating;
        this.duration = duration;
        this.actors = actors;
        this.genre = genre;
    }

    public Actions(String type, String page, String feature, User user,
                   String duration, String rating, List<String> actors, List<String> genre) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.user = user;
        this.duration = duration;
        this.rating = rating;
        this.actors = actors;
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", user=" + user +
                ", startsWith='" + startsWith + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", actors=" + actors +
                ", genre=" + genre +
                '}';
    }
}
