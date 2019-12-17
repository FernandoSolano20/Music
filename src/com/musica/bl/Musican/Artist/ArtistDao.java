package com.musica.bl.Musican.Artist;

import com.musica.bl.Gender.Gender;
import com.musica.dl.DataAccess;
import com.musica.dl.LogError;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao implements IArtistDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Artist> getAll() throws Exception {
        List<Artist> artists = new ArrayList<>();
        Artist artist = null;
        String queryString = "SELECT * FROM Artist as a INNER JOIN Gender as g ON a.idGender = g.id";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                LocalDate born = result.getDate("born").toLocalDate();
                Date dateDead = result.getDate("dead");
                LocalDate dead = null;
                if(dateDead != null){
                    dead = dateDead.toLocalDate();
                }
                String country = result.getString("country");
                int old = result.getInt("old");
                String reference = result.getString("reference");
                String description = result.getString("description");
                int idGender = result.getInt("idGender");
                String nameGender = result.getString(13);
                String descriptionGender = result.getString(14);
                String artistName = result.getString("artistName");
                Gender gender = new Gender(idGender,nameGender,descriptionGender);
                artist = new Artist(id,name,lastName,country,old,born,dead,reference,description,gender,artistName);
                artists.add(artist);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return artists;
    }

    @Override
    public int save(Artist artist) throws Exception {
        int message = -1;
        String queryString = "INSERT INTO Artist(name, lastName, born, dead, country, old, reference, description, idGender, artistName) " +
                "VALUES('"+ artist.getName() +"', '" + artist.getLastName() +"', '" + artist.getBorn() + "', '" + artist.getDead() +"', '" +
                artist.getCountry() + "', '" + artist.getOld() +"', '" + artist.getReference() +"', '" + artist.getDescription() +"', '" +
                artist.getGender().getId() + "', '" + artist.getArtist() +"')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean update(Artist artist) throws Exception {
        boolean message = false;
        String queryString = "UPDATE Artist SET name= '"+ artist.getName() +"', lastName= '" + artist.getLastName() + "', " +
                "born= '" + artist.getBorn() + "' , dead= '" + artist.getDead() + "' , old= " + artist.getOld() + " , " +
                "reference= '" + artist.getReference() + "' , description= '" + artist.getDescription() + "' , idGender= " + artist.getGender().getId() + ", " +
                "artistName= '" + artist.getArtist() + "'" +
                "WHERE id= " + artist.getId() + "";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al actualizar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean delete(Artist artist) throws Exception {
        boolean message = false;
        String queryString = "DELETE FROM Artist " +
                "WHERE id = "+ artist.getId();
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al borrar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public List<Artist> searchArtist(String seacher) throws Exception {
        List<Artist> artists = new ArrayList<>();
        Artist artist = null;
        String queryString = "SELECT * FROM Artist as a INNER JOIN Gender as g ON a.idGender = g.id " +
                "WHERE name + ' ' + lastName = '" + seacher + "' AND country = '" + seacher + "' AND artistName = '" + seacher + "' ";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String nameArtist = result.getString("name");
                String lastNameArtist = result.getString("lastName");
                LocalDate born = result.getDate("born").toLocalDate();
                Date dateDead = result.getDate("dead");
                LocalDate dead = null;
                if(dateDead != null){
                    dead = dateDead.toLocalDate();
                }
                String country = result.getString("country");
                int old = result.getInt("old");
                String reference = result.getString("reference");
                String description = result.getString("description");
                int idGender = result.getInt("idGender");
                String nameGender = result.getString(13);
                String descriptionGender = result.getString(14);
                String artistName = result.getString("artistName");
                Gender gender = new Gender(idGender,nameGender,descriptionGender);
                artist = new Artist(id,nameArtist,lastNameArtist,country,old,born,dead,reference,description,gender,artistName);
            }
            artists.add(artist);
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return artists;
    }

    @Override
    public Artist searchArtistByArtistName(String name) throws Exception {
        Artist artist = null;
        String queryString = "SELECT * FROM Artist as a INNER JOIN Gender as g ON a.idGender = g.id " +
                "WHERE artistName = '"+ name + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String nameArtist = result.getString("name");
                String lastName = result.getString("lastName");
                LocalDate born = result.getDate("born").toLocalDate();
                Date dateDead = result.getDate("dead");
                LocalDate dead = null;
                if(dateDead != null){
                    dead = dateDead.toLocalDate();
                }
                String country = result.getString("country");
                int old = result.getInt("old");
                String reference = result.getString("reference");
                String description = result.getString("description");
                int idGender = result.getInt("idGender");
                String nameGender = result.getString(13);
                String descriptionGender = result.getString(14);
                String artistName = result.getString("artistName");
                Gender gender = new Gender(idGender,nameGender,descriptionGender);
                artist = new Artist(id,nameArtist,lastName,country,old,born,dead,reference,description,gender,artistName);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return artist;
    }

    @Override
    public Artist searchArtistById(int id) throws Exception {
        Artist artist = null;
        String queryString = "SELECT * FROM Artist as a INNER JOIN Gender as g ON a.idGender = g.id " +
                "WHERE a.id = "+ id + "";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int idArtist = result.getInt("id");
                String nameArtist = result.getString("name");
                String lastName = result.getString("lastName");
                LocalDate born = result.getDate("born").toLocalDate();
                Date dateDead = result.getDate("dead");
                LocalDate dead = null;
                if(dateDead != null){
                    dead = dateDead.toLocalDate();
                }
                String country = result.getString("country");
                int old = result.getInt("old");
                String reference = result.getString("reference");
                String description = result.getString("description");
                int idGender = result.getInt("idGender");
                String nameGender = result.getString(13);
                String descriptionGender = result.getString(14);
                String artistName = result.getString("artistName");
                Gender gender = new Gender(idGender,nameGender,descriptionGender);
                artist = new Artist(idArtist,nameArtist,lastName,country,old,born,dead,reference,description,gender,artistName);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return artist;
    }
}
