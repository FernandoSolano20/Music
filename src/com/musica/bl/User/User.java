package com.musica.bl.User;

public abstract class User {
    private int id;
    private String userName;
    private String name;
    private String lastName;
    private String email;
    private String pass;
    private String image;

    public User(int id, String userName, String name, String lastName, String email, String pass, String image) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pass = pass;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
