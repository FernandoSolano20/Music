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
        boolean message = false;
        String queryString = "UPDATE Gender SET name= '"+ gender.getName() +"', description= '" + gender.getDescription() + "' " +
                "WHERE id= " + gender.getId() + "";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
    }

    @Override
    public boolean delete(Gender gender) {
        boolean message = false;
        String queryString = "DELETE FROM Gender " +
                "WHERE id = "+ gender.getId();
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
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

    @Override
    public Gender searchGenderById(int id){
        Gender gender = null;
        String queryString = "SELECT * FROM Gender " +
                "WHERE id = "+ id + "";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int idGender = result.getInt("id");
                String nameGender = result.getString("name");
                String description = result.getString("description");
                gender = new Gender(idGender,nameGender,description);
            }
        }
        catch (Exception e){
            gender = null;
        }
        return gender;
    }
}
