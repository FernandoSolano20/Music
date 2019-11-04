package com.musica.bl;

import com.musica.bl.User.User;
import com.musica.bl.User.UserDao;
import com.musica.dl.DataAccess;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaMusical {
    private DataAccess dl = new DataAccess();
    Dao dao;

    public String registerUser(User user){
        dao = new UserDao();
        return dao.save(user);
    }

    public List<String> getAllUser(){
        dao = new UserDao();
        ArrayList<String> result = new ArrayList<>();
        List<User> users = dao.getAll();
        if(users.size() > 0){
            for (User user:users) {
                result.add(user.toString());
            }
        }
        else {
            result.add("No hay usuarios");
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return new UserDao().isAdminOnDB();
    }
}
