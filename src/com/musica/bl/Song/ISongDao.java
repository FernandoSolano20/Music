package com.musica.bl.Song;

import com.musica.bl.Dao;
import com.musica.bl.ReproductionList.ReproductionList;

public interface ISongDao extends Dao<Song> {
    Song searchSongByName(String song);
}
