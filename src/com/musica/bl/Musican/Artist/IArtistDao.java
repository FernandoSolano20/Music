package com.musica.bl.Musican.Artist;

import com.musica.bl.Dao;

public interface IArtistDao extends Dao<Artist> {
    Artist searchArtistByArtistName(String name);
}
