package com.musica.bl.Album;

import com.musica.bl.Dao;

public interface IAlbumDao extends Dao<Album> {
    boolean saveArtists(Album album);
    Album searchAlbumByName(String name);
    Album searchAlbumById(int id);
}
