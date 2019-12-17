package com.musica.tl;

import com.musica.bl.Album.Album;
import com.musica.bl.Album.IAlbumDao;
import com.musica.bl.BibliotecaMusical;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Gender.IGenderDao;
import com.musica.bl.Mail.SendMail;
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
import sun.java2d.loops.GeneralRenderer;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.time.temporal.ChronoUnit.YEARS;

public class Controller {
    private Random rnd = new Random();
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

    public boolean registerClient(int id, String name, String lastName, int old, String country, String email, String pass, String userName, String image) throws Exception {
        String randomNum = ""+(100000 + rnd.nextInt(900000));
        Client client = new Client(id,userName,name,lastName,email,randomNum,image,randomNum,old,country);
        client.setFirstTime(true);
        if (userDao.save(client) == 1){
            SendMail.sendMail(client.getEmail(), "Codigo Verficacion", "Su codigo de verificacion es: "+ client.getRandomPass() +"");
            return true;
        }
        return false;
    }

    public List<String> getAllUser() throws Exception {
        List<String> result = new ArrayList<>();
        List<User> users = userDao.getAll();
        for (User user:users) {
            result.add(user.toString());
        }
        return result;
    }

    public boolean isAdminOnDB() throws Exception {
        return userDao.isAdminOnDB();
    }

    public boolean registerAdmin(int id, String name, String lastName, String email, String pass, String userName, String image) throws Exception {
        String randomNum = ""+(100000 + rnd.nextInt(900000));
        Admin admin = new Admin(id,userName,name,lastName,email,randomNum,image,randomNum);
        admin.setFirstTime(true);
        if (userDao.save(admin) == 1){
            SendMail.sendMail(admin.getEmail(), "Codigo Verficacion", "Su codigo de verificacion es: "+ admin.getRandomPass() +"");
            return true;
        }
        return false;
    }

    public String login(String email, String pass) throws Exception {
        User user = userDao.login(email,pass);
        User.setActualUser(user);
        String response = null;
        if (user != null){
            response = user.getType();
        }
        return response;
    }

    public boolean isFirstTime() throws Exception {
        User user = userDao.getUserById(User.getActualUser().getId());
        return user.isFirstTime();
    }

    public boolean validateYears(int old){
        return old >= 18;
    }

    public boolean validatePassword(String pass){
        return true;
    }

    public boolean updateUser(String pass, boolean firstTime) throws Exception {
        User user = User.getActualUser();
        user.setPass(pass);
        user.setFirstTime(firstTime);
        boolean response = false;
        response = userDao.update(user);
        return response;
    }

    public boolean forgetPass(String email) throws Exception {
        String randomNum = ""+(100000 + rnd.nextInt(900000));
        User user = userDao.getUserByEmail(email);
        if(user != null){
            user.setRandomPass(randomNum);
            user.setFirstTime(true);
            user.setPass(randomNum);
            boolean response = userDao.update(user);
            SendMail.sendMail(user.getEmail(),"Olvido la contraseña","Su nueva contraseña es: " + randomNum);
            return response;
        }
        return false;
    }
    /**
     *
     * Song section
     */
    public int registerSong(String name, String gender, String artist, String nameCompositor, int year, int month, int day, String album, int score, String pathSong, int price) throws Exception {
        Album album1 = searchAlbumByName(album);
        if(album1 != null){
            Gender gen = searchGenderByName(gender);
            if(gen != null){
                Artist artist1 = searchArtistByArtistName(artist);
                if(artist1 != null){
                    Compositor compositor1 = compositorDao.searchCompositorByNameAndLastName(nameCompositor);
                    if(compositor1 != null){
                        Song song = new Song(name,gen,artist1,compositor1, LocalDate.of(year,month,day),album1,score,pathSong,User.getActualUser(),price);
                        int response  = songDao.save(song);
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

    public int updateSong(int id, String name, String gender, String artist, String nameCompositor, int year, int month, int day, String album, int score, String pathSong, int price) throws Exception {
        Album album1 = searchAlbumByName(album);
        if(album1 != null){
            Gender gen = searchGenderByName(gender);
            if(gen != null){
                Artist artist1 = searchArtistByArtistName(artist);
                if(artist1 != null){
                    Compositor compositor1 = compositorDao.searchCompositorByNameAndLastName(nameCompositor);
                    if(compositor1 != null){
                        Song song = new Song(id,name,gen,artist1,compositor1, LocalDate.of(year,month,day),album1,score,pathSong,price);
                        int response  = songDao.update(song)? 1 : 0;
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

    private Song searchSongByName(String song) throws Exception {
        return songDao.searchSongByName(song);
    }

    private Song searchSongById(int id) throws Exception {
        return songDao.searchSongById(id);
    }

    public String getSongById(int id) throws Exception {
        return searchSongById(id).toString();
    }

    private List<Song> getSongs() throws Exception {
        return songDao.getAll();
    }

    public List<String> getAllSongs() throws Exception {
        List<String> result = new ArrayList<>();
        List<Song> songs = getSongs();
        for (Song item:songs) {
            result.add(item.toString());
        }
        return result;
    }

    public List<String> searchSongByAlbumId(int idAlbum) throws Exception {
        List<String> result = new ArrayList<>();
        List<Song> songs = songDao.searchSongByAlbumId(idAlbum);
        for (Song item:songs) {
            result.add(item.toString());
        }
        return result;
    }

    public boolean deleteSong(int id) throws Exception {
        Song song = searchSongById(id);
        boolean response = false;
        if(song != null){
            response = songDao.delete(song);
        }
        return response;
    }


    /***
     * Gender Section
     */
    public boolean registerGender(String name, String description) throws Exception {
        Gender gender = new Gender(name,description);
        return genderDao.save(gender) != -1 ? true : false;
    }

    public Gender searchGenderByName(String name) throws Exception {
        return genderDao.searchGenderByName(name);
    }

    public List<String> getAllGender() throws Exception {
        List<String> result = new ArrayList<>();
        List<Gender> genders = genderDao.getAll();
        for (Gender gender:genders) {
            result.add(gender.toString());
        }
        return result;
    }

    private Gender searchGenderById(int id) throws Exception {
        return genderDao.searchGenderById(id);
    }

    public String getGenderById(int id) throws Exception {
        Gender gender = searchGenderById(id);
        if (gender != null){
            return gender.toString();
        }
        return "";
    }

    public boolean updateGender(int id, String name, String description) throws Exception {
        Gender gender = new Gender(id,name,description);
        boolean response = false;
        response = genderDao.update(gender);
        return response;
    }

    public boolean deleteGender(int id) throws Exception {
        Gender gender = searchGenderById(id);
        boolean response = false;
        if(gender != null){
            response = genderDao.delete(gender);
        }
        return response;
    }


    /**
     *
     * Artist section
     */
    public boolean registerArtist(String name, String lastName, String country, LocalDate dateBorn, LocalDate dateDead, String reference, String description, String gender, String artistName) throws Exception {
        Gender gen = searchGenderByName(gender);
        if(gen != null){
            LocalDate actual = dateDead != null ? dateDead : LocalDate.now();
            int old = (int)YEARS.between(dateBorn, actual);
            Artist artist = new Artist(name,lastName,country,old,dateBorn,dateDead,reference,description,gen,artistName);
            return artistDao.save(artist) != -1 ? true : false;
        }
        return false;
    }

    private Artist searchArtistByArtistName(String artist) throws Exception {
        return artistDao.searchArtistByArtistName(artist);
    }

    public List<String> getAllArtists() throws Exception {
        List<String> result = new ArrayList<>();
        List<Artist> artists = artistDao.getAll();
        for (Artist artist:artists) {
            result.add(artist.toString());
        }
        return result;
    }

    public boolean updateArtist(int id, String name, String lastName, String country, LocalDate dateBorn, LocalDate dateDead, String reference, String description, String gender, String artistName) throws Exception {
        Gender gen = searchGenderByName(gender);
        if(gen != null) {
            LocalDate actual = dateDead != null ? dateDead : LocalDate.now();
            int old = (int)YEARS.between(dateBorn, actual);
            Artist artist = new Artist(id,name,lastName,country,old,dateBorn,dateDead,reference,description,gen,artistName);
            return artistDao.update(artist);
        }
        return false;
    }

    public boolean deleteArtist(int id) throws Exception {
        Artist artist = searchArtistById(id);
        boolean response = false;
        if(artist != null){
            response = artistDao.delete(artist);
        }
        return response;
    }

    public String getArtistById(int id) throws Exception {
        Artist artist = searchArtistById(id);
        if (artist != null){
            return artist.toString();
        }
        return "";
    }

    private Artist searchArtistById(int id) throws Exception {
        return artistDao.searchArtistById(id);
    }


    /**
     *Compositor Section
     */
    public boolean registerCompositor(String name, String lastName, String country, int old, String[] genders) throws Exception {
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

    public List<String> getAllCompositor() throws Exception {
        List<String> result = new ArrayList<>();
        List<Compositor> compositors = compositorDao.getAll();
        for (Compositor compositor:compositors) {
            result.add(compositor.toString());
        }
        return result;
    }

    public boolean updateCompositor(int id, String name, String lastName, String country, int old) throws Exception {
        Compositor compositor = new Compositor(id,name,lastName,country,old);
        boolean response = false;
        response = compositorDao.update(compositor);
        return response;
    }

    public boolean deleteCompositor(int id) throws Exception {
        Compositor compositor = searchCompositorById(id);
        boolean response = false;
        if(compositor != null){
            response = compositorDao.delete(compositor);
        }
        return response;
    }

    public String getCompositorById(int id) throws Exception {
        Compositor compositor = searchCompositorById(id);
        if (compositor != null){
            return compositor.toString();
        }
        return "";
    }

    private Compositor searchCompositorById(int id) throws Exception {
        return compositorDao.searchCompositorById(id);
    }

    /**
     *
     * Album section
     */
    public boolean registerAlbum(String name, LocalDate release, String image, String[] artists) throws Exception {
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

    private Album searchAlbumByName(String album) throws Exception {
        return albumDao.searchAlbumByName(album);
    }

    public List<String> getAllAlbum() throws Exception {
        List<String> result = new ArrayList<>();
        List<Album> albums = albumDao.getAll();
        for (Album album:albums) {
            result.add(album.toString());
        }
        return result;
    }

    public boolean updateAlbum(int id, String name, LocalDate release, String image) throws Exception {
        Album album = new Album(id,name,release,image);
        boolean response = false;
        response = albumDao.update(album);
        return response;
    }

    public boolean deleteAlbum(int id) throws Exception {
        Album album = searchAlbumById(id);
        boolean response = false;
        if(album != null){
            response = albumDao.delete(album);
        }
        return response;
    }

    public String getAlbumById(int id) throws Exception {
        Album album = searchAlbumById(id);
        if (album != null){
            return album.toString();
        }
        return "";
    }

    private Album searchAlbumById(int id) throws Exception {
        return albumDao.searchAlbumById(id);
    }


    /**
     *
     * ReproductionList section
     */
    public boolean registerReproductionList(String name, int year, int month, int day, String[] songs) throws Exception {
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
            reproductionList.setId(id);
            return reproductionListDao.saveSongs(reproductionList);
        }
        return false;
    }

    public boolean saveSongReproductionList(int id, String name) throws Exception {
        ReproductionList reproductionList = new ReproductionList(id);
        Song song = searchSongByName(name);
        if(song != null){
            reproductionList.setSongs(song);
            return reproductionListDao.saveSongs(reproductionList);
        }
        return false;
    }

    public List<String> searchReproductionListByUser() throws Exception {
        List<ReproductionList> rls = reproductionListDao.searchReproductionListByUser(User.getActualUser().getId());
        List<String> result = new ArrayList<>();
        for (int i = 0; i < rls.size(); i++) {
            result.add(rls.get(i).toString());
        }
        return result;
    }

    public List<String> searchSongsByReproductionListId(int id) throws Exception {
        List<Song> songs = reproductionListDao.searchSongsByReproductionListId(id);
        List<String> result = new ArrayList<>();
        for (Song song:songs) {
            result.add(song.toString());
        }
        return result;
    }

    public String searchReproductionListById(int id) throws Exception {
        ReproductionList reproductionList = reproductionListDao.searchReproductionListById(id);
        String result = "";
        if(reproductionList != null){
            return  reproductionList.toString();
        }
        return result;
    }

    public boolean deleteSongsReproductionList(int idReproduction, int idSong) throws Exception {
        boolean response = reproductionListDao.deleteSongs(idReproduction,idSong);
        return response;
    }

    public boolean updateReproductionListId(int id, String name) throws Exception {
        ReproductionList reproductionList = new ReproductionList();
        reproductionList.setId(id);
        reproductionList.setName(name);
        return reproductionListDao.update(reproductionList);
    }

    public boolean deleteReproductionListId(int id) throws Exception {
        ReproductionList reproductionList = new ReproductionList(id);
        return reproductionListDao.delete(reproductionList);
    }
    /**
     *
     * Catalog Section
     */
    public boolean addSongToCatalog(String name) throws Exception {
        Song song = songDao.searchSongByName(name);
        if(song != null)
           return clientDao.addSongToCatalog(User.getActualUser(),song);
        return false;
    }

    public String userType(){
        return User.getActualUser().getType();
    }

    public boolean buy(int idSong) throws Exception {
        Song song = searchSongById(idSong);
        if(song != null){
            return clientDao.addSongToCatalog(User.getActualUser(), song);
        }
        return false;
    }

    public List<String> getCatalog() throws Exception {
        User user = userDao.getUserById(User.getActualUser().getId());
        List<Song> catalog = user != null ? ((Client)user).getCatalog() : new ArrayList<>();
        List<String> response = new ArrayList<>();
        for (Song song:catalog) {
            response.add(song.toString());
        }
        return response;
    }

    public List<String> showMediaUser() throws Exception {
        List<String> catalog = getCatalog();
        List<String> reproductionList = searchReproductionListByUser();
        List<String> result = new ArrayList<>();
        for (String item : catalog) {
            result.add("Song,"+item);
        }
        for (String item:reproductionList) {
            result.add("List,"+item);
        }
        return result;
    }
}
