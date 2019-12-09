package com.musica.ui;

import com.musica.bl.Song.Song;
import com.musica.tl.Controller;
import com.musica.ui.views.Song.Lists.ListsSong;
import com.musica.ui.views.Song.Profile.Profile;
import com.musica.ui.views.reproductionList.Lists.ListsReproList;
import com.musica.ui.views.reproductionList.Lists.Song.AddSongReproduction;
import com.musica.ui.views.reproductionList.Lists.Song.ListsSongsRL;
import com.musica.ui.views.reproductionList.Update.UpdateRL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicUI {
    protected ShowView show = new ShowView();
    protected Controller controller = new Controller();
    private String id;
    private List<String> queue = new ArrayList<>();

    public List<String> getQueue() {
        return queue;
    }

    public void setQueue(String song) {
        this.queue.add(song);
    }

    public String getId() {
        return id;
    }

    protected void cGender(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Gender/Register/Register.fxml", "Crear Género");
    }

    protected void rGender(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Gender/List/List.fxml", "Listar Género");
    }

    protected void uGender(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Gender/Update/Update.fxml", "Actualizar Género");
    }

    protected void sGender(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Gender/Search/Search.fxml", "Buscar Género");
    }

    protected void dGender(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Gender/Delete/Delete.fxml", "Borrar Género");
    }

    protected void cComp(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Compositor/Register/Register.fxml", "Crear Compositor");
    }

    protected void rComp(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Compositor/List/List.fxml", "Listar Compositor");
    }

    protected void uComp(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Compositor/Update/Update.fxml", "Actualizar Compositor");
    }

    protected void sComp(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Compositor/Search/Search.fxml", "Buscar Compositor");
    }

    protected void dComp(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Compositor/Delete/Delete.fxml", "Borrar Compositor");
    }

    protected void cArt(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Artist/Register/Register.fxml", "Crear Artista");
    }

    protected void rArt(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Artist/List/List.fxml", "Listar Artista");
    }

    protected void uArt(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Artist/Update/Update.fxml", "Actualizar Artista");
    }

    protected void sArt(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Artist/Search/Search.fxml", "Buscar Artista");
    }

    protected void dArt(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Artist/Delete/Delete.fxml", "Borrar Artista");
    }

    protected void cAlbum(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Album/Register/Register.fxml", "Crear Album");
    }

    protected void rAlbum(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Album/List/List.fxml", "Listar Album");
    }

    protected void uAlbum(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Album/Update/Update.fxml", "Actualizar Album");
    }

    protected void sAlbum(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Album/Search/Search.fxml", "Buscar Album");
    }

    protected void dAlbum(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Album/Delete/Delete.fxml", "Borrar Album");
    }

    protected void cSong(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Song/Register/Register.fxml", "Crear Canción");
    }

    protected void rSong(ActionEvent event, String id) throws IOException {
        show.ShowWindow(event, "views/Song/Profile/Profile.fxml", "Listar Canción",id,new Profile());
    }

    protected void uSong(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Song/Update/Update.fxml", "Actualizar Canción");
    }

    protected void sSong(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Song/Search/Search.fxml", "Buscar Canción");
    }

    protected void dSong(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/Song/Delete/Delete.fxml", "Borrar Canción");
    }

    protected void logout(ActionEvent event) throws IOException {
        show.ShowWindow(event, "views/login/login.fxml", "Borrar Canción");
    }

    protected void login(ActionEvent event) throws IOException {
        show.ShowWindow(event,"views/login/login.fxml", "Iniciar Sesión");
    }

    protected void index(ActionEvent event) throws IOException {
        if(controller.userType() == "Administrador"){
            show.ShowWindow(event,"views/index/admin/Index.fxml", "Inicio");
        }
        else {
            show.ShowWindow(event,"views/index/user/Index.fxml", "Inicio");
        }
    }

    protected void songsOnReproductionList(ActionEvent event, String id) throws IOException {
        show.ShowWindow(event,"views/Song/Lists/List.fxml","Txt",id,new ListsSong());
    }

    public void transferId(String message) {
        id = message;
    }

    protected void buy(ActionEvent event) throws IOException {
        show.ShowWindow(event,"views/buy/Buy.fxml","Txt");
    }

    protected void lists(ActionEvent event) throws IOException {
        show.ShowWindow(event,"views/reproductionList/Lists/Lists.fxml", "Lista de reproduccion");
    }

    protected void catalogs(ActionEvent event) throws IOException {
        show.ShowWindow(event,"views/Catalog/Catalog.fxml","Txt");
    }

    protected void albums(ActionEvent event) throws IOException {
    }

    protected void reproductions(ActionEvent event) throws IOException {
        show.ShowWindow(event,"views/SongQueue/SongQueue.fxml", "Colas");
    }

    public void addSongtoReproductionList(ActionEvent event, String id) throws IOException {
        show.ShowWindow(event,"views/reproductionList/Lists/Song/AddSong.fxml","Txt",id,new AddSongReproduction());
    }

    public void updateReproductionList(ActionEvent event, String id) throws IOException {
        show.ShowWindow(event,"views/reproductionList/Update/Update.fxml","Txt",id,new UpdateRL());
    }

    public void adminSongsReproductionList(ActionEvent event, String id) throws IOException {
        show.ShowWindow(event,"views/reproductionList/Lists/Song/Lists.fxml","Txt",id,new ListsSongsRL());
    }

    protected void createReproductionList(ActionEvent event) throws IOException {
        show.ShowWindow(event,"views/reproductionList/Register/Register.fxml", "Crear Lista de reproduccion");
    }
}
