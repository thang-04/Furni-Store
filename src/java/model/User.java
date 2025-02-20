package model;

import java.util.Date;

public class User {

    private int uid;
    private String userName;
    private String fullName;
    private String pass;
    private int roleId;
    private String address;
    private Date birthDay;
    private String phone;
    private String email;
    private String image;

    public User() {
    }

    public User(int uid, String userName, String fullName, String pass, int roleId,
            String address, Date birthDay, String phone, String email, String image) {
        this.uid = uid;
        this.userName = userName;
        this.fullName = fullName;
        this.pass = pass;
        this.roleId = roleId;
        this.address = address;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
