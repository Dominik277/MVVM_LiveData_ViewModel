package view.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import view.model.db.FavouritesDbHelper;

public class FavouritesViewModel extends AndroidViewModel {

    private FavouritesDbHelper mFavHelper;
    private ArrayList<Favourites> mFavs;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
    }

    public List<Favourites> getFavs(){
        if (mFavs == null){
            mFavs = new ArrayList<>();

            createDummyList();
            loadFavs();
        }
        ArrayList<Favourites> clonedFavs = new ArrayList<>(mFavs.size());
        for (int i=0; i<mFavs.size(); i++){
            clonedFavs.add(new Favourites(mFavs.get(i)));
        }
        return clonedFavs;
    }

    public void createDummyList(){

        addFav("https://www.journaldev.com",(new Date()).getTime());
        addFav("https://www.medium.com",(new Date()).getTime());
        addFav("https://www.reddit.com",(new Date()).getTime());
        addFav("https://www.github.com",(new Date()).getTime());
        addFav("https://www.hackerrank.com",(new Date()).getTime());
        addFav("https://www.developers.android.com",(new Date()).getTime());

    }

    private void loadFavs(){

    }

    public Favourites addFav(String url, long date){

    }

    public void removeFav(long id){

    }

}
