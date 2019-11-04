package com.musica.tl;

import com.musica.bl.BibliotecaMusical;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    BibliotecaMusical logic = new BibliotecaMusical();

    public String registerClient(int id, String name, String lastName, int old, String country, String email, String pass, String userName, String image){
        Client client = new Client(id,userName,name,lastName,email,pass,image,old,country);
        return logic.registerUser(client);
    }

    public List<String> getAllUser(){
        ArrayList<String> result = (ArrayList<String>)logic.getAllUser();
        for (String item:result) {
            System.out.println(item);
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return logic.isAdminOnDB();
    }

    public String registerAdmin(int id, String name, String lastName, String email, String pass, String userName, String image) {
        Admin admin = new Admin(id,userName,name,lastName,email,pass,image);
        return logic.registerUser(admin);
    }
}
