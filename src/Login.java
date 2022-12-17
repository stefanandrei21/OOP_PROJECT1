import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Login extends Page{
    private CurrentUser userLoggedIn;
    private List<Movie> loggedInMovieList;
    public Login(CurrentUser userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }
    public Login(String title) {
        super(title);
        this.userLoggedIn = null;
    }

    public Login(String title, CurrentUser userLoggedIn) {
        super(title);
        this.userLoggedIn = userLoggedIn;
    }
    public void sortByDuration(String duration) {
        Collections.sort(this.loggedInMovieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (duration.equals("increasing")) {
                    return Integer.compare(o1.getDuration(), o2.getDuration());
                } else if (duration.equals("decreasing")) {
                    return Integer.compare(o2.getDuration(), o1.getDuration());
                }
                return 0;
            }
        });
    }

    public List<Movie> getLoggedInMovieList() {
        return loggedInMovieList;
    }

    public void setLoggedInMovieList(List<Movie> loggedInMovieList) {
        this.loggedInMovieList = loggedInMovieList;
    }

    public CurrentUser getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(CurrentUser userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public boolean verifyCredentials(List<User> userList){

       for(User user : userList) {
           if(this.userLoggedIn.getName().equals(user.getName()) && this.getUserLoggedIn().getPassword().equals(user.getPassword())) {
               return true;
           }
       }
       return false;
    }
}
