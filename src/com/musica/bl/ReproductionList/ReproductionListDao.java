package com.musica.bl.ReproductionList;

import com.musica.bl.Dao;
import com.musica.bl.Song.Song;
import com.musica.dl.DataAccess;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReproductionListDao implements Dao<ReproductionList> {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<ReproductionList> getAll() {
        List<ReproductionList> reproductionLists = new ArrayList<>();
        /*ReproductionList reproductionList = null;
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
                int idGender = result.getInt("idGender");
                Gender genderSong = new Gender(idGender,"","");

                song = new Song(id,name,release,score,creator,songPath,genderSong,compositor,artist,album);
                songs.add(song);
            }
        }
        catch (Exception e){
            songs = null;
        }*/
        return reproductionLists;
    }

    @Override
    public int save(ReproductionList reproductionList) {
        int message = -1;
        String queryString = "INSERT INTO music.reproductionlist(reproductionlist.create, reproductionlist.name, reproductionlist.score, reproductionlist.idUser) " +
                "VALUES('"+ reproductionList.getCreate() +"', '"+ reproductionList.getName() +"', "+ (int)reproductionList.getScore() +
                ", 117840834"+ /*reproductionList.getUser().getId() + */")";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
        }
        return message;
    }

    @Override
    public boolean update(ReproductionList reproductionList) {
        return false;
    }

    @Override
    public boolean delete(ReproductionList reproductionList) {
        return false;
    }

    public boolean saveSongs(ReproductionList reproductionList){
        boolean message = false;
        List<Song> songs = reproductionList.getSongs();
        for (Song song:songs) {
            String queryString = "INSERT INTO ReproductionListSong(idReproductionList, idSong) " +
                    "VALUES('"+ reproductionList.getId() +"', '" + song.getId() +"')";
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                message = false;
            }
        }
        return message;
    }
}
