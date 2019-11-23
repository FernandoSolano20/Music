package com.musica.bl.Song;

import com.musica.bl.Album.Album;
import com.musica.bl.Dao;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Musican.Compositor.Compositor;
import com.musica.bl.User.Client.Client;
import com.musica.bl.User.User;
import com.musica.dl.DataAccess;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SongDao implements ISongDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Song> getAll() {
        List<Song> songs = new ArrayList<>();
        Song song = null;
        String queryString = "SELECT * FROM Song";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                LocalDate release = result.getDate("releaseDay").toLocalDate();
                int score = result.getInt("score");
                int creator = result.getInt("creator");
                String songPath = result.getString("songPath");

                int idGender = result.getInt("idGender");
                Gender genderSong = new Gender(idGender,"","");

                int idCompositor = result.getInt("idCompositor");
                Compositor compositor = new Compositor(idCompositor);

                int idArtist = result.getInt("idArtist");
                Artist artist = new Artist(idArtist);

                int idAlbum = result.getInt("idAlbum");
                Album album = new Album(idAlbum);

                song = new Song(id,name,release,score,new User(creator),songPath,genderSong,compositor,artist,album);
                songs.add(song);
            }
        }
        catch (Exception e){
            songs = null;
        }
        return songs;
    }

    @Override
    public int save(Song song) {
        int message = -1;
        String queryString = "INSERT INTO Song(name, releaseDay, score, creator, idGender, idCompositor, idArtist, idAlbum, songPath) " +
                "VALUES('"+ song.getName() +"', '"+ song.getRelease() +"', '"+ song.getScore() +"', '1"+ /*song.getCreator().getId() +*/
                "', "+ song.getGender().getId() + ", "+ song.getCompositor().getId() +", "+ song.getArtist().getId() +", "+ song.getAlbum().getId() +
                ", '"+ song.getSong() + "')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
        }
        return message;
    }

    @Override
    public boolean update(Song song) {
        return false;
    }

    @Override
    public boolean delete(Song song) {
        return false;
    }

    @Override
    public Song searchSongByName(String name) {
        Song song = null;
        String queryString = "SELECT * FROM Song " +
                "WHERE name = '" + name + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String nameSong = result.getString("name");
                LocalDate release = result.getDate("releaseDay").toLocalDate();
                int score = result.getInt("score");
                int creator = result.getInt("creator");
                String songPath = result.getString("songPath");

                int idGender = result.getInt("idGender");
                Gender genderSong = new Gender(idGender,"","");

                int idCompositor = result.getInt("idCompositor");
                Compositor compositor = new Compositor(idCompositor);

                int idArtist = result.getInt("idArtist");
                Artist artist = new Artist(idArtist);

                int idAlbum = result.getInt("idAlbum");
                Album album = new Album(idAlbum);

                song = new Song(id,nameSong,release,score,new User(creator),songPath,genderSong,compositor,artist,album);
            }
        }
        catch (Exception e){
            song = null;
        }
        return song;
    }
}
