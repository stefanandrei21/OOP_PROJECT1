import java.util.List;

public class Input {
    private List<User> userList;
    private List<Movie> movieList;

    private List<Actions> actions;

    public void init(List<User>userList, List<Movie> movieList, List<Actions> actions) {
        this.userList = userList;
        this.movieList = movieList;
        this.actions = actions;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public void setActions(List<Actions> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Input{" +
                "userList=" + userList +
                ", movieList=" + movieList +
                ", actions=" + actions +
                '}';
    }
}