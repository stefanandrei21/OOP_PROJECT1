package DataBase;

import java.util.Objects;

public class User {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private Integer balance;
    private boolean loggedIn = false;

    public User() {
        this.name = null;
        this.password = null;
        this.accountType = null;
        this.country = null;
        this.balance = null;
    }
    public User(final String name, final String password, final String accountType, final String country, final Integer balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public User(final User user) {
        this.name = user.name;
        this.password = user.password;
        this.balance = user.balance;
        this.country = user.country;
        this.accountType = user.accountType;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof User user)) { return false; }
        return getName().equals(user.getName());
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     *
     * @return
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     *
     * @return
     */
    public void setLoggedIn(final boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     *
     * @return
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     *
     * @return
     */
    public void setBalance(final Integer balance) {
        this.balance = balance;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Database.User{"
                + "name='" + name + '\''
                + ", password='" + password + '\''
                + ", accountType='" + accountType + '\''
                + ", country='" + country + '\''
                + ", balance=" + balance
                + '}';
    }

}
