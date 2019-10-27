package com.musica.bl.User;

public class ReadUser {
    public String getUsersById(int id){
        return "SELECT * FROM User\n" +
                "WHERE id= " + id + ";";
    }
}
