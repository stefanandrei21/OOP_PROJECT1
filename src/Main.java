import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.out.println(args[0]);
    InputLoader inputLoader = new InputLoader();
   // inputLoader.init("D:\\My_homework\\checker\\resources\\in\\basic_2.json");
    inputLoader.init(args[0]);
    Input input = inputLoader.readData();
    int register = 1;
    int logged_in = 0;
    Login login = new Login("loginpage");
    RegisterPage registerPage = new RegisterPage("registerPage");
    HomePage homePage = new HomePage("homePage");
    MoviesPage moviesPage = new MoviesPage("moviesPage", input.getMovieList());
    Page currentPage = homePage;
        List<Output> listToPrint = new ArrayList<Output>();
        for (Actions action : input.getActions()) {

            Output output = new Output();
            register = 1;
            if (action.getType().equals("change page")){
                //Eroare pt daca esti pe pagina de login si vrei sa schimbi pagina cu login
                if (action.getPage().equals("login") && currentPage.getTitle().equals("loginpage")) {
                    output.setError("Error");
                }
                // Eroare pt daca vrei sa schimbi pagina cu movies page si nu esti logged in
                if (action.getPage().equals("moviesPage") && logged_in == 0) {
                    output.setError("Error");
                }

                if (action.getPage().equals("login")) {
                    currentPage = login;
                } else if (action.getPage().equals("register")) {
                    currentPage = registerPage;
                } else if (action.getPage().equals("logout")) {
                    currentPage = homePage;
                    logged_in = 0;
                } else if (action.getPage().equals("movies")) {
                    currentPage = moviesPage;
                    output.setCurrentUser( login.getUserLoggedIn());
                    moviesPage.setMovieListNoCountry(login.getUserLoggedIn().getCountry());

                    output.setCurrentMovieList(moviesPage.getMovieList());
                    listToPrint.add(output);

                }

            } else if (action.getType().equals("on page")) {

                if (action.getFeature().equals("login") && currentPage.getTitle().equals("loginpage")) {
                    CurrentUser loggedUser = new CurrentUser(action.getUser());
                    login.setUserLoggedIn(loggedUser);
                    if(login.verifyCredentials(input.getUserList()) && logged_in == 0){


                        output.setCurrentUser(new CurrentUser(input.findUserByUsername(action.getUser().getName())));
                        listToPrint.add(output);

                        logged_in = 1;
                    } else {
                        output.setError("Error");
                    }
                } else if (action.getFeature().equals("register") && currentPage.getTitle().equals("RegisterPage")) {
                    CurrentUser user_to_register = new CurrentUser(action.getUser());
                    for(User user : input.getUserList()) {
                        if(user.getName().equals(user_to_register.getName())){
                            register = 0;

                            output.setError("Error");
                        }
                    }
                    if (register == 1) {
                        login.setUserLoggedIn( user_to_register);
                        input.addUser(user_to_register);
                        output.setCurrentUser(new CurrentUser(action.getUser()));
                        listToPrint.add(output);
                        logged_in = 1;

                    }
                } else if (action.getFeature().equals("search") && currentPage.getTitle().equals("moviesPage")) {
                    output.setCurrentUser(login.getUserLoggedIn());
                    List<Movie> searchedMovieList = new ArrayList<Movie>();
                    for (Movie movie : input.getMovieList()) {
                        if (movie.getName().startsWith(action.getStartsWith())) {

                            output.setCurrentMovieList(searchedMovieList);
                        }

                    }
                    listToPrint.add(output);

                } else if (action.getFeature().equals("filter") && currentPage.getTitle().equals("moviesPage")) {
                    //if (act)
                    output.setCurrentMovieList(moviesPage.getMovieList());
                    output.setCurrentUser(login.getUserLoggedIn());
                    listToPrint.add(output);
                }
            }
            if (output.getError() != null) {
                listToPrint.add(output);
            }

        }

        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> arrayToPrint = new ArrayList<Map<String, Object>>();
        int index = 0;

        for(Output out : listToPrint) {
            Map<String, Object> myObject = new LinkedHashMap<String, Object>();
            myObject.put("error", out.getError());
            myObject.put("currentMoviesList", out.getCurrentMovieList());

            JSONObject json = new JSONObject();
            json.put("error", out.getError());
            json.put("currentMoviesList", out.getCurrentMovieList());
            JSONObject newJson = new JSONObject();
            Map<String, Object> newObj = new LinkedHashMap<>();

            if(out.getCurrentUser() != null) {
                Map<String, Object> myObj3 = new LinkedHashMap<>();
                JSONObject json3 = new JSONObject();

                myObj3.put("name", out.getCurrentUser().getName());
                myObj3.put("password", out.getCurrentUser().getPassword());
                myObj3.put("accountType", out.getCurrentUser().getAccountType());
                myObj3.put("country", out.getCurrentUser().getCountry());
                myObj3.put("balance", out.getCurrentUser().getBalance().toString());


                newObj.put("credentials", myObj3);
                newObj.put("tokensCount", out.getCurrentUser().getTokensCount());
                newObj.put("numFreePremiumMovies", out.getCurrentUser().getNumFreePremiumMovies());
                newObj.put("purchasedMovies", out.getCurrentUser().getPurchasedMovies());
                newObj.put("watchedMovies", out.getCurrentUser().getWatchedMovies());
                newObj.put("likedMovies", out.getCurrentUser().getLikedMovies());
                newObj.put("ratedMovies", out.getCurrentUser().getRatedMovies());
                json.put("currentUser", newJson);
                myObject.put("currentUser", newObj);
                //json.put("text", out.getCurrentUser().getTokensCount());
            } else {
                json.put("currentUser", out.getCurrentUser());
                myObject.put("currentUser", out.getCurrentUser());
            }

            arrayToPrint.add(myObject);

            jsonArray.add(myObject);
        }

        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.enable(SerializationFeature.INDENT_OUTPUT);

        //String json = mapper2.writeValueAsString(jsonArray);

        File jsonFile = new File("D:\\My_homework\\results.out");

        mapper2.writeValue(jsonFile, jsonArray);

    }
}
