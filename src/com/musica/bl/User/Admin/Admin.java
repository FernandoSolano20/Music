package com.musica.bl.User.Admin;

import com.musica.bl.User.User;

public class Admin extends User {
    public Admin(int id, String userName, String name, String lastName, String email, String pass, String image) {
        super(id, userName, name, lastName, email, pass, image);
        setType("Administrador");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
