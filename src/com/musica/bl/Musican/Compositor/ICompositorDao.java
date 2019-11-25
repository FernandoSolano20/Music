package com.musica.bl.Musican.Compositor;

import com.musica.bl.Dao;

public interface ICompositorDao extends Dao<Compositor> {
    boolean saveGenders(Compositor compositor);
    Compositor searchCompositorByNameAndLastName(String name);
    Compositor searchCompositorById(int id);
}
