package com.musica.bl.ReproductionList;

import com.musica.bl.Dao;

public interface IReproductionListDao extends Dao<ReproductionList> {
    boolean saveSongs(ReproductionList reproductionList);
}
