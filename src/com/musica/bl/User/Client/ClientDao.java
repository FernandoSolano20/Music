package com.musica.bl.User.Client;

import com.musica.bl.Song.Song;
import com.musica.bl.User.User;
import com.musica.dl.DataAccess;
import com.musica.dl.LogError;

public class ClientDao implements IClientDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public boolean addSongToCatalog(User client, Song song) throws Exception {
        boolean message = false;
        String queryString = "INSERT INTO Catalog(idUser, idSong) " +
                "VALUES("+ client.getId() +", "+ song.getId() + ")";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return message;
    }
}
