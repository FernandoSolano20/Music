package com.musica.bl.User;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;
import com.musica.bl.Song.SongDao;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;
import com.musica.dl.DataAccess;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
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

                    List<Song> songs = new ArrayList<>();
                    Song song = null;
                    String queryStringSong = "SELECT * FROM Catalog as c" +
                            "INNER JOIN Song as s " +
                            "ON c.idSong = s.id " +
                            "WHERE idUser = " + user.getId();
                    ResultSet resultSongs = dataAccess.selectData(queryString);
                    try{
                        while (resultSongs.next())
                        {
                            int idSong = result.getInt("idSong");
                            SongDao songDao = new SongDao();
                            ((Client)users).setSongOnCatalog(songDao.searchSongById(idSong));
                        }
                    }
                    catch (Exception e){
                        users = null;
                    }
                    return users;
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
    public int save(User user) {
        boolean message = false;
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
            message = false;
        }
        return message ? 1 : -1;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
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

    public User login(String email, String pass){
        int count = 0;
        User user = null;
            String queryString = "SELECT * FROM User " +
                    "WHERE email = '"+ email +"' AND password = '"+ pass +"'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String emailUser = result.getString("email");
                String passUser = result.getString("password");
                String userName = result.getString("userName");
                String type = result.getString("type");
                String image = result.getString("image");
                int old = 0;
                String country = "";

                if(type.equals("Cliente")){
                    old = result.getInt("yearsOld");
                    country = result.getString("country");
                    user = new Client(id, userName, name, lastName, emailUser, passUser, image, old, country);
                }
                else {
                    user = new Admin(id, userName, name, lastName, emailUser, passUser, image);
                }

            }
        }
        catch (Exception e){
            return user;
        }
        return user;
    }
}
