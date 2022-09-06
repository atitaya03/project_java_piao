package ku.cs.models;

public class Account {
    private String username;
    private String password;
    private String confirmpass;

    public Account(String username, String password, String confirmpass) {
        this.username = username;
        this.password = password;
        this.confirmpass = confirmpass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public boolean loginSuccess(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public boolean checkAccount(String username) {
        if (this.username.equals(username))
            return true;
        return false;

    }
}
