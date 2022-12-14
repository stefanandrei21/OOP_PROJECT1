import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {
    private String inputPath;

    public InputLoader() {

    }

    public void init(String inputPathGiven){
        this.inputPath = inputPathGiven;
    }

    public Input readData(){
        JSONParser jsonParser = new JSONParser();
        List<User> userList = null;
        List<Movie> movieList = null;
        List<Actions> actions = null;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new
                    FileReader(inputPath));
            JSONArray jsonUser = (JSONArray) jsonObject.get("users");
            JSONArray jsonMovie = (JSONArray) jsonObject.get("movies");
            JSONArray jsonAction = (JSONArray) jsonObject.get("actions");
            userList = readUser(jsonUser);
            movieList = readMovie(jsonMovie);
            actions = readActions(jsonAction);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Input input = new Input();
        input.init(userList, movieList, actions);
        return input;
    }

    public List<User> readUser(JSONArray jsonUserList) {
        List<User> userList = new ArrayList<User>();
        if(jsonUserList != null){
            for(Object jsonUser : jsonUserList) {
                JSONObject jsonCredentials = ((JSONObject) ((JSONObject) jsonUser).get("credentials"));
                String name = (String) jsonCredentials.get("name");
                String password = (String) jsonCredentials.get("password");
                String accountType = (String) jsonCredentials.get("accountType");
                String country = (String) jsonCredentials.get("country");
                String balance_aux = (String)(jsonCredentials.get("balance"));
                Integer balance = Integer.parseInt(balance_aux);
                userList.add(new User(name, password,accountType,country,balance));
            }
        }
        return userList;
    }

    public List<Movie> readMovie(JSONArray jsonMovieList) {
        List<Movie> movieList = new ArrayList<Movie>();
        if (jsonMovieList != null) {
            for (Object jsonMovie : jsonMovieList) {
                List<String> genres = new ArrayList<>();
                JSONArray jsonGenres = (JSONArray) ((JSONObject) jsonMovie).get("genres");
                for (Object jsonGenre : jsonGenres) {
                    genres.add(jsonGenre.toString());
                }
                List<String> actors = new ArrayList<>();
                JSONArray jsonActors = (JSONArray) ((JSONObject) jsonMovie).get("actors");
                for (Object jsonActor : jsonActors) {
                    actors.add(jsonActor.toString());
                }
                List<String> countriesBanned = new ArrayList<>();
                JSONArray jsonCountriesBanned = (JSONArray) ((JSONObject)jsonMovie).get("countriesBanned");
                for (Object jsonCountrie : jsonCountriesBanned) {
                    countriesBanned.add(jsonCountrie.toString());
                }
                String name = (String)((JSONObject) jsonMovie).get("name");
                Integer year = ((Long) ((JSONObject) jsonMovie).get("year")).intValue();
                Integer duration = ((Long) ((JSONObject) jsonMovie).get("duration")).intValue();
                movieList.add(new Movie(name, year, duration, genres, countriesBanned, actors , 0, 0.00, 0));
            }

        }

        return movieList;
    }

    public List<Actions> readActions(JSONArray jsonActionsList) {
        List<Actions> actionsList = new ArrayList<Actions>();
        if (jsonActionsList != null) {
            for (Object jsonAction : jsonActionsList) {
                String type = (String) ((JSONObject) jsonAction).get("type");
                String page = (String) ((JSONObject) jsonAction).get("page");
                String feature = (String) ((JSONObject) jsonAction).get("feature");
                String  startsWith = (String) ((JSONObject) jsonAction).get("startsWith");
                JSONObject jsonCredentials = ((JSONObject) ((JSONObject) jsonAction).get("credentials"));
                String name = null;
                String password = null;
                String country = null;
                String accountType = null;
                Integer balance = null;
                User userFromActions = new User();
                if(jsonCredentials != null) {
                    name = (String) jsonCredentials.get("name");
                    password = (String) jsonCredentials.get("password");
                    country = (String) jsonCredentials.get("country");
                    accountType = (String) jsonCredentials.get("accountType");

                    if((String)(jsonCredentials.get("balance")) != null) {
                        String balance_aux = (String) (jsonCredentials.get("balance"));
                        balance = Integer.parseInt(balance_aux);;
                    }
                    userFromActions.setBalance(balance);
                    userFromActions.setCountry(country);
                    userFromActions.setName(name);
                    userFromActions.setPassword(password);
                    userFromActions.setAccountType(accountType);
                }
                String rating = null;
                String duration = null;
                List<String> actors = new ArrayList<>();
                List<String> genre = new ArrayList<>();
                JSONObject jsonFilter = ((JSONObject) ((JSONObject) jsonAction).get("filters"));
                if (jsonFilter != null) {
                   JSONObject jsonSort =((JSONObject) jsonFilter.get("sort"));
                   if (jsonSort != null) {
                       rating = (String) jsonSort.get("rating");
                       duration = (String) jsonSort.get("duration");
                   }
                    JSONObject jsonContains = (JSONObject) jsonFilter.get("contains");
                    if (jsonContains != null) {
                        JSONArray jsonArrayActors = (JSONArray) (jsonContains).get("actors");

                        for (Object jsonActor : jsonArrayActors) {
                            actors.add(jsonActor.toString());
                        }
                        JSONArray jsonArrayGenre = (JSONArray) jsonContains.get("genre");

                        for (Object jsonGenre : jsonArrayGenre) {
                            genre.add(jsonGenre.toString());
                        }
                    }
                }
                actionsList.add(new Actions(type, page, feature, userFromActions, startsWith, duration, rating, actors, genre));
            }
        }



        return actionsList;
    }

}
