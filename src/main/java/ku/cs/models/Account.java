package ku.cs.models;

public class Account {
    private String role;
    private String username;
    private String password;
    private  String imagePath;


    public Account(String username, String password, String role) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.imagePath = "/ku/cs/profileUsers/defaultProfile.jpg";

    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
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
