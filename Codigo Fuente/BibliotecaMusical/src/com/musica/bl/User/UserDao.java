package com.musica.bl.User;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;
import com.musica.bl.Song.SongDao;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;
import com.musica.dl.DataAccess;
import com.musica.dl.LogError;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private DataAccess dataAccess = new DataAccess();

    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        String queryString = "SELECT * FROM User";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                users.add(createUser(result));
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return users;
    }

    @Override
    public int save(User user) throws Exception {
        boolean message = false;
        String queryString = "INSERT INTO User(id, name, lastName, email, password, userName, image, type, firstTime, randomPass";

        String queryValues = ") VALUES("+ user.getId() +", '"+ user.getName() +"', '"+ user.getLastName() +"', '"+ user.getEmail() +"', '"+ user.getPass() +
                "', '"+ user.getUserName() +"', '"+ user.getImage() +"', '"+ user.getType() +"', "+ user.isFirstTime() +", '"+ user.getRandomPass() +"'";

        if(user.getType() == "Cliente" && user instanceof Client){
            queryString += ", yearsOld, country";
            Client client = (Client) user;
            queryValues += ", "+ client.getOld() +", '"+ client.getCountry() +"'";
        }

        queryValues += ")";
        try {
            message = dataAccess.insertData(queryString+queryValues);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return message ? 1 : -1;
    }

    @Override
    public boolean update(User user) throws Exception {
        boolean message = false;
        String queryString = "UPDATE User SET password= '" + user.getPass() + "', firstTime=" + user.isFirstTime() + ", randomPass= '" + user.getRandomPass() + "' " +
                "WHERE id = " + user.getId() + "";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al actualizar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    public boolean isAdminOnDB() throws Exception {
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
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return count > 0;
    }

    public User login(String email, String pass) throws Exception {
        int count = 0;
        User user = null;
            String queryString = "SELECT * FROM User " +
                    "WHERE userName = '"+ email +"' AND password = '"+ pass +"'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                user = createUser(result);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws Exception {
        String queryString = "SELECT * FROM User " +
                "WHERE id = " + id + "";
        ResultSet result = dataAccess.selectData(queryString);
        User user = null;
        try{
            while (result.next())
            {
                user = createUser(result);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = null;
        String queryString = "SELECT * FROM User " +
                "WHERE email = '"+ email +"'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                user = createUser(result);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return user;
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        User user = null;
        String queryString = "SELECT * FROM User " +
                "WHERE userName = '"+ userName +"'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                user = createUser(result);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return user;
    }

    private User createUser(ResultSet result) throws Exception {
        User user = null;
        int id = result.getInt("id");
        String name = result.getString("name");
        String lastName = result.getString("lastName");
        String email = result.getString("email");
        String pass = result.getString("password");
        String userName = result.getString("userName");
        String type = result.getString("type");
        String image = result.getString("image");
        String random = result.getString("randomPass");
        int old = 0;
        String country = "";

        if(type.equals("Cliente")){
            old = result.getInt("yearsOld");
            country = result.getString("country");
            user = new Client(id, userName, name, lastName, email, pass, image, random, old, country);

            List<Song> songs = new ArrayList<>();
            Song song = null;
            String queryStringSong = "SELECT * FROM music.catalog as c " +
                    "INNER JOIN music.song as s ON c.idSong = s.id " +
                    "WHERE c.idUser = " + user.getId();
            ResultSet resultSongs = dataAccess.selectData(queryStringSong);
            try{
                while (resultSongs.next())
                {
                    int idSong = resultSongs.getInt("idSong");
                    SongDao songDao = new SongDao();
                    ((Client)user).setSongOnCatalog(songDao.searchSongById(idSong));
                }
            }
            catch (Exception e){
                LogError.getLogger().info("Error " + e.getMessage());
                throw new Exception("Error al obtener la informacion en la base de datos");
            }
        }
        else {
            user = new Admin(id, userName, name, lastName, email, pass, image, random);
        }
        user.setFirstTime(result.getBoolean("firstTime"));
        return user;
    }
}
