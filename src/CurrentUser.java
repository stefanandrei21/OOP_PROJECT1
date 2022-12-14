import java.util.ArrayList;
import java.util.List;

public class CurrentUser extends User{
    private int tokensCount;
    private int numFreePremiumMovies;


    private List<String> purchasedMovies;
    private List<String> watchedMovies;
    private List<String> likedMovies;
    private List<String> ratedMovies;

    public CurrentUser(int tokensCount, int numFreePremiumMovies, List<String> purchasedMovies, List<String> watchedMovies, List<String> likedMovies, List<String> ratedMovies) {
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
    }
    public CurrentUser(User user) {
        super(user);
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }
    public CurrentUser(String name, String password, String accountType, String country, Integer balance, int tokensCount, int numFreePremiumMovies, List<String> purchasedMovies, List<String> watchedMovies, List<String> likedMovies, List<String> ratedMovies) {
        super(name, password, accountType, country, balance);
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
    }

    public CurrentUser(User user, int tokensCount, int numFreePremiumMovies, List<String> purchasedMovies, List<String> watchedMovies, List<String> likedMovies, List<String> ratedMovies) {
        super(user);
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public List<String> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(List<String> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public List<String> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(List<String> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public List<String> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(List<String> likedMovies) {
        this.likedMovies = likedMovies;
    }

    @Override
    public String toString() {
        return  super.toString() + "CurrentUser{" +
                "tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                '}';
    }

    public List<String> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(List<String> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
