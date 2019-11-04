package com.musica.bl.User;

import com.musica.bl.Dao;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;
import com.musica.dl.DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    private DataAccess dataAccess = new DataAccess();

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        User user = null;
        String queryString = "SELECT * FROM User";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String pass = result.getString("password");
                String userName = result.getString("userName");
                String type = result.getString("type");
                String image = result.getString("image");
                int old = 0;
                String country = "";

                if(type.equals("Cliente")){
                    old = result.getInt("yearsOld");
                    country = result.getString("country");
                    user = new Client(id, userName, name, lastName, email, pass, image, old, country);
                }
                else {
                    user = new Admin(id, userName, name, lastName, email, pass, image);
                }
                users.add(user);
            }
        }
        catch (Exception e){
            users = null;
        }
        return users;
    }

    @Override
    public String save(User user) {
        String message = "";
        String queryString = "INSERT INTO User(id, name, lastName, email, password, userName, image, type";

        String queryValues = ") VALUES("+ user.getId() +", '"+ user.getName() +"', '"+ user.getLastName() +"', '"+ user.getEmail() +"', '"+ user.getPass() +
                "', '"+ user.getUserName() +"', '"+ user.getImage() +"', '"+ user.getType() +"'";

        if(user.getType() == "Cliente" && user instanceof Client){
            queryString += ", yearsOld, country";
            Client client = (Client) user;
            queryValues += ", "+ client.getOld() +", '"+ client.getCountry() +"'";
        }

        queryValues += ")";
        try {
            message = dataAccess.insertData(queryString+queryValues);
        } catch (Exception e) {
            message = "Error";
        }
        return message;
    }

    @Override
    public String update(User user) {
        return null;
    }

    @Override
    public String delete(User user) {
        return null;
    }

    public boolean isAdminOnDB(){
        int count = 0;
        String queryString = "SELECT id FROM User " +
                "WHERE type = 'Administrador'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                count++;
            }
        }
        catch (Exception e){
            return true;
        }
        return count > 0;
    }
}
