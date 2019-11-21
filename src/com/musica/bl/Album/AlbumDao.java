package com.musica.bl.Album;

import com.musica.bl.Country.Country;
import com.musica.bl.Dao;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.dl.DataAccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao implements Dao<Album> {
    private DataAccess dataAccess = new DataAccess();
    @Override
    public List<Album> getAll() {
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
                        int idArtist = resultArtist.getInt("id");
                        String nameArtist = resultArtist.getString("name");
                        String lastName = resultArtist.getString("lastName");
                        LocalDate born = resultArtist.getDate("born").toLocalDate();
                        Date dateDead = resultArtist.getDate("dead");
                        LocalDate dead = null;
                        if(dateDead != null){
                            dead = dateDead.toLocalDate();
                        }
                        String coun = resultArtist.getString("country");
                        Country country = new Country(coun);
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
                    artist = null;
                }
                albums.add(album);
            }
        }
        catch (Exception e){
            albums = null;
        }
        return albums;
    }

    @Override
    public int save(Album album) {
        int message = -1;
        String queryString = "INSERT INTO Album(name, releaseDate, image) " +
                "VALUES('"+ album.getName() + "', '" + album.getReleaseDate() + "', '" + album.getImage()  + "')";
        try {
            message = dataAccess.insertIntoData(queryString);
        } catch (Exception e) {
            message = -1;
        }
        return message;
    }

    @Override
    public boolean update(Album album) {
        return false;
    }

    @Override
    public boolean delete(Album album) {
        return false;
    }

    public boolean saveArtists(Album album){
        boolean message = false;
        ArrayList<Artist> artists = album.getArtists();
        for (Artist artist:artists) {
            String queryString = "INSERT INTO AlbumArtist(idAlbum, idArtist) " +
                    "VALUES('"+ album.getId() +"', '" + artist.getId() +"')";
            try {
                message = dataAccess.insertData(queryString);
            } catch (Exception e) {
                message = false;
            }
        }
        return message;
    }
}