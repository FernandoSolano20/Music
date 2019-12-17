package com.musica.bl;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws Exception;

    int save(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(T t) throws Exception;
}
