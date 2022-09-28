package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String role;
    private String username;
    private String password;
    private  String imagePath;
    private boolean isBaned;
    private int loginAttempt;

    private String loginTime;


    public Account(String username, String password, String role) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.imagePath = "/ku/cs/profileUsers/defaultProfile.jpg";
        this.isBaned = false;
        this.loginAttempt = 0;
        this.loginTime="00";

    }
    public Account(String username, String password, String role,boolean isBaned,int loginAttempt , String imagePath, String loginTime) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.imagePath = imagePath;
        this.isBaned = isBaned;
        this.loginAttempt = loginAttempt;
        this.loginTime=loginTime;

    }
    public void initialLoginTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Before Formatting: " + now);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
         this.loginTime = now.format(format);
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

    public boolean isBaned() {
        return isBaned;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public boolean loginSuccess(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }
    public void changePassword(String newPassword){
        password = newPassword;
    }

    public boolean checkAccount(String username) {
        if (this.username.equals(username))
            return true;
        return false;

    }
    public boolean isStudent() {
        if (this.role.equals("student"))
            return true;
        return false;

    }
    public boolean isStaff() {
        if (this.role.equals("staff"))
            return true;
        return false;

    }
    public boolean isAdmin() {
        if (this.role.equals("admin"))
            return true;
        return false;

    }


}
