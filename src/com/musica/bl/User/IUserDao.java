package com.musica.bl.User;

import com.musica.bl.Dao;

public interface IUserDao extends Dao<User> {
    boolean isAdminOnDB() throws Exception;
    User login(String email, String pass) throws Exception;
    User getUserById(int id) throws Exception;
    User getUserByEmail(String email) throws Exception;
}
