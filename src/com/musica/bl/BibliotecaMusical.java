package com.musica.bl;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaMusical {/*
    private static User actualUserId;
    private List<Gender> genders = new GenderDao().getAll();
    private List<User> users = new UserDao().getAll();
    private List<Country> countries =  new ArrayList<>();
    private List<Compositor> compositors = new CompositorDao().getAll();
    private List<Artist> artists = new ArtistDao().getAll();
    private List<Album> albums = new AlbumDao().getAll();
    //private Lit
    private List<Song> songs = getAllSongs();
    private List<ReproductionList> reproductionLists = getReproductionLists();

    private Dao dao;

    public User getActualUser(){
        return actualUserId;
    }

    public boolean registerUser(User user){
        dao = new UserDao();
        boolean response = dao.save(user) == 1 ? true : false;
        if(response && user instanceof Client){
            Client client = (Client) user;
            List<Song> songs = client.getCatalog();
            for (Song song: songs) {
                client.replaceSong(song);
            }
        }
        return response;
    }

    public List<String> getAllUser(){
        dao = new UserDao();
        ArrayList<String> result = new ArrayList<>();
        List<User> users = dao.getAll();
        if(users.size() > 0){
            for (User user:users) {
                result.add(user.toString());
            }
        }
        else {
            result.add("No hay usuarios");
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return new UserDao().isAdminOnDB();
    }

    public boolean login(String email, String pass) {
        UserDao dao = new UserDao();
         User user = dao.login(email,pass);
         if (user != null){
             BibliotecaMusical.actualUserId = user;
             return true;
         }
         return false;
    }

    public boolean validateYears(int old) {
        return old <= 18;
    }

    public boolean validatePassword(String pass) {
        return true;
    }

    public boolean registerSong(Song song) {
        dao = new SongDao();
        return dao.save(song) == 0 ? false : true;
    }

    public boolean registerGender(Gender gender) {
        dao = new GenderDao();
        boolean response = dao.save(gender) == 0 ? false : true;
        if(response == true){
            genders = dao.getAll();
        }
        return response;
    }

    public boolean registerArtist(Artist artist) {
        dao = new ArtistDao();
        boolean response =  dao.save(artist) == 0 ? false : true;
        if(response == true){
            genders = dao.getAll();
        }
        return response;
    }

    public Gender searchGenderByName(String name){
        Gender gen = null;
        for (Gender gender:genders) {
            if (gender.getName().equals(name)){
                gen = gender;
                break;
            }
        }
        return gen;
    }

    public Gender searchGenderById(int id){
        Gender gen = null;
        for (Gender gender:genders) {
            if (gender.getId() == id){
                gen = gender;
                break;
            }
        }
        return gen;
    }

    public Country searchCountryByName(String name){
        countries.add(new Country("Country"));
        Country coun = null;
        for (Country country:countries) {
            if (country.getName().equals(name)){
                coun = country;
                break;
            }
        }
        return coun;
    }

    public boolean registerCompositor(Compositor compositor) {
        CompositorDao dao = new CompositorDao();
        boolean response = false;
        int id = dao.save(compositor);
        compositor.setId(id);
        response = id == 0 ? false : true;
        if (response){
            response = dao.saveGenders(compositor);
        }
        else {
            response = false;
        }
        compositors = dao.getAll();
        return response;
    }

    public Compositor searchCompositorByNameAndLastName(String name){
        Compositor comp = null;
        for (Compositor compositor:compositors) {
            if (name.equals(compositor.getName() + " " + compositor.getLastName())){
                comp = compositor;
                break;
            }
        }
        return comp;
    }

    public Compositor searchCompositorById(int id){
        Compositor comp = null;
        for (Compositor compositor:compositors) {
            if (compositor.getId() == id){
                comp = compositor;
                break;
            }
        }
        return comp;
    }

    public int countGenders(){
        return genders.size();
    }

    /*public int countContries(){
        return countries.size();
    }*/
/*
    public int countCompositors(){
        return compositors.size();
    }

    public int countArtists(){
        return artists.size();
    }

    public Artist searchArtistByArtistName(String artist) {
        Artist art = null;
        for (Artist artist1:artists) {
            if (artist1.getArtist().equals(artist)){
                art = artist1;
                break;
            }
        }
        return art;
    }

    public Artist searchArtistById(int id) {
        Artist art = null;
        for (Artist artist1:artists) {
            if (artist1.getId() == id){
                art = artist1;
                break;
            }
        }
        return art;
    }

    public boolean registerAlbum(Album album) {
        AlbumDao dao = new AlbumDao();
        boolean response = false;
        int id = dao.save(album);
        album.setId(id);
        response = id == 0 ? false : true;
        if (response){
            response = dao.saveArtists(album);
        }
        else {
            response = false;
        }
        albums = dao.getAll();
        return response;
    }

    public Album searchAlbumByName(String name){
        Album album = null;
        for (Album album1:albums) {
            if (name.equals(album1.getName())){
                album = album1;
                break;
            }
        }
        return album;
    }

    public Album searchAlbumById(int id){
        Album album = null;
        for (Album album1:albums) {
            if (album1.getId() == id){
                album = album1;
                break;
            }
        }
        return album;
    }

    private List<Song> getAllSongs() {
        dao = new SongDao();
        List<Song> songs = dao.getAll();
        for (Song song:songs) {
            song.setGender(searchGenderById(song.getGender().getId()));
            song.setCompositor(searchCompositorById(song.getCompositor().getId()));
            song.setArtist(searchArtistById(song.getArtist().getId()));
            song.setAlbum(searchAlbumById(song.getAlbum().getId()));
        }
        return songs;
    }

    public Song searchSongByName(String name){
        Song song = null;
        for (Song song1:songs) {
            if (name.equals(song1.getName())){
                song = song1;
                break;
            }
        }
        return song;
    }

    public Song searchSongById(int id){
        Song song = null;
        for (Song song1:songs) {
            if (song1.getId() == id){
                song = song1;
                break;
            }
        }
        return song;
    }

    public User searchUserById(int id){
        User u = null;
        for (User user:users) {
            if (user.getId() == id){
                u = user;
                break;
            }
        }
        return u;
    }

    public boolean registerReproductionList(ReproductionList reproductionList) {
        ReproductionListDao dao = new ReproductionListDao();
        boolean response = false;
        int id = dao.save(reproductionList);
        reproductionList.setId(id);
        response = id == 0 ? false : true;
        if (response){
            response = dao.saveSongs(reproductionList);
        }
        else {
            response = false;
        }
        reproductionLists = dao.getAll();
        return response;
    }

    private List<ReproductionList> getReproductionLists() {
        dao = new ReproductionListDao();
        List<ReproductionList> reproductionLists = dao.getAll();
        for (ReproductionList reproList:reproductionLists) {
            reproList.setUser(searchUserById(reproList.getUser().getId()));
            List<Song> songs = reproList.getSongs();
            for (Song song:songs){
                Song s = searchSongById(song.getId());
                reproList.replaceSong(s);
            }
        }
        return reproductionLists;
    }

    public boolean addSongToCatalog(Song song, User actualUser) {
        ClientDao dao = new ClientDao();
        boolean response = dao.save(actualUser, song) == 1 ? true : false;
        if(response){
            users = new UserDao().getAll();
        }
        return response;
    }*/
}
