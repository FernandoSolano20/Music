package com.musica.bl.factory;

import com.musica.bl.Album.AlbumDao;
import com.musica.bl.Album.IAlbumDao;
import com.musica.bl.Gender.GenderDao;
import com.musica.bl.Gender.IGenderDao;
import com.musica.bl.Musican.Artist.ArtistDao;
import com.musica.bl.Musican.Artist.IArtistDao;
import com.musica.bl.Musican.Compositor.CompositorDao;
import com.musica.bl.Musican.Compositor.ICompositorDao;
import com.musica.bl.ReproductionList.IReproductionListDao;
import com.musica.bl.ReproductionList.ReproductionListDao;
import com.musica.bl.Song.ISongDao;
import com.musica.bl.Song.SongDao;
import com.musica.bl.User.Client.ClientDao;
import com.musica.bl.User.Client.IClientDao;
import com.musica.bl.User.IUserDao;
import com.musica.bl.User.UserDao;

public class MySqlDaoFactory extends DaoFactory {
    @Override
    public IUserDao getUserDao() {
        return new UserDao();
    }

    @Override
    public IClientDao getClientDao() {
        return new ClientDao();
    }

    @Override
    public IGenderDao getGenderDao() {
        return new GenderDao();
    }

    @Override
    public ISongDao getSongDao() {
        return new SongDao();
    }

    @Override
    public IReproductionListDao getReproductionListDao() {
        return new ReproductionListDao();
    }

    @Override
    public IArtistDao getArtistDao() {
        return new ArtistDao();
    }

    @Override
    public ICompositorDao getCompositorDao() {
        return new CompositorDao();
    }

    @Override
    public IAlbumDao getAlbumDao() {
        return new AlbumDao();
    }
}
