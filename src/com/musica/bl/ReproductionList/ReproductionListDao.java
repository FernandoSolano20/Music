package com.musica.bl.ReproductionList;

import com.musica.bl.Dao;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Song.Song;
import com.musica.bl.Song.SongDao;
import com.musica.bl.User.User;
import com.musica.dl.DataAccess;
import com.musica.dl.LogError;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReproductionListDao implements IReproductionListDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<ReproductionList> getAll() throws Exception {
        List<ReproductionList> reproductionLists = new ArrayList<>();
        ReproductionList reproductionList = null;
        String queryString = "SELECT * FROM ReproductionList as rl " +
                "INNER JOIN User as u ON rl.idUser = u.id";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                LocalDate create = result.getDate("create").toLocalDate();
                String name = result.getString("name");
                double score = result.getDouble("score");
                int idUser = result.getInt("idUser");
                User user = new User(idUser);
                reproductionList = new ReproductionList(id,create,name,score,user);
                Song song = null;
                String queryStringGender = "SELECT * FROM ReproductionListSong as rls " +
                        "INNER JOIN Song as s " +
                        "ON rls.idSong = s.id " +
                        "WHERE idReproductionList = " + reproductionList.getId();
                ResultSet resultSong = dataAccess.selectData(queryStringGender);
                try{
                    SongDao songDao = new SongDao();
                    while (resultSong.next())
                    {
                        int idSong = resultSong.getInt("id");
                        song = songDao.searchSongById(idSong);
                        reproductionList.setSongs(song);
                    }
                }
                catch (Exception e){
                    LogError.getLogger().info("Error " + e.getMessage());
                    throw new Exception("Error al obtener la informacion en la base de datos");
                }
                reproductionLists.add(reproductionList);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return reproductionLists;
    }

    @Override
    public int save(ReproductionList reproductionList) throws Exception {
        int message = -1;
        String queryString = "INSERT INTO music.reproductionlist(reproductionlist.create, reproductionlist.name, reproductionlist.score, reproductionlist.idUser) " +
                "VALUES('"+ reproductionList.getCreate() +"', '"+ reproductionList.getName() +"', "+ reproductionList.getScore() +
                ", "+ reproductionList.getUser().getId() + ")";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean update(ReproductionList reproductionList) throws Exception {
        boolean message = false;
        String queryString = "UPDATE ReproductionList SET name= '"+ reproductionList.getName() +"' " +
                "WHERE id = "+ reproductionList.getId() +"";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al actualizar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean delete(ReproductionList reproductionList) throws Exception {
        boolean message = false;
        String queryString = "DELETE FROM ReproductionList " +
                "WHERE id = "+ reproductionList.getId() +"";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al borrar la informacion en la base de datos");
        }
        return message;
    }

    public boolean saveSongs(ReproductionList reproductionList) throws Exception {
        boolean message = false;
        List<Song> songs = reproductionList.getSongs();
        for (Song song:songs) {
            String queryString = "INSERT INTO ReproductionListSong(idReproductionList, idSong) " +
                    "VALUES('"+ reproductionList.getId() +"', '" + song.getId() +"')";
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                LogError.getLogger().info("Error " + e.getMessage());
                throw new Exception("Error al guardar la informacion en la base de datos");
            }
        }
        return message;
    }

    public boolean deleteSongs(int idSong, int idReproduction) throws Exception {
        boolean message = false;
            String queryString = "DELETE FROM ReproductionListSong " +
                    "WHERE idReproductionList = "+ idReproduction + " AND idSong = " + idSong + "";
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                LogError.getLogger().info("Error " + e.getMessage());
                throw new Exception("Error al borrar la informacion en la base de datos");
            }
        return message;
    }

    public List<ReproductionList> searchReproductionListByUser(int id) throws Exception {
        List<ReproductionList> reproductionLists = new ArrayList<>();
        ReproductionList reproductionList = null;
        String queryString = "SELECT * FROM ReproductionList as rl " +
                "INNER JOIN User as u ON rl.idUser = u.id " +
                "WHERE idUser = " + id + "";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int idRepro = result.getInt("id");
                LocalDate create = result.getDate("create").toLocalDate();
                String name = result.getString("name");
                double score = result.getDouble("score");
                int idUser = result.getInt("idUser");
                User user = new User(idUser);
                reproductionList = new ReproductionList(idRepro,create,name,score,user);
                Song song = null;
                String queryStringGender = "SELECT * FROM ReproductionListSong as rls " +
                        "INNER JOIN Song as s " +
                        "ON rls.idSong = s.id " +
                        "WHERE idReproductionList = " + reproductionList.getId();
                ResultSet resultSong = dataAccess.selectData(queryStringGender);
                try{
                    SongDao songDao = new SongDao();
                    while (resultSong.next())
                    {
                        int idSong = resultSong.getInt("id");
                        song = songDao.searchSongById(idSong);
                        reproductionList.setSongs(song);
                    }
                }
                catch (Exception e){
                    LogError.getLogger().info("Error " + e.getMessage());
                    throw new Exception("Error al obtener la informacion en la base de datos");
                }
                reproductionLists.add(reproductionList);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return reproductionLists;
    }

    public List<Song> searchSongsByReproductionListId(int id) throws Exception {
        List<Song> songs = new ArrayList<>();
        String queryStringGender = "SELECT * FROM ReproductionListSong as rls " +
                "INNER JOIN Song as s " +
                "ON rls.idSong = s.id " +
                "WHERE idReproductionList = " + id;
        ResultSet resultSong = dataAccess.selectData(queryStringGender);
        try{
            SongDao songDao = new SongDao();
            while (resultSong.next())
            {
                int idSong = resultSong.getInt("id");
                Song song = songDao.searchSongById(idSong);
                songs.add(song);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return songs;
    }

    @Override
    public ReproductionList searchReproductionListById(int id) throws Exception {
        ReproductionList reproductionList = null;
        String queryStringGender = "SELECT * FROM ReproductionList " +
                "WHERE id = " + id;
        ResultSet resultSong = dataAccess.selectData(queryStringGender);
        try{
            while (resultSong.next())
            {
                int idRepro = resultSong.getInt("id");
                LocalDate create = resultSong.getDate("create").toLocalDate();
                String name = resultSong.getString("name");
                double score = resultSong.getDouble("score");
                reproductionList = new ReproductionList(id,create,name,score);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return reproductionList;
    }
}
