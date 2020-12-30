package view.model;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private FavouritesAdapter mFavAdapter;
    private FavouritesViewModel mFavViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        FloatingActionButton fab = findViewById(R.id.fab);

        mFavViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);

        List<Favourites> favourites = mFavViewModel.getFavs();
        mFavAdapter = new FavouritesAdapter(this,R.layout.list_item_row,favourites);
        listView.setAdapter(mFavAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inUrl = new EditText(MainActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("New Favourite")
                        .setMessage("Add a url link below")
                        .setView(inUrl)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = String.valueOf(inUrl.getText());
                                long date = (new Date()).getTime();

                                mFavAdapter.add(mFavViewModel.addFav(url, date));
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    public void deleteFav(View view){
        View parent = (View) view.getParent();
        int position = (int) parent.getTag(R.id.POS);
        Favourites favourites = mFavAdapter.getItem(position);
        mFavViewModel.removeFav(favourites.mId);
        mFavAdapter.remove(favourites);
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
