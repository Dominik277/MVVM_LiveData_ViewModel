package view.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavouritesDbHelper extends SQLiteOpenHelper {

    public FavouritesDbHelper(@Nullable Context context) {
        super(context,DbSettings.DB_NAME,null,DbSettings.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
