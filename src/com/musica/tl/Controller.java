package com.musica.tl;

import com.musica.bl.BibliotecaMusical;
import com.musica.bl.User.Client;

public class Controller {

    BibliotecaMusical logic = new BibliotecaMusical();

    public String registerClient(int id, String name, String lastName, String old, String country, String email, String pass, String userName, String image){
        Client client = new Client();
        return logic.registerUser(client);
    }
}
