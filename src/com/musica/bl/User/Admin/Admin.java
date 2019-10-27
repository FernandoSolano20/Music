package com.musica.bl.User.Admin;

import com.musica.bl.User.IUser;
import com.musica.bl.User.User;

public class Admin extends User implements IUser {
    public Admin(int id, String userName, String name, String lastName, String email, String pass, String image) {
        super(id, userName, name, lastName, email, pass, image);
    }
}
