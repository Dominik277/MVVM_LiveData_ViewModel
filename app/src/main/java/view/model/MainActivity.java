package view.model;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FavouritesAdapter mFavAdapter;
    private FavouritesViewModel mFavViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void deteFav(View view){
        View parent = (View) view.getParent();
        int position = (int)parent.getTag(R.id.POS);
        Favourites favourites = mFavAdapter.getItem(position);
        mFavViewModel.removeFav(favourites.mId);
        mFavAdapter.remove(favourites);
    }

    public class FavouritesAdapter extends ArrayAdapter<Favourites>{

        public FavouritesAdapter(@NonNull Context context, int resource, List<Favourites> todos) {
            super(context, resource,todos);
        }
    }

}
