package view.model;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import view.model.db.DbSettings;
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

        mFavs.clear();

        SQLiteDatabase db = mFavHelper.getReadableDatabase();
        Cursor cursor = db.query(DbSettings.DBEntry.TABLE,
                new String[]{
                        DbSettings.DBEntry._ID,
                        DbSettings.DBEntry.COL_FAV_URL,
                        DbSettings.DBEntry.COL_FAV_DATE
                },null,null,null,null,null);
        while (cursor.moveToNext()){
            int idxId = cursor.getColumnIndex(DbSettings.DBEntry._ID);
            int idxUrl = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_URL);
            int idxDate = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_DATE);
            mFavs.add(new Favourites(cursor.getLong(idxId),cursor.getString(idxUrl)
                                    ,cursor.getLong(idxDate)));
        }
        cursor.close();
        db.close();

    }

    public Favourites addFav(String url, long date){

    }

    public void removeFav(long id){

    }

}
