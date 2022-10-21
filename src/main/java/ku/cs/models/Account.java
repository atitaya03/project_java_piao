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
    private String unBannedRequest;
    private  Theme theme = new Theme();


    public Account(String displaynamename,String username, String password, String role,String organization) {
        this.role = role;
        this.displayname = displaynamename;
        this.username = username;
        this.password = password;
        this.organization = organization;
        this.imagePath = "data/profileUsers/defaultProfile.png";
        this.isBaned = false;
        this.loginAttempt = 0;
        this.loginTime=getLoginTime();
        this.unBannedRequest = "-";
        initialLoginTime();
    }

    public Account(String displayname,String username, String password, String role,String organization,boolean isBaned,int loginAttempt , String imagePath, String loginTime,String unBannedRequest) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.organization = organization;
        this.imagePath = imagePath;
        this.isBaned = isBaned;
        this.loginAttempt = loginAttempt;
        this.loginTime=loginTime;
        this.unBannedRequest = unBannedRequest;

    }
    public void initialLoginTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Before Formatting: " + now);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.loginTime = now.format(format);
    }

    public LocalDateTime getTime() {
        String[] time = loginTime.split(" ");

        String[] data = time[0].split("-");
        int year = Integer.parseInt(data[2]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[0]);

        String[] splittime = time[1].split(":");
        int hour = Integer.parseInt(splittime[0]);
        int minute = Integer.parseInt(splittime[1]);
        int sec = Integer.parseInt(splittime[2]);
        return LocalDateTime.of(year, month, day, hour, minute, sec);

    }

    public Theme getTheme() {
        return theme;
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

    public boolean isBanned() {
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

    @Override
    public String toString() {
        String banStatus = "ปกติ";
        if (this.isBaned == true) banStatus = "ถูกแบน";
        return "ชื่อบัญชีผู้ใช้: " + username + " [สถานะ: " + banStatus + "]\nประเภทผู้ใช้: "+ role + "\nเข้าใช้งานล่าสุด: " + loginTime;
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
        if (isAdmin()){
            student.isBaned = false ;
            student.loginAttempt = 0;
            student.unBannedRequest="-";}
    }
    public void ban(Account student) {
        if (isAdmin())
            student.isBaned = true ;
    }

    public void unBanRequest(String request) {
        unBannedRequest = request;
    }

    public String getUnbanRequest() {
        return unBannedRequest;
    }
}
