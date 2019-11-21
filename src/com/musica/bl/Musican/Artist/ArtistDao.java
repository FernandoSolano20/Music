package com.musica.bl.Musican.Artist;

import com.musica.bl.Album.Album;
import com.musica.bl.Country.Country;
import com.musica.bl.Dao;
import com.musica.bl.Gender.Gender;
import com.musica.dl.DataAccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao implements Dao<Artist> {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Artist> getAll() {
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
                String coun = result.getString("country");
                Country country = new Country(coun);
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
            artists = null;
        }
        return artists;
    }

    @Override
    public int save(Artist artist) {
        int message = -1;
        String queryString = "INSERT INTO Artist(name, lastName, born, dead, country, old, reference, description, idGender, artistName) " +
                "VALUES('"+ artist.getName() +"', '" + artist.getLastName() +"', '" + artist.getBorn() + "', '" + artist.getDead() +"', '" +
                artist.getCountry().getName() + "', '" + artist.getOld() +"', '" + artist.getReference() +"', '" + artist.getDescription() +"', '" +
                artist.getGender().getId() + "', '" + artist.getArtist() +"')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
        }
        return message;
    }

    @Override
    public boolean update(Artist artist) {
        return false;
    }

    @Override
    public boolean delete(Artist artist) {
        return false;
    }
}
