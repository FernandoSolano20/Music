package com.musica.bl.User.Client;

import com.musica.bl.Song.Song;
import com.musica.bl.User.User;
import com.musica.dl.DataAccess;

public class ClientDao implements IClientDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public boolean addSongToCatalog(User client, Song song) {
        boolean message = false;
        String queryString = "INSERT INTO Catalog(idUser, idSong) " +
                "VALUES("+ client.getId() +", "+ song.getId() + ")";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(Client client) {
        return false;
    }
}
