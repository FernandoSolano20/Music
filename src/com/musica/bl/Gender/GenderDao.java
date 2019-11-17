package com.musica.bl.Gender;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;
import com.musica.dl.DataAccess;

import java.util.List;

public class GenderDao implements Dao<Gender> {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Gender> getAll() {
        return null;
    }

    @Override
    public boolean save(Gender gender) {
        boolean message = false;
        String queryString = "INSERT INTO Gender(name, description) " +
                "VALUES('"+ gender.getName() +"', '"+ gender.getDescription() +"')";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
    }

    @Override
    public boolean update(Gender gender) {
        return false;
    }

    @Override
    public boolean delete(Gender gender) {
        return false;
    }
}
