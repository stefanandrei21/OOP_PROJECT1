import java.util.List;

public class Login extends Page{
    private CurrentUser userLoggedIn;

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
