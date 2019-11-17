package com.musica.bl.Musican.Artist;

import com.musica.bl.Dao;
import com.musica.bl.Gender.Gender;
import com.musica.dl.DataAccess;

import java.util.List;

public class ArtistDao implements Dao<Artist> {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Artist> getAll() {
        return null;
    }

    @Override
    public boolean save(Artist artist) {
        boolean message = false;
        String queryString = "INSERT INTO Artist(name, lastName, born, dead, country, old, reference, description, idGender, artistName) " +
                "VALUES('"+ artist.getName() +"', '" + artist.getLastName() +"', '" + artist.getBorn() + "', '" + artist.getDead() +"', '" +
                artist.getCountry().getName() + "', '" + artist.getOld() +"', '" + artist.getReference() +"', '" + artist.getDescription() +"', '" +
                artist.getGender().getId() + "', '" + artist.getArtist() +"')";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
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
