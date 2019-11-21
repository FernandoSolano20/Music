package com.musica.tl;

import com.musica.bl.Album.Album;
import com.musica.bl.BibliotecaMusical;
import com.musica.bl.Country.Country;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Musican.Compositor.Compositor;
import com.musica.bl.ReproductionList.ReproductionList;
import com.musica.bl.Song.Song;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class Controller {

    BibliotecaMusical logic = new BibliotecaMusical();

    public boolean registerClient(int id, String name, String lastName, int old, String country, String email, String pass, String userName, String image){
        Client client = new Client(id,userName,name,lastName,email,pass,image,old,country);
        return logic.registerUser(client);
    }

    public List<String> getAllUser(){
        ArrayList<String> result = (ArrayList<String>)logic.getAllUser();
        for (String item:result) {
            System.out.println(item);
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return logic.isAdminOnDB();
    }

    public boolean registerAdmin(int id, String name, String lastName, String email, String pass, String userName, String image) {
        Admin admin = new Admin(id,userName,name,lastName,email,pass,image);
        return logic.registerUser(admin);
    }

    public boolean login(String email, String pass) {
        return logic.login(email,pass);
    }

    public boolean validateYears(int old){
        return logic.validateYears(old);
    }

    public boolean validatePassword(String pass){
        return logic.validatePassword(pass);
    }

    public int registerSong(String name, String gender, String artist, String compositor, int year, int month, int day, String album, int score, String pathSong) {
        Album album1 = logic.searchAlbumByName(album);
        if(album1 != null){
            Gender gen = logic.searchGenderByName(gender);
            if(gen != null){
                Artist artist1 = logic.searchArtistByArtistName(artist);
                if(artist1 != null){
                    Compositor compositor1 = logic.searchCompositorByNameAndLastName(compositor);
                    if(compositor1 != null){
                        Song song = new Song(name,gen,artist1,compositor1, LocalDate.of(year,month,day),album1,score,pathSong,logic.getActualUser().getId());
                        int response  = logic.registerSong(song) ? 1 : 0;
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

    public boolean registerGender(String name, String description) {
        Gender gender = new Gender(name,description);
        return logic.registerGender(gender);
    }

    public boolean registerArtist(String name, String lastName, String country, LocalDate dateBorn, LocalDate dateDead, String reference, String description, String gender, String artistName) {
        Gender gen = logic.searchGenderByName(gender);
        Country coun = logic.searchCountryByName(country);
        if(gen != null && coun != null){
            LocalDate actual = dateDead != null ? dateDead : LocalDate.now();
            int old = (int)YEARS.between(dateBorn, actual);
            Artist artist = new Artist(name,lastName,coun,old,dateBorn,dateDead,reference,description,gen,artistName);
            return logic.registerArtist(artist);
        }
        return false;
    }

    public boolean registerCompositor(String name, String lastName, String country, int old, String[] genders) {
        Country coun = logic.searchCountryByName(country);
        if(coun != null){
            Compositor compositor = new Compositor(name,lastName,coun,old);
            for (String gender:genders) {
                Gender gen = logic.searchGenderByName(gender);
                if(gen != null){
                    compositor.setGenders(gen);
                }
            }
            return logic.registerCompositor(compositor);
        }
        return false;
    }

    public boolean registerAlbum(String name, LocalDate release, String image, String[] artists) {
        Album album = new Album(name,release,image);
        for (String artist:artists) {
            Artist art = logic.searchArtistByArtistName(artist);
            if(art != null){
                album.setArtists(art);
            }
        }
        return logic.registerAlbum(album);
    }

    public boolean registerReproductionList(String name, int year, int month, int day, String[] songs) {
        ReproductionList reproductionList = new ReproductionList(LocalDate.of(year,month,day),name,logic.getActualUser());
        for (String song:songs) {
            Song son = logic.searchSongByName(song);
            if(son != null){
                reproductionList.setSongs(son);
            }
        }
        reproductionList.setScore();
        return logic.registerReproductionList(reproductionList);
    }
}
