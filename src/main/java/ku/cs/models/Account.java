package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String role;
    private String username;
    private String displayname;

    private String organization;
    private String password;
    private  String imagePath;
    private boolean isBaned;
    private int loginAttempt;

    private String loginTime;


    public Account(String displaynamename,String username, String password, String role,String organization) {
        this.role = role;
        this.displayname = displaynamename;
        this.username = username;
        this.password = password;
        this.organization = organization;
        this.imagePath = "/ku/cs/profileUsers/defaultProfile.jpg";
        this.isBaned = false;
        this.loginAttempt = 0;
        this.loginTime="00";

    }
    public Account(String displayname,String username, String password, String role,String organization,boolean isBaned,int loginAttempt , String imagePath, String loginTime) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.organization = organization;
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

    public String getDisplayname() {
        return displayname;
    }

    public String getOrganization() {
        return organization;
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
        return this.role.equals("student");

    }
    public boolean isStaff() {
        return this.role.equals("staff");

    }
    public boolean isAdmin() {
        return this.role.equals("admin");

    }

    public void updateLoginAttempt() {
        this.loginAttempt ++ ;
    }
    public void unBan(Account student) {
        if (isAdmin())
            student.isBaned = false ;
    }
}
