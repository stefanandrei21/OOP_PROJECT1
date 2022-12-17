package DataBase;

import java.util.List;

public final class Input {
    private List<CurrentUser> userList;
    private List<Movie> movieList;

    private List<Actions> actions;

    public void init(final List<CurrentUser> myUserList, final List<Movie> myMovieList, final List<Actions> myActions) {
        this.userList = myUserList;
        this.movieList = myMovieList;
        this.actions = myActions;
    }

    /**
     *
     * @param name
     * @return
     */
    public CurrentUser findUserByUsername(final String name) {
        for (CurrentUser user : userList) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
    public Movie findMovieByUsername(final String name) {
        for (Movie mov : this.movieList) {
            if (mov.getName().equals(name)) {
                return mov;
            }
        }
        return null;
    }

    /**
     *
     * @param user
     */
    public void updateUserInDatabase(final CurrentUser user) {
        if (userList.contains(user)) {
            int index = userList.indexOf(user);
            userList.remove(index);
            CurrentUser userUpdate = new CurrentUser(user, user.getTokensCount(),
                        user.getNumFreePremiumMovies(), user.getPurchasedMovies(),
                        user.getWatchedMovies(), user.getLikedMovies(), user.getRatedMovies());

            userList.add(userUpdate);
        }
    }

    /**
     *
     * @param user
     */
    public void addUser(final CurrentUser user) {
        userList.add(user);
    }

    public List<CurrentUser> getUserList() {
        return userList;
    }

    public void setUserList(final List<CurrentUser> userList) {
        this.userList = userList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(final List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public void setActions(final List<Actions> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Database.Input{"
                + "userList=" + userList
                + ", currentMovieList=" + movieList
                + ", actions=" + actions
                + '}';
    }
}
