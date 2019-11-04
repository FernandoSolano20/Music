package com.musica.bl;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAll();

    String save(T t);

    String update(T t);

    String delete(T t);
}
