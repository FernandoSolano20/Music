package com.musica.bl.Album;

import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Song.SongDao;
import com.musica.dl.DataAccess;
import com.musica.dl.LogError;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao implements IAlbumDao {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Album> getAll() throws Exception {
        List<Album> albums = new ArrayList<>();
        Album album = null;
        String queryString = "SELECT * FROM Album";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                LocalDate release = result.getDate("releaseDate").toLocalDate();
                String image = result.getString("image");
                album = new Album(id,name,release,image);
                Artist artist = null;
                String queryStringGender = "SELECT * FROM AlbumArtist as aa " +
                        "INNER JOIN Artist as a ON aa.idArtist = a.id " +
                        "INNER JOIN Gender as g ON a.idGender= g.id " +
                        "WHERE idAlbum = " + album.getId();
                ResultSet resultArtist = dataAccess.selectData(queryStringGender);
                try{
                    while (resultArtist.next())
                    {
                        int idArtist = resultArtist.getInt("idArtist");
                        String nameArtist = resultArtist.getString("name");
                        String lastName = resultArtist.getString("lastName");
                        LocalDate born = resultArtist.getDate("born").toLocalDate();
                        Date dateDead = resultArtist.getDate("dead");
                        LocalDate dead = null;
                        if(dateDead != null){
                            dead = dateDead.toLocalDate();
                        }
                        String country = resultArtist.getString("country");
                        int old = resultArtist.getInt("old");
                        String reference = resultArtist.getString("reference");
                        String description = resultArtist.getString("description");
                        int idGender = resultArtist.getInt("idGender");
                        String nameGender = resultArtist.getString(15);
                        String descriptionGender = resultArtist.getString(16);
                        Gender gender = new Gender(idGender,nameGender,descriptionGender);
                        String artistName = resultArtist.getString("artistName");
                        artist = new Artist(id,name,lastName,country,old,born,dead,reference,description,gender,artistName);
                        album.setArtists(artist);
                    }
                }
                catch (Exception e){
                    LogError.getLogger().info("Error " + e.getMessage());
                    throw new Exception("Error al obtener la informacion en la base de datos");
                }
                SongDao songDao = new SongDao();
                album.setSongs(songDao.searchSongByAlbumId(album.getId()));
                albums.add(album);
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return albums;
    }

    @Override
    public int save(Album album) throws Exception {
        int message = -1;
        String queryString = "INSERT INTO Album(name, releaseDate, image) " +
                "VALUES('"+ album.getName() + "', '" + album.getReleaseDate() + "', '" + album.getImage()  + "')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al guardar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean update(Album album) throws Exception {
        boolean message = false;
        String queryString = "UPDATE Album SET name= '"+ album.getName() +"', releaseDate= '" + album.getReleaseDate() + "', image= '" + album.getImage() + "' " +
                "WHERE id= " + album.getId() + "";
        try {
            message = dataAccess.insertData(queryString);
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al actualizar la informacion en la base de datos");
        }
        return message;
    }

    @Override
    public boolean delete(Album album) throws Exception {
        boolean message = false;
        String queryString = "DELETE FROM AlbumArtist " +
                "WHERE idAlbum = "+ album.getId();
        try {
            message = dataAccess.insertData(queryString);
            queryString = "DELETE FROM Album " +
                    "WHERE id = "+ album.getId();
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                LogError.getLogger().info("Error " + e.getMessage());
                throw new Exception("Error al borrar la informacion en la base de datos");
            }
        } catch (Exception e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al borrar la informacion en la base de datos");
        }
        return message;
    }

    public boolean saveArtists(Album album) throws Exception {
        boolean message = false;
        ArrayList<Artist> artists = album.getArtists();
        for (Artist artist:artists) {
            String queryString = "INSERT INTO AlbumArtist(idAlbum, idArtist) " +
                    "VALUES('"+ album.getId() +"', '" + artist.getId() +"')";
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                LogError.getLogger().info("Error " + e.getMessage());
                throw new Exception("Error al guardar la informacion en la base de datos");
            }
        }
        return message;
    }

    @Override
    public Album searchAlbumByName(String name) throws Exception {
        Album album = null;
        String queryString = "SELECT * FROM Album " +
                "WHERE name = '" + name + "'";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int id = result.getInt("id");
                String albumName = result.getString("name");
                LocalDate release = result.getDate("releaseDate").toLocalDate();
                String image = result.getString("image");
                album = new Album(id,albumName,release,image);
                SongDao songDao = new SongDao();
                album.setSongs(songDao.searchSongByAlbumId(album.getId()));
                Artist artist = null;
                String queryStringGender = "SELECT * FROM AlbumArtist as aa " +
                        "INNER JOIN Artist as a ON aa.idArtist = a.id " +
                        "INNER JOIN Gender as g ON a.idGender= g.id " +
                        "WHERE idAlbum = " + album.getId();
                ResultSet resultArtist = dataAccess.selectData(queryStringGender);
                try{
                    while (resultArtist.next())
                    {
                        int idArtist = resultArtist.getInt("idArtist");
                        String nameArtist = resultArtist.getString("name");
                        String lastName = resultArtist.getString("lastName");
                        LocalDate born = resultArtist.getDate("born").toLocalDate();
                        Date dateDead = resultArtist.getDate("dead");
                        LocalDate dead = null;
                        if(dateDead != null){
                            dead = dateDead.toLocalDate();
                        }
                        String country = resultArtist.getString("country");
                        int old = resultArtist.getInt("old");
                        String reference = resultArtist.getString("reference");
                        String description = resultArtist.getString("description");
                        int idGender = resultArtist.getInt("idGender");
                        String nameGender = resultArtist.getString(15);
                        String descriptionGender = resultArtist.getString(16);
                        Gender gender = new Gender(idGender,nameGender,descriptionGender);
                        String artistName = resultArtist.getString("artistName");
                        artist = new Artist(id,name,lastName,country,old,born,dead,reference,description,gender,artistName);
                        album.setArtists(artist);
                    }
                }
                catch (Exception e){
                    LogError.getLogger().info("Error " + e.getMessage());
                    throw new Exception("Error al obtener la informacion en la base de datos");
                }
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return album;
    }

    @Override
    public Album searchAlbumById(int id) throws Exception {
        Album album = null;
        String queryString = "SELECT * FROM Album " +
                "WHERE id = " + id + "";
        ResultSet result = dataAccess.selectData(queryString);
        try{
            while (result.next())
            {
                int idAlbum = result.getInt("id");
                String albumName = result.getString("name");
                LocalDate release = result.getDate("releaseDate").toLocalDate();
                String image = result.getString("image");
                album = new Album(idAlbum,albumName,release,image);
                SongDao songDao = new SongDao();
                album.setSongs(songDao.searchSongByAlbumId(album.getId()));
                Artist artist = null;
                String queryStringGender = "SELECT * FROM AlbumArtist as aa " +
                        "INNER JOIN Artist as a ON aa.idArtist = a.id " +
                        "INNER JOIN Gender as g ON a.idGender= g.id " +
                        "WHERE idAlbum = " + album.getId();
                ResultSet resultArtist = dataAccess.selectData(queryStringGender);
                try{
                    while (resultArtist.next())
                    {
                        int idArtist = resultArtist.getInt("idArtist");
                        String nameArtist = resultArtist.getString("name");
                        String lastName = resultArtist.getString("lastName");
                        LocalDate born = resultArtist.getDate("born").toLocalDate();
                        Date dateDead = resultArtist.getDate("dead");
                        LocalDate dead = null;
                        if(dateDead != null){
                            dead = dateDead.toLocalDate();
                        }
                        String country = resultArtist.getString("country");
                        int old = resultArtist.getInt("old");
                        String reference = resultArtist.getString("reference");
                        String description = resultArtist.getString("description");
                        int idGender = resultArtist.getInt("idGender");
                        String nameGender = resultArtist.getString(15);
                        String descriptionGender = resultArtist.getString(16);
                        Gender gender = new Gender(idGender,nameGender,descriptionGender);
                        String artistName = resultArtist.getString("artistName");
                        artist = new Artist(idArtist,nameArtist,lastName,country,old,born,dead,reference,description,gender,artistName);
                        album.setArtists(artist);
                    }
                }
                catch (Exception e){
                    LogError.getLogger().info("Error " + e.getMessage());
                    throw new Exception("Error al obtener la informacion en la base de datos");
                }
            }
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al obtener la informacion en la base de datos");
        }
        return album;
    }
}
