public class Login {
    private User user;

    public Login(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
        dsa
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean verifyCredentials(String username, String password){
        return username.equals(this.user.getName()) && password.equals(this.user.getPassword());
    }
}
