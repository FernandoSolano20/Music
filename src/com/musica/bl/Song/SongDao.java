package com.musica.bl.Song;

import com.musica.bl.Dao;
import com.musica.bl.User.Client.Client;
import com.musica.dl.DataAccess;

import java.util.List;

public class SongDao implements Dao<Song> {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Song> getAll() {
        return null;
    }

    @Override
    public boolean save(Song song) {
        boolean message = false;
        String queryString = "INSERT INTO Song(name, ) " +
                "VALUES('"+ song.getName() +"', '"+ song.getGender() +"', '"+ song.getArtist() +"', '"+ song.getCompositor() +"', '"+ song.getRelease() +
                "', '"+ song.getAlbum() +"', "+ song.getScore() +", '"+ song.getSong() +"')";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
    }

    @Override
    public boolean update(Song song) {
        return false;
    }

    @Override
    public boolean delete(Song song) {
        return false;
    }
}
