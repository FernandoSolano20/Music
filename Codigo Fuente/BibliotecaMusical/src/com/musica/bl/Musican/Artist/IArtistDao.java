package com.musica.bl.Musican.Artist;

import com.musica.bl.Dao;

import java.util.List;

public interface IArtistDao extends Dao<Artist> {
    Artist searchArtistByArtistName(String name) throws Exception;
    Artist searchArtistById(int id) throws Exception;
    List<Artist> searchArtist(String seacher) throws Exception;
}
