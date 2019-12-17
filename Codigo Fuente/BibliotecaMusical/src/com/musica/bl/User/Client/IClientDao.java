package com.musica.bl.User.Client;

import com.musica.bl.Song.Song;
import com.musica.bl.User.User;

public interface IClientDao {
    boolean addSongToCatalog(User client, Song song) throws Exception;
}
