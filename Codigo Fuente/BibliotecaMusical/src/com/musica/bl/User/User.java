package com.musica.bl.User;

import com.musica.bl.Person;

import java.util.Objects;

public class User extends Person {
    private String userName;
    private String email;
    private String pass;
    private String image;
    private String type;
    public static User actualUser;
    private boolean firstTime;
    private String randomPass;

    public User(int id, String userName, String name, String lastName, String email, String pass, String image, String randomPass) {
        super(id,name,lastName);
        this.userName = userName;
        this.email = email;
        this.pass = pass;
        this.image = image;
        this.randomPass = randomPass;
    }

    public User(int id) {
        super(id);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static User getActualUser() {
        return actualUser;
    }

    public static void setActualUser(User actualUser) {
        User.actualUser = actualUser;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public String getRandomPass() {
        return randomPass;
    }

    public void setRandomPass(String randomPass) {
        this.randomPass = randomPass;
    }

    @Override
    public String toString() {
        return super.toString() + "," + userName +
                "," + email +
                "," + pass +
                "," + image +
                "," + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof User)) return false;
        User user = (User) o;
        return super.equals(user);
    }
}
