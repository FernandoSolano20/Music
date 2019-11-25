package com.musica.bl.Musican.Artist;

import com.musica.bl.Dao;

import java.util.List;

public interface IArtistDao extends Dao<Artist> {
    Artist searchArtistByArtistName(String name);
    Artist searchArtistById(int id);
    List<Artist> searchArtist(String seacher);
}
