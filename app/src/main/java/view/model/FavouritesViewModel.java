package view.model;

import android.app.Application;

import java.util.ArrayList;
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

    }

    private void loadFavs(){

    }

    public Favourites addFav(String url, long date){

    }

    public void removeFav(long id){

    }

}
