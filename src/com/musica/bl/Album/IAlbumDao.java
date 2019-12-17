package com.musica.bl.Album;

import com.musica.bl.Dao;

public interface IAlbumDao extends Dao<Album> {
    boolean saveArtists(Album album) throws Exception;
    Album searchAlbumByName(String name) throws Exception;
    Album searchAlbumById(int id) throws Exception;
}
