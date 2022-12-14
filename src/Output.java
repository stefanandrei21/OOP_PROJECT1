import java.util.ArrayList;
import java.util.List;

public class Output {
    private String error;
    private List<Movie> currentMovieList;
    private CurrentUser currentUser;



    public Output() {
        this.error = null;
        this.currentMovieList = new ArrayList<>();
        this.currentUser = null;
    }

    public Output(String error, List<Movie> currentMovieList, CurrentUser currentUser) {
        this.error = error;
        this.currentMovieList = currentMovieList;
        this.currentUser = currentUser;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(List<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "Output{" +
                "error='" + error + '\'' +
                ", currentMovieList=" + currentMovieList +
                ", currentUser=" + currentUser +
                '}';
    }
}
