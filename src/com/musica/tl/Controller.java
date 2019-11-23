package com.musica.tl;

import com.musica.bl.Album.Album;
import com.musica.bl.Album.IAlbumDao;
import com.musica.bl.BibliotecaMusical;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Gender.IGenderDao;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Musican.Artist.IArtistDao;
import com.musica.bl.Musican.Compositor.Compositor;
import com.musica.bl.Musican.Compositor.ICompositorDao;
import com.musica.bl.ReproductionList.IReproductionListDao;
import com.musica.bl.ReproductionList.ReproductionList;
import com.musica.bl.Song.ISongDao;
import com.musica.bl.Song.Song;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;
import com.musica.bl.User.Client.IClientDao;
import com.musica.bl.User.IUserDao;
import com.musica.bl.User.User;
import com.musica.bl.factory.DaoFactory;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class Controller {

    private DaoFactory factory;
    private IUserDao userDao;
    private IClientDao clientDao;
    private ISongDao songDao;
    private IReproductionListDao reproductionListDao;
    private IArtistDao artistDao;
    private ICompositorDao compositorDao;
    private IGenderDao genderDao;
    private IAlbumDao albumDao;

    public Controller() {
        factory = DaoFactory.getDaoFactory(1);
        userDao = factory.getUserDao();
        clientDao = factory.getClientDao();
        songDao = factory.getSongDao();
        reproductionListDao = factory.getReproductionListDao();
        artistDao = factory.getArtistDao();
        compositorDao = factory.getCompositorDao();
        genderDao = factory.getGenderDao();
        albumDao = factory.getAlbumDao();
    }

    BibliotecaMusical logic = new BibliotecaMusical();

    public boolean registerClient(int id, String name, String lastName, int old, String country, String email, String pass, String userName, String image){
        Client client = new Client(id,userName,name,lastName,email,pass,image,old,country);
        return userDao.save(client) == 1 ? true : false;
    }

    public List<String> getAllUser(){
        List<String> result = new ArrayList<>();
        List<User> users = userDao.getAll();
        for (User user:users) {
            result.add(user.toString());
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return userDao.isAdminOnDB();
    }

    public boolean registerAdmin(int id, String name, String lastName, String email, String pass, String userName, String image) {
        Admin admin = new Admin(id,userName,name,lastName,email,pass,image);
        return userDao.save(admin) == 1 ? true : false;
    }

    public boolean login(String email, String pass) {
        User user = userDao.login(email,pass);
        User.setActualUser(user);
        return user != null;
    }

    public boolean validateYears(int old){
        return old <= 18;
    }

    public boolean validatePassword(String pass){
        return true;
    }


    /**
     *
     * Song section
     */
    public int registerSong(String name, String gender, String artist, String nameCompositor, String lastNameCompositor, int year, int month, int day, String album, int score, String pathSong) {
        Album album1 = searchAlbumByName(album);
        if(album1 != null){
            Gender gen = searchGenderByName(gender);
            if(gen != null){
                Artist artist1 = searchArtistByArtistName(artist);
                if(artist1 != null){
                    Compositor compositor1 = compositorDao.searchCompositorByNameAndLastName(nameCompositor, lastNameCompositor);
                    if(compositor1 != null){
                        Song song = new Song(name,gen,artist1,compositor1, LocalDate.of(year,month,day),album1,score,pathSong,User.getActualUser());
                        int response  = songDao.save(song) != 1 ? 1 : 0;
                        return response;
                    }
                    return -1;
                }
                return -2;
            }
            return -3;
        }
        return -4;
    }

    private Song searchSongByName(String song) {
        return songDao.searchSongByName(song);
    }


    /***
     * Gender Section
     */
    public boolean registerGender(String name, String description) {
        Gender gender = new Gender(name,description);
        return genderDao.save(gender) != -1 ? true : false;
    }

    public Gender searchGenderByName(String name){
        return genderDao.searchGenderByName(name);
    }

    public List<String> getAllGender(){
        List<String> result = new ArrayList<>();
        List<Gender> genders = genderDao.getAll();
        for (Gender gender:genders) {
            result.add(gender.toString());
        }
        return result;
    }


    /**
     *
     * Artist section
     */
    public boolean registerArtist(String name, String lastName, String country, LocalDate dateBorn, LocalDate dateDead, String reference, String description, String gender, String artistName) {
        Gender gen = searchGenderByName(gender);
        if(gen != null){
            LocalDate actual = dateDead != null ? dateDead : LocalDate.now();
            int old = (int)YEARS.between(dateBorn, actual);
            Artist artist = new Artist(name,lastName,country,old,dateBorn,dateDead,reference,description,gen,artistName);
            return artistDao.save(artist) != -1 ? true : false;
        }
        return false;
    }

    private Artist searchArtistByArtistName(String artist) {
        return artistDao.searchArtistByArtistName(artist);
    }


    /**
     *Compositor Section
     */
    public boolean registerCompositor(String name, String lastName, String country, int old, String[] genders) {
        Compositor compositor = new Compositor(name,lastName,country,old);
        for (String gender:genders) {
            Gender gen = searchGenderByName(gender);
            if(gen != null){
                compositor.setGenders(gen);
            }
        }
        int id = compositorDao.save(compositor);
        if(id != -1){
            compositor.setId(id);
            return compositorDao.saveGenders(compositor);
        }
        return false;
    }


    /**
     *
     * Album section
     */
    public boolean registerAlbum(String name, LocalDate release, String image, String[] artists) {
        Album album = new Album(name,release,image);
        for (String artist:artists) {
            Artist art = artistDao.searchArtistByArtistName(artist);
            if(art != null){
                album.setArtists(art);
            }
        }
        int id = albumDao.save(album);
        if(id != -1){
            album.setId(id);
            return albumDao.saveArtists(album);
        }
        return false;
    }

    private Album searchAlbumByName(String album) {
        return albumDao.searchAlbumByName(album);
    }


    /**
     *
     * ReproductionList section
     */
    public boolean registerReproductionList(String name, int year, int month, int day, String[] songs) {
        ReproductionList reproductionList = new ReproductionList(LocalDate.of(year,month,day),name,User.getActualUser());
        for (String song:songs) {
            Song son = searchSongByName(song);
            if(son != null){
                reproductionList.setSongs(son);
            }
        }
        reproductionList.setScore();
        int id = reproductionListDao.save(reproductionList);
        if(id != -1){
            return reproductionListDao.saveSongs(reproductionList);
        }
        return false;
    }


    /**
     *
     * Catalog Section
     */
    public boolean addSongToCatalog(String name) {
        Song song = songDao.searchSongByName(name);
        if(song != null)
           return clientDao.addSongToCatalog(User.getActualUser(),song);
        return false;
    }
}
