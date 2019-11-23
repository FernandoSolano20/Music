package com.musica.bl.Gender;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;
import com.musica.dl.DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenderDao implements IGenderDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Gender> getAll() {
        List<Gender> genders = new ArrayList<>();
        Gender gender = null;
        String queryString = "SELECT * FROM Gender";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                gender = new Gender(id,name,description);
                genders.add(gender);
            }
        }
        catch (Exception e){
            genders = null;
        }
        return genders;
    }

    @Override
    public int save(Gender gender) {
        int message = -1;
        String queryString = "INSERT INTO Gender(name, description) " +
                "VALUES('"+ gender.getName() +"', '"+ gender.getDescription() +"')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
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

    @Override
    public Gender searchGenderByName(String name) {
        Gender gender = null;
        String queryString = "SELECT * FROM Gender " +
                "WHERE name = '"+ name + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String nameGender = result.getString("name");
                String description = result.getString("description");
                gender = new Gender(id,nameGender,description);
            }
        }
        catch (Exception e){
            gender = null;
        }
        return gender;
    }
}
