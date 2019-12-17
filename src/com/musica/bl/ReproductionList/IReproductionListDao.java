package com.musica.bl.ReproductionList;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;

import java.util.List;

public interface IReproductionListDao extends Dao<ReproductionList> {
    boolean saveSongs(ReproductionList reproductionList) throws Exception;
    List<ReproductionList> searchReproductionListByUser(int id) throws Exception;
    List<Song> searchSongsByReproductionListId(int id) throws Exception;
    ReproductionList searchReproductionListById(int id) throws Exception;
    boolean deleteSongs(int idSong, int idReproduction) throws Exception;
}
