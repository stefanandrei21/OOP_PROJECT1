package DataBase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    private String inputPath;

    public InputLoader() {

    }

    /**
     *
     * @param inputPathGiven
     */
    public void init(final String inputPathGiven) {
        this.inputPath = inputPathGiven;
    }

    /**
     *
     * @return
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        List<CurrentUser> userList = null;
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

    /**
     *
     * @param jsonUserList
     * @return
     */
    public List<CurrentUser> readUser(final JSONArray jsonUserList) {
        List<CurrentUser> userList = new ArrayList<CurrentUser>();
        if (jsonUserList != null) {
            for (Object jsonUser : jsonUserList) {
                JSONObject jsonCredentials = ((JSONObject)
                        ((JSONObject) jsonUser).get("credentials"));
                String name = (String) jsonCredentials.get("name");
                String password = (String) jsonCredentials.get("password");
                String accountType = (String) jsonCredentials.get("accountType");
                String country = (String) jsonCredentials.get("country");
                String balanceAux = (String) (jsonCredentials.get("balance"));
                Integer balance = Integer.parseInt(balanceAux);
                User userAux = new User(name, password, accountType, country, balance);
                userList.add(new CurrentUser(userAux));
            }
        }
        return userList;
    }

    /**
     *
     * @param jsonMovieList
     * @return
     */

    public List<Movie> readMovie(final JSONArray jsonMovieList) {
        List<Movie> movieList = new ArrayList<Movie>();
        if (jsonMovieList != null) {
            for (Object jsonMovie : jsonMovieList) {
                List<String> genres = new ArrayList<>();
                JSONArray jsonGenres = (JSONArray)
                        ((JSONObject) jsonMovie).get("genres");
                for (Object jsonGenre : jsonGenres) {
                    genres.add(jsonGenre.toString());
                }
                List<String> actors = new ArrayList<>();
                JSONArray jsonActors = (JSONArray) ((JSONObject) jsonMovie).get("actors");
                for (Object jsonActor : jsonActors) {
                    actors.add(jsonActor.toString());
                }
                List<String> countriesBanned = new ArrayList<>();
                JSONArray jsonCountriesBanned = (JSONArray)
                        ((JSONObject) jsonMovie).get("countriesBanned");
                for (Object jsonCountrie : jsonCountriesBanned) {
                    countriesBanned.add(jsonCountrie.toString());
                }
                String name = (String) ((JSONObject) jsonMovie).get("name");
                Integer year = ((Long) ((JSONObject) jsonMovie).get("year")).intValue();
                Integer duration = ((Long) ((JSONObject) jsonMovie).get("duration")).intValue();
                movieList.add(new Movie(name, year, duration, genres,
                        countriesBanned, actors, 0, 0.00, 0));
            }

        }

        return movieList;
    }

    /**
     *
     * @param jsonActionsList
     * @return
     */
    public List<Actions> readActions(final JSONArray jsonActionsList) {
        List<Actions> actionsList = new ArrayList<Actions>();
        if (jsonActionsList != null) {
            for (Object jsonAction : jsonActionsList) {
                String type = (String) ((JSONObject) jsonAction).get("type");
                String page = (String) ((JSONObject) jsonAction).get("page");
                String feature = (String) ((JSONObject) jsonAction).get("feature");
                String countStr = (String) ((JSONObject) jsonAction).get("count");
                Long auxRate = (Long) ((JSONObject) jsonAction).get("rate");
                Integer rate = 0;
                if (auxRate != null)
                    rate = auxRate.intValue();
//                Integer rate = 0;
//                if (auxRate != null) {
//                    rate = Integer.parseInt(auxRate);
//                }
                Integer count = 0;
                if (countStr != null) {
                    count = Integer.parseInt(countStr);
                }


                String  startsWith = (String) ((JSONObject) jsonAction).get("startsWith");
                JSONObject jsonCredentials = ((JSONObject)
                        ((JSONObject) jsonAction).get("credentials"));
                String name = null;
                String password = null;
                String country = null;
                String accountType = null;
                String movie = (String) ((JSONObject) jsonAction).get("movie");
                Integer balance = null;
                User userFromActions = new User();
                if (jsonCredentials != null) {
                    name = (String) jsonCredentials.get("name");
                    password = (String) jsonCredentials.get("password");
                    country = (String) jsonCredentials.get("country");
                    accountType = (String) jsonCredentials.get("accountType");

                    if ((String) (jsonCredentials.get("balance")) != null) {
                        String balanceAux = (String) (jsonCredentials.get("balance"));
                        balance = Integer.parseInt(balanceAux);;
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
                   JSONObject jsonSort = ((JSONObject) jsonFilter.get("sort"));
                   if (jsonSort != null) {
                       rating = (String) jsonSort.get("rating");
                       duration = (String) jsonSort.get("duration");
                   }
                    JSONObject jsonContains = (JSONObject) jsonFilter.get("contains");
                    if (jsonContains != null) {
                        JSONArray jsonArrayActors = (JSONArray) (jsonContains).get("actors");
                        if (jsonArrayActors != null) {
                            for (Object jsonActor : jsonArrayActors) {
                                actors.add(jsonActor.toString());
                            }
                        }

                        JSONArray jsonArrayGenre = (JSONArray) jsonContains.get("genre");
                        if (jsonArrayGenre != null) {


                            for (Object jsonGenre : jsonArrayGenre) {
                                genre.add(jsonGenre.toString());
                            }
                        }
                    }
                }

                actionsList.add(new Actions(type, page, feature, userFromActions, startsWith,
                        rating, duration, actors, genre, movie, count, rate));
            }
        }



        return actionsList;
    }

}
