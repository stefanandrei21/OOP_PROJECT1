package Pages;
import DataBase.CurrentUser;
import DataBase.Movie;
import DataBase.User;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Login extends Page {
    private CurrentUser userLoggedIn;
    private List<Movie> loggedInMovieList;

    public Login(final CurrentUser userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }
    public Login(final String title) {
        super(title);
        this.userLoggedIn = null;
    }

    public Login(final String title, final CurrentUser userLoggedIn) {
        super(title);
        this.userLoggedIn = userLoggedIn;
    }

    /**
     * Sorteaza filmele in caz ca actiunea este filter
     * override compare
     * @param duration
     * @param rating
     */
    public void sortByDuration(final String duration, final String rating) {
        Collections.sort(this.loggedInMovieList, new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                if (duration != null) {
                    if (duration.equals("increasing")) {
                        if (o1.getDuration() != o2.getDuration()) {
                            return Integer.compare(o1.getDuration(), o2.getDuration());
                        } else {
                            if (rating.equals("increasing")) {
                                return Double.compare(o1.getRating(), o2.getRating());
                            } else if (rating.equals("decreasing")) {
                                return Double.compare(o2.getRating(), o1.getRating());
                            }
                        }
                    } else if (duration.equals("decreasing")) {
                        if (o1.getDuration() != o2.getDuration()) {
                            return Integer.compare(o2.getDuration(), o1.getDuration());
                        } else {
                            if (rating.equals("increasing")) {
                                return Double.compare(o1.getRating(), o2.getRating());
                            } else if (rating.equals("decreasing")) {
                                return Double.compare(o2.getRating(), o1.getRating());
                            }
                        }
                    }
                } else if (duration == null && rating != null) {
                    if (rating.equals("increasing")) {
                        return Double.compare(o1.getRating(), o2.getRating());
                    } else if (rating.equals("decreasing")) {
                        return Double.compare(o2.getRating(), o1.getRating());
                    }
                }
                return 0;
            }
        });
    }

    /**
     * verifica daca un film este cumparat si il returneaza
     * @param movie
     * @return
     */
    public Movie checkIfMovieIsInPurchased(final String movie) {
        for (Movie goMovie : this.userLoggedIn.getPurchasedMovies()) {
            if (goMovie.getName().equals(movie)) {
               return goMovie;
            }
        }

        return null;
    }

    /**
     * verifica daca un film este vazut
     * @param movie
     * @return
     */
    public Movie ifWatched(final String movie) {
        for (Movie goMovie : this.userLoggedIn.getWatchedMovies()) {
            if (goMovie.getName().equals(movie)) {
                return goMovie;
            }
        }

        return null;
    }

    /**
     * getter si setter pt MovieList
     * @return
     */
    public List<Movie> getLoggedInMovieList() {
        return loggedInMovieList;
    }

    public void setLoggedInMovieList(final List<Movie> loggedInMovieList) {
        this.loggedInMovieList = loggedInMovieList;
    }

    /**
     * getter si setter pt userul logat
     * @return
     */
    public CurrentUser getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(final CurrentUser userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    /**
     * verific credentialele si daca este
     * in lista mea de utilizatori return true
     * @param userList
     * @return
     */
    public boolean verifyCredentials(final List<CurrentUser> userList) {
       for (User user : userList) {
           if (this.userLoggedIn.getName().equals(user.getName())
                   && this.getUserLoggedIn().getPassword().equals(
                           user.getPassword())) {
               return true;
           }
       }
       return false;
    }
}
