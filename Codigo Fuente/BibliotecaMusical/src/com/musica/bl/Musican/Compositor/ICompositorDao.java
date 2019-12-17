package com.musica.bl.Musican.Compositor;

import com.musica.bl.Dao;

public interface ICompositorDao extends Dao<Compositor> {
    boolean saveGenders(Compositor compositor) throws Exception;
    Compositor searchCompositorByNameAndLastName(String name) throws Exception;
    Compositor searchCompositorById(int id) throws Exception;
}
