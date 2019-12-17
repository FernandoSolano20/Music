package com.musica.bl.Song;

import com.musica.bl.Album.Album;
import com.musica.bl.Dao;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Musican.Compositor.Compositor;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;
import com.musica.bl.User.User;
import com.musica.dl.DataAccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SongDao implements ISongDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Song> getAll() {
        List<Song> songs = new ArrayList<>();
        Song song = null;
        String queryString = "SELECT * FROM Song as s " +
                "INNER JOIN Gender as g ON s.idGender = g.id " +
                "INNER JOIN music.user as u ON s.creator = u.id " +
                "INNER JOIN Compositor as c ON s.idCompositor = c.id " +
                "INNER JOIN Artist as a ON s.idArtist = a.id " +
                "INNER JOIN Gender as ag ON a.idGender = ag.id " +
                "INNER JOIN Album as al ON s.idAlbum = al.id ";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                song = createSong(result);
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
        String queryString = "INSERT INTO Song(name, releaseDay, score, creator, idGender, idCompositor, idArtist, idAlbum, songPath, price) " +
                "VALUES('"+ song.getName() +"', '"+ song.getRelease() +"', '"+ song.getScore() +"', '"+ song.getCreator().getId() +
                "', "+ song.getGender().getId() + ", "+ song.getCompositor().getId() +", "+ song.getArtist().getId() +", "+ song.getAlbum().getId() +
                ", '"+ song.getSong() + "', "+ song.getPrice() + ")";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
        }
        return message;
    }

    @Override
    public boolean update(Song song) {
        boolean message = false;
        String queryString = "UPDATE Song SET name='"+ song.getName() +"', releaseDay='"+ song.getRelease() +"', score="+ song.getScore() +", " +
                "idGender="+ song.getGender().getId() + ", idCompositor="+ song.getCompositor().getId() + ", idArtist="+ song.getArtist().getId() +", " +
                "idAlbum="+ song.getAlbum().getId() + ", songPath='"+ song.getSong() + "', price="+ song.getPrice() + "";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
    }

    @Override
    public boolean delete(Song song) {
        boolean message = false;
        String queryString = "DELETE FROM Song " +
                "WHERE id = "+ song.getId();
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            message = false;
        }
        return message;
    }

    @Override
    public Song searchSongByName(String name) {
        Song song = null;
        String queryString = "SELECT * FROM Song as s " +
                "INNER JOIN Gender as g ON s.idGender = g.id " +
                "INNER JOIN music.user as u ON s.creator = u.id " +
                "INNER JOIN Compositor as c ON s.idCompositor = c.id " +
                "INNER JOIN Artist as a ON s.idArtist = a.id " +
                "INNER JOIN Gender as ag ON a.idGender = ag.id " +
                "INNER JOIN Album as al ON s.idAlbum = al.id " +
                "WHERE s.name = '" + name + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                song = createSong(result);
            }
        }
        catch (Exception e){
            song = null;
        }
        return song;
    }

    @Override
    public Song searchSongById(int id) {
        Song song = null;
        String queryString = "SELECT * FROM Song as s " +
                "INNER JOIN Gender as g ON s.idGender = g.id " +
                "INNER JOIN User as u ON s.creator = u.id " +
                "INNER JOIN Compositor as c ON s.idCompositor = c.id " +
                "INNER JOIN Artist as a ON s.idArtist = a.id " +
                "INNER JOIN Gender as ag ON a.idGender = ag.id " +
                "INNER JOIN Album as al ON s.idAlbum = al.id " +
                "WHERE s.id = '" + id + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                song = createSong(result);
            }
        }
        catch (Exception e){
            song = null;
        }
        return song;
    }

    @Override
    public List<Song> searchSongByAlbumId(int id) {
        List<Song> songs = new ArrayList<>();
        Song song = null;
        String queryString = "SELECT * FROM Song as s " +
                "INNER JOIN Gender as g ON s.idGender = g.id " +
                "INNER JOIN music.user as u ON s.creator = u.id " +
                "INNER JOIN Compositor as c ON s.idCompositor = c.id " +
                "INNER JOIN Artist as a ON s.idArtist = a.id " +
                "INNER JOIN Gender as ag ON a.idGender = ag.id " +
                "INNER JOIN Album as al ON s.idAlbum = al.id " +
                "WHERE s.idAlbum = " + id + "";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                song = createSong(result);
                songs.add(song);
            }
        }
        catch (Exception e){
            songs = null;
        }
        return songs;
    }

    private Song createSong(ResultSet result) throws SQLException {
        Song song = null;
        int id = result.getInt("id");
        String name = result.getString("name");
        LocalDate release = result.getDate("releaseDay").toLocalDate();
        int score = result.getInt("score");
        String songPath = result.getString("songPath");
        int price = result.getInt("price");

        int idGender = result.getInt("idGender");
        Gender genderSong = new Gender(idGender,
                result.getString(13),
                result.getString(14));

        int creatorId = result.getInt("creator");
        String nameCreator = result.getString(16);
        String lastName = result.getString(17);
        String email = result.getString(18);
        String pass = result.getString(19);
        String userName = result.getString(22);
        String image = result.getString(23);
        String type = result.getString(24);
        boolean firstTime = result.getBoolean("firstTime");
        String randomPass = result.getString("randomPass");
        User creator = null;
        if(type == "Cliente"){
            int old = result.getInt(20);
            String countryCreator = result.getString(21);
            creator = new Client(creatorId,userName,nameCreator,lastName,email,pass,image,randomPass,old,countryCreator);
        }
        else{
            creator = new Admin(creatorId,userName,nameCreator,lastName,email,pass,image,randomPass);
        }
        creator.setFirstTime(firstTime);

        int idCompositor = result.getInt("idCompositor");
        String nameComp = result.getString(28);
        String lastNameComp = result.getString(29);
        String countComp = result.getString(30);
        int oldComp = result.getInt(31);
        Compositor compositor = new Compositor(idCompositor,nameComp,lastNameComp,countComp,oldComp);

        Gender genderComp = null;
        String queryStringGender = "SELECT * FROM GenderCompositor as gc " +
                "INNER JOIN Gender as g " +
                "ON gc.idGender = g.id " +
                "WHERE idCompositor = " + compositor.getId();
        ResultSet resultGender = dataAccess.selectData(queryStringGender);
        try{
            while (resultGender.next())
            {
                int idGenderComp = resultGender.getInt("id");
                String nameGenderComp = resultGender.getString("name");
                String descriptionComp = resultGender.getString("description");
                genderComp = new Gender(idGenderComp,nameGenderComp,descriptionComp);
                compositor.setGenders(genderComp);
            }
        }
        catch (Exception e){
            genderComp = null;
        }


        int idArtist = result.getInt("idArtist");
        String nameArt = result.getString(33);
        String lastNameArt = result.getString(34);
        LocalDate bornArt = result.getDate(35).toLocalDate();
        Date dead = result.getDate(36);
        LocalDate deadArt = null;
        if(dead != null){
            deadArt = dead.toLocalDate();
        }
        String countArt = result.getString(37);
        int oldArt = result.getInt(38);
        String refeArt = result.getString(39);
        String descripArt = result.getString(40);
        String artName = result.getString(42);
        Gender genderArt = new Gender(result.getInt(43),
                result.getString(44),
                result.getString(45));
        Artist artist = new Artist(idArtist,nameArt,lastNameArt,countArt,oldArt,bornArt,deadArt,refeArt,descripArt,genderArt,artName);

        int idAlbum = result.getInt("idAlbum");
        String nameAlbum = result.getString(47);
        LocalDate releaseDayAlbum = result.getDate(48).toLocalDate();
        String imageAlbum = result.getString(49);
        Album album = new Album(idAlbum,nameAlbum,releaseDayAlbum,imageAlbum);

        Artist artistAlbum = null;
        String queryStringGenderAlbum = "SELECT * FROM AlbumArtist as aa " +
                "INNER JOIN Artist as a ON aa.idArtist = a.id " +
                "INNER JOIN Gender as g ON a.idGender= g.id " +
                "WHERE idAlbum = " + album.getId();
        ResultSet resultArtist = dataAccess.selectData(queryStringGenderAlbum);
        try{
            while (resultArtist.next())
            {
                int idArtistAlbum = resultArtist.getInt("idArtist");
                String nameArtistAlbum = resultArtist.getString("name");
                String lastNameArtistAlbum = resultArtist.getString("lastName");
                LocalDate bornArtistAlbum = resultArtist.getDate("born").toLocalDate();
                Date dateDeadArtistAlbum = resultArtist.getDate("dead");
                LocalDate deadArtistAlbum = null;
                if(dateDeadArtistAlbum != null){
                    deadArtistAlbum = dateDeadArtistAlbum.toLocalDate();
                }
                String countryArtistAlbum = resultArtist.getString("country");
                int oldArtistAlbum = resultArtist.getInt("old");
                String referenceArtistAlbum = resultArtist.getString("reference");
                String descriptionArtistAlbum = resultArtist.getString("description");
                int idGenderArtistAlbum = resultArtist.getInt("idGender");
                String nameGenderArtistAlbum = resultArtist.getString(15);
                String descriptionGenderArtistAlbum = resultArtist.getString(16);
                Gender genderArtistAlbum = new Gender(idGenderArtistAlbum,nameGenderArtistAlbum,descriptionGenderArtistAlbum);
                String artistNameArtistAlbum = resultArtist.getString("artistName");
                artistAlbum = new Artist(idArtistAlbum,nameArtistAlbum,lastNameArtistAlbum,countryArtistAlbum,oldArtistAlbum,bornArtistAlbum,deadArtistAlbum,referenceArtistAlbum,descriptionArtistAlbum,genderArtistAlbum,artistNameArtistAlbum);
                album.setArtists(artistAlbum);
            }
        }
        catch (Exception e){
            artist = null;
        }

        song = new Song(id,name,release,score,creator,songPath,genderSong,compositor,artist,album,price);
        return song;
    }
}
