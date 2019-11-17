package com.musica.bl;

import java.util.List;

public interface Dao<T> {

    List<T> getAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
