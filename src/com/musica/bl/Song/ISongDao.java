package com.musica.bl.Song;

import com.musica.bl.Dao;
import com.musica.bl.ReproductionList.ReproductionList;

import java.util.List;

public interface ISongDao extends Dao<Song> {
    Song searchSongByName(String song);
    Song searchSongById(int id);
    List<Song> searchSongByAlbumId(int id);
}
