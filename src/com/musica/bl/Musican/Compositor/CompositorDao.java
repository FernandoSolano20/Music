package com.musica.bl.Musican.Compositor;

import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.dl.DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompositorDao implements ICompositorDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Compositor> getAll() {
        List<Compositor> compositors = new ArrayList<>();
        Compositor compositor = null;
        String queryString = "SELECT * FROM Compositor";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String country = result.getString("country");
                int old = result.getInt("old");
                compositor = new Compositor(id,name,lastName,country,old);

                Gender gender = null;
                String queryStringGender = "SELECT * FROM GenderCompositor as gc " +
                        "INNER JOIN Gender as g " +
                        "ON gc.idGender = g.id " +
                        "WHERE idCompositor = " + compositor.getId();
                ResultSet resultGender = dataAccess.selectData(queryStringGender);
                try{
                    while (resultGender.next())
                    {
                        int idGender = resultGender.getInt("id");
                        String nameGender = resultGender.getString("name");
                        String description = resultGender.getString("description");
                        gender = new Gender(idGender,nameGender,description);
                        compositor.setGenders(gender);
                    }
                }
                catch (Exception e){
                    gender = null;
                }

                compositors.add(compositor);
            }
        }
        catch (Exception e){
            compositors = null;
        }
        return compositors;
    }

    @Override
    public int save(Compositor compositor) {
        int message = -1;
        String queryString = "INSERT INTO Compositor(name, lastName, country, old) " +
                "VALUES('"+ compositor.getName() +"', '" + compositor.getLastName() + "', '"  + compositor.getCountry() + "', '"
                + compositor.getOld() +"')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
        }
        return message;
    }

    @Override
    public boolean update(Compositor compositor) {
        return false;
    }

    @Override
    public boolean delete(Compositor compositor) {
        return false;
    }

    public boolean saveGenders(Compositor compositor){
        boolean message = false;
        ArrayList<Gender> genders = compositor.getGenders();
        for (Gender gender:genders) {
            String queryString = "INSERT INTO GenderCompositor(idGender, idCompositor) " +
                    "VALUES('"+ gender.getId() +"', '" + compositor.getId() +"')";
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                message = false;
            }
        }
        return message;
    }

    @Override
    public Compositor searchCompositorByNameAndLastName(String name, String lastName) {
        Compositor compositor = null;
        String queryString = "SELECT * FROM Compositor " +
                "WHERE name = '" + name + "' AND lastName = '" + lastName + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String nameCompositor = result.getString("name");
                String lastNameCompositor = result.getString("lastName");
                String country = result.getString("country");
                int old = result.getInt("old");
                compositor = new Compositor(id,nameCompositor,lastNameCompositor,country,old);

                Gender gender = null;
                String queryStringGender = "SELECT * FROM GenderCompositor as gc " +
                        "INNER JOIN Gender as g " +
                        "ON gc.idGender = g.id " +
                        "WHERE idCompositor = " + compositor.getId();
                ResultSet resultGender = dataAccess.selectData(queryStringGender);
                try{
                    while (resultGender.next())
                    {
                        int idGender = resultGender.getInt("id");
                        String nameGender = resultGender.getString("name");
                        String description = resultGender.getString("description");
                        gender = new Gender(idGender,nameGender,description);
                        compositor.setGenders(gender);
                    }
                }
                catch (Exception e){
                    gender = null;
                }
            }
        }
        catch (Exception e){
            compositor = null;
        }
        return compositor;
    }
}
