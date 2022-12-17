import DataBase.*;
import Pages.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramWorkflow {
    private Input input;

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public List<Output>  work() {
        int register = 1;
        int logged_in = 0;
        Login login = new Login("loginpage");
        RegisterPage registerPage = new RegisterPage("registerPage");
        HomePage homePage = new HomePage("homePage");
        MoviesPage moviesPage = new MoviesPage("moviesPage", input.getMovieList());
        UpgradesPage upgradesPage = new UpgradesPage("UpgradesPage");
        SeeDetailsPage seeDetailsPage = new SeeDetailsPage("seeDetailsPage");
        Page currentPage = homePage;


        List<Output> listToPrint = new ArrayList<Output>();
        for (Actions action : input.getActions()) {

            Output output = new Output();
            List<Movie> purchasedMovieList;
            register = 1;
            if (action.getType().equals("change page")) {
                //Eroare pt daca esti pe pagina de login si vrei sa schimbi pagina cu login
                if (action.getPage().equals("login") && logged_in == 1) {
                    output.setError("Error");
                } else if (action.getPage().equals("logout") && logged_in == 0) {
                    output.setError("Error");
                }
                else if (action.getPage().equals("login") && logged_in == 0) {
                    currentPage = login;
                } else if (action.getPage().equals("register")) {
                    currentPage = registerPage;
                } else if (action.getPage().equals("logout")) {
                    login.setUserLoggedIn(null);
                    currentPage = homePage;

                    logged_in = 0;
                } else if (action.getPage().equals("movies") && logged_in == 1) {
                    currentPage = moviesPage;
                    login.setLoggedInMovieList(moviesPage.getMovieListNoCountry(login.getUserLoggedIn().getCountry()));



                    output.setCurrentMovieList(deepCopy(login.getLoggedInMovieList()));

                    output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                    listToPrint.add(output);

                } else if (action.getPage().equals("see details")) {
                    boolean found_movie = false;
                    Movie seeDetailsMovie = new Movie();
                    for (Movie movie : login.getLoggedInMovieList()) {
                        if (action.getMovie().equals(movie.getName())) {
                            found_movie = true;
                            seeDetailsMovie = new Movie(movie);
                        }
                    }

                    if (!found_movie) {
                        output.setError("Error");

                    } else {
                        currentPage = seeDetailsPage;
                        seeDetailsPage.setSeeDetailsToMovie(seeDetailsMovie);
                        List<Movie> helpMovie = new ArrayList<Movie>();
                        helpMovie.add(seeDetailsMovie);
                        List<Movie> createCopy = new ArrayList<Movie>();
                        for (Movie inML : helpMovie) {
                            createCopy.add(new Movie(inML));
                        }
                        output.setCurrentMovieList(createCopy);


                        purchasedMovieList = new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies()));
                        output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                                purchasedMovieList, new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                        listToPrint.add(output);
                    }
                } else if (action.getPage().equals("upgrades") && logged_in == 1) {
                    currentPage = upgradesPage;

                } else {
                    output.setError("Error");
                }
            } else if (action.getType().equals("on page")) {
                if(action.getFeature().equals("login") && !currentPage.getTitle().equals("loginpage")) {
                    output.setError("Error");
                } else
                if (action.getFeature().equals("login") && currentPage.getTitle().equals("loginpage")) {
                    CurrentUser loggedUser = new CurrentUser(action.getUser(), 0, 15, null, null, null, null);
                    if(logged_in == 0) {
                        login.setUserLoggedIn(loggedUser);
                    }

                    if(login.verifyCredentials(input.getUserList()) && logged_in == 0){
                        CurrentUser userToWork = input.findUserByUsername(loggedUser.getName());

                        CurrentUser loginUser = new CurrentUser(userToWork, userToWork.getTokensCount(), userToWork.getNumFreePremiumMovies() ,userToWork.getPurchasedMovies(), userToWork.getWatchedMovies(),userToWork.getLikedMovies(),userToWork.getRatedMovies());

                        login.setUserLoggedIn(loginUser);

                        purchasedMovieList = new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies()));
                        output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                                purchasedMovieList, new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                        listToPrint.add(output);

                        logged_in = 1;
                    } else {
                        output.setError("Error");
                    }
                } else if (action.getFeature().equals("register") && currentPage.getTitle().equals("Pages.RegisterPage")) {
                    CurrentUser user_to_register = new CurrentUser(action.getUser());

                    for(User user : input.getUserList()) {
                        if(user.getName().equals(user_to_register.getName())){
                            register = 0;

                            output.setError("Error");
                        }
                    }
                    if (register == 1) {
                        currentPage = login;
                        login.setUserLoggedIn( user_to_register);

                        input.addUser(user_to_register);

                        purchasedMovieList = new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies()));
                        output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                                purchasedMovieList, new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));

                        listToPrint.add(output);
                       currentPage = login;
                        logged_in = 1;

                    }
                } else if (action.getFeature().equals("search") && currentPage.getTitle().equals("moviesPage")) {
                    purchasedMovieList = new ArrayList<>();
                    if (login.getUserLoggedIn().getPurchasedMovies() != null) {
                        purchasedMovieList = new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies()));
                    }

                    output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                            purchasedMovieList, new ArrayList<>(login.getUserLoggedIn().getWatchedMovies()),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                    List<Movie> searchedMovieList = new ArrayList<Movie>();
                    for (Movie movie : login.getLoggedInMovieList()) {
                        if (movie.getName().startsWith(action.getStartsWith())) {
                            searchedMovieList.add(movie);

                        }

                    }
                    List<Movie> createCopy = new ArrayList<Movie>();
                    for (Movie inML : searchedMovieList) {
                        createCopy.add(new Movie(inML));
                    }
                    output.setCurrentMovieList(createCopy);

                    listToPrint.add(output);


                } else if (action.getFeature().equals("filter") && currentPage.getTitle().equals("moviesPage")) {
                    List<Movie> movieListContains = new ArrayList<Movie>();
                    if (action.getActors() != null) {

                        for (Movie movie : moviesPage.getMovieListNoCountry(login.getUserLoggedIn().getCountry())) {
                            if (movie.containsActors(action.getActors())) {
                                movieListContains.add(movie);
                            }
                        }
                        login.setLoggedInMovieList(movieListContains);
                    }
                    List<Movie> movieListContains2 = new ArrayList<Movie>();

                    if (action.getGenre() != null) {

                        for (Movie movie : login.getLoggedInMovieList()) {
                            if (movie.containsGenre(action.getGenre())) {
                                movieListContains2.add(movie);
                            }
                        }
                        login.setLoggedInMovieList(movieListContains2);
                    }
                    login.sortByDuration(action.getDuration(),action.getRating());
//                    if (action.getDuration().equals("decreasing")) {
//
//                        login.sortByDuration("decreasing");
//
//
//                    } else if (action.getDuration().equals("increasing")) {
//                        login.sortByDuration("increasing");
//                    }
//                    if (action.getRating().equals("decreasing")) {
//
//                        login.sortByDuration("decreasing");
//
//
//                    } else if (action.getDuration().equals("increasing")) {
//                        login.sortByDuration("increasing");
//                    }

                    List<Movie> createCopy = new ArrayList<Movie>();
                    for (Movie inML : login.getLoggedInMovieList()) {
                        createCopy.add(new Movie(inML));
                    }
                    output.setCurrentMovieList(createCopy);

                    output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                    listToPrint.add(output);
                } else if (action.getFeature().equals("filter") && !currentPage.getTitle().equals("moviesPage")) {
                    output.setError("Error");
                }else if (action.getFeature().equals("buy tokens") && currentPage.getTitle().equals("UpgradesPage")) {
                    if (action.getCount() < login.getUserLoggedIn().getBalance()) {
                        Integer tokens_count = action.getCount();
                        Integer balance = login.getUserLoggedIn().getBalance();
                        login.getUserLoggedIn().setBalance(login.getUserLoggedIn().getBalance() - action.getCount());
                        login.getUserLoggedIn().setTokensCount(action.getCount());
                        input.findUserByUsername(login.getUserLoggedIn().getName()).setTokensCount(tokens_count);
                        input.findUserByUsername(login.getUserLoggedIn().getName()).setBalance(balance - tokens_count);

                    }

                } else if (action.getFeature().equals("buy premium account") && currentPage.getTitle().equals("UpgradesPage")) {
                    if (login.getUserLoggedIn().getTokensCount() >= 10) {
                        login.getUserLoggedIn().setTokensCount(login.getUserLoggedIn().getTokensCount() - 10);
                        login.getUserLoggedIn().setAccountType("premium");
                        input.findUserByUsername(login.getUserLoggedIn().getName()).setAccountType("premium");
                    }
                } else if (action.getFeature().equals("purchase") && currentPage.getTitle().equals("seeDetailsPage")) {

                    boolean buyMovie = false;
                    if (login.getUserLoggedIn().getAccountType().equals("standard")) {
                        if (login.getUserLoggedIn().getTokensCount() >= 2) {
                            login.getUserLoggedIn().setTokensCount(login.getUserLoggedIn().getTokensCount() - 2);
                            buyMovie = true;
                        }
                    } else if (login.getUserLoggedIn().getAccountType().equals("premium")) {
                        if (login.getUserLoggedIn().getNumFreePremiumMovies() > 0) {
                            login.getUserLoggedIn().setNumFreePremiumMovies(login.getUserLoggedIn().getNumFreePremiumMovies() - 1);
                            buyMovie = true;
                        } else {
                            if (login.getUserLoggedIn().getTokensCount() >= 2) {
                                login.getUserLoggedIn().setTokensCount(login.getUserLoggedIn().getTokensCount() - 2);
                                buyMovie = true;
                            }
                        }
                    }
                    if (buyMovie) {
                        login.getUserLoggedIn().getPurchasedMovies().add(seeDetailsPage.getSeeDetailsToMovie());
                    }

                    input.findUserByUsername(login.getUserLoggedIn().getName()).setNumFreePremiumMovies(login.getUserLoggedIn().getNumFreePremiumMovies());
                    input.findUserByUsername(login.getUserLoggedIn().getName()).setTokensCount(login.getUserLoggedIn().getTokensCount());
                    List<Movie> setMovToL = new ArrayList<Movie>();
                    setMovToL.add(seeDetailsPage.getSeeDetailsToMovie());
                    List<Movie> createCopy = new ArrayList<Movie>();
                    for (Movie inML : setMovToL) {
                        createCopy.add(new Movie(inML));
                    }
                    output.setCurrentMovieList(createCopy);
                    List<Movie> createCopyNum = new ArrayList<Movie>();
//                    for (Movie inML : login.getUserLoggedIn().ge) {
//                        createCopyNum.add(new Movie(inML));
//                    }
                    output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                            new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                    listToPrint.add(output);
//                   // input.findUserByUsername(login.getUserLoggedIn().getName()).getPurchasedMovies().add(seeDetailsPage.getSeeDetailsToMovie());

                } else if (action.getFeature().equals("watch") && currentPage.getTitle().equals("seeDetailsPage")) {
                    if (login.checkIfMovieIsInPurchased(seeDetailsPage.getSeeDetailsToMovie().getName()) != null) {
                        login.getUserLoggedIn().getWatchedMovies().add(login.checkIfMovieIsInPurchased(seeDetailsPage.getSeeDetailsToMovie().getName()));
                        input.findUserByUsername(login.getUserLoggedIn().getName()).setWatchedMovies(login.getUserLoggedIn().getWatchedMovies());
                        output.setCurrentMovieList(deepCopy(login.getUserLoggedIn().getWatchedMovies()));
                        output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),  new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                        listToPrint.add(output);
                    } else {
                        output.setError("Error");
                    }
                    // verific daca e in lista mea de cumparate
                    // daca e adaug la logincurrentuser movies watched
                    // dupa ce am adaugat la login user setez si in input
                } else if (action.getFeature().equals("like") && currentPage.getTitle().equals("seeDetailsPage")) {
                    Movie likeMovie = login.checkIfMovieIsInPurchased(seeDetailsPage.getSeeDetailsToMovie().getName());
                    if (likeMovie != null) {
                        likeMovie.setNumLikes(likeMovie.getNumLikes() + 1);
                        login.getUserLoggedIn().getLikedMovies().add(likeMovie);
                        input.findUserByUsername(login.getUserLoggedIn().getName()).setLikedMovies(login.getUserLoggedIn().getLikedMovies());
                        input.findMovieByUsername(likeMovie.getName()).setNumLikes(likeMovie.getNumLikes());
                        List<Movie> setMovToL = new ArrayList<Movie>();
                        setMovToL.add(likeMovie);
                        List<Movie> createCopy = new ArrayList<Movie>();
                        for (Movie inML : setMovToL) {
                            createCopy.add(new Movie(inML));
                        }
                        output.setCurrentMovieList(createCopy);
                        output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                        listToPrint.add(output);
                    } else {
                        output.setError("Error");
                    }

                } else if (action.getFeature().equals("rate") && currentPage.getTitle().equals("seeDetailsPage")) {
                    Movie rateMovie = login.checkIfMovieIsInPurchased(seeDetailsPage.getSeeDetailsToMovie().getName());
                    if (rateMovie != null) {
                        login.getUserLoggedIn().getRatedMovies().add(rateMovie);
                        rateMovie.setNumRatings(rateMovie.getNumRatings() + 1);
                        rateMovie.setRating((rateMovie.getRating() + action.getRate()) / rateMovie.getNumRatings());

                        input.findUserByUsername(login.getUserLoggedIn().getName()).setRatedMovies(login.getUserLoggedIn().getRatedMovies());
                        input.findMovieByUsername(rateMovie.getName()).setNumRatings(rateMovie.getNumRatings());
                        input.findMovieByUsername(rateMovie.getName()).setRating(rateMovie.getRating());
                        List<Movie> ratedM = new ArrayList<Movie>();
                        ratedM.add(seeDetailsPage.getSeeDetailsToMovie());

                        output.setCurrentMovieList(deepCopy(ratedM));
                        output.setCurrentUser(new CurrentUser(login.getUserLoggedIn(), login.getUserLoggedIn().getTokensCount(), login.getUserLoggedIn().getNumFreePremiumMovies(),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getPurchasedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getWatchedMovies())), new ArrayList<>(deepCopy(login.getUserLoggedIn().getLikedMovies())),
                                new ArrayList<>(deepCopy(login.getUserLoggedIn().getRatedMovies()))));
                        listToPrint.add(output);
                    } else {
                        output.setError("Error");
                    }

                } else {
                    output.setError("Error");
                }
            }


            if (output.getError() != null) {
                listToPrint.add(output);
            }

        }
        return listToPrint;
    }
    public static List<Movie> deepCopy(List<Movie> movies) {
        if(movies != null) {
            List<Movie> copiedMovies = new ArrayList<>();
            for (Movie movie : movies) {
                copiedMovies.add(new Movie(movie));
            }
            return copiedMovies;
        }
        return new ArrayList<>();
    }
}
