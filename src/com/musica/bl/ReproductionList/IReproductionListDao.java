package com.musica.bl.ReproductionList;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;

import java.util.List;

public interface IReproductionListDao extends Dao<ReproductionList> {
    boolean saveSongs(ReproductionList reproductionList);
    List<ReproductionList> searchReproductionListByUser(int id);
    List<Song> searchSongsByReproductionListId(int id);
    ReproductionList searchReproductionListById(int id);
    boolean deleteSongs(int idSong, int idReproduction);
}
