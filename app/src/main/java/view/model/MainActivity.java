package view.model;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


//ovo nam je glavna klasa unutar koje se također nalazi i onCreate() metoda koja se izvrsava kada se
//program prvi put izvodi, unutar ove metode se deklariraju stvari koje se ne mjenjaju tijekom zivotnog
//vijeka aplikacije, unutar te metodi bi se trebali "infejtati" svi oni view-ovi koji se nalaze u XML-u
//kako bi njima mogli baratati u Java kodu i vrsiti razne operacije na njima
public class MainActivity extends AppCompatActivity {

    //
    private FavouritesAdapter mFavAdapter;
    private FavouritesViewModel mFavViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ovdje smo kreirali objekt tipa ListView koji se zove listView od XML elementa koji se nalazi
        //unutar activity_main.xml layout-a i sada mozemo s njim "baratati"
        ListView listView = findViewById(R.id.listView);

        //ovdje smo također stvorili Java objekt od XML elementa a to je Floatin Action Button koji se
        //isto nalazi u activity_main layout-u
        FloatingActionButton fab = findViewById(R.id.fab);

        mFavViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);

        List<Favourites> favourites = mFavViewModel.getFavs();
        mFavAdapter = new FavouritesAdapter(this,R.layout.list_item_row,favourites);
        listView.setAdapter(mFavAdapter);

        //ovdje smo pomocu setOnClickListener() metode definirali da ce se klikom na ovaj gumb nesto desiti
        //nismo jos definirali sta, ali smo definirali da ce se desiti
        fab.setOnClickListener(new View.OnClickListener() {

            //ovdje je definirano sta ce se desiti prilikom klika na floating action button unutar ove metode
            @Override
            public void onClick(View v) {

                //prilikom klika na gumb kreiran je objekt u memoriji kojega cemo referencirati pomocu imena
                //inUlr, a bit ce tipa EditText
                final EditText inUrl = new EditText(MainActivity.this);

                //također prilikom klika na gumb ce nam biti prikazan i AlertDialog
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)

                        //ova metoda nam daje mogucnost da odredimo koji ce biti naslov naseg AlertDialog-a
                        //a naslov koji zelimo da bude moramo predati kao argument ovoj metodi u obliku stringa
                        .setTitle("New Favourite")

                        //ova metoda nam sluzi kako bi postavili poruku unutar AlertDialog-a, a poruku koju zelimo
                        //postaviti prilazemo ovoj metodi kao argument tipa string
                        .setMessage("Add a url link below")
                        .setView(inUrl)

                        //AlertDialog uvijek ima dva gumba, jedan Positive Button i jedan NegativeButton, unutar ove
                        //metode deklariramo sta ce pisati na tom PositiveButton-u i sta ce se desavati prilikom klika
                        //na taj Positive Button
                        //u ovom metodi smo rekli da ce Positive Button biti ispunjen tekstom "Add"
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = String.valueOf(inUrl.getText());
                                long date = (new Date()).getTime();

                                mFavAdapter.add(mFavViewModel.addFav(url, date));
                            }
                        })

                        //ovdje definiramo sta ce se desiti prilikom klika na Negative Button i posto nista nismo definirali
                        //to znaci da da ne zelimo apsolutno nista da se dogodi prilikom klika na gumb i jos smo definirali
                        //da ce ovaj gumb biti ispunjen tekstom "Cancel"
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


    public class FavouritesAdapter extends ArrayAdapter<Favourites>{

        private class ViewHolder{
            TextView tvUrl;
            TextView tvDate;
        }

        public FavouritesAdapter(@NonNull Context context, int resource, List<Favourites> todos) {
            super(context, resource,todos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Favourites favourites = getItem(position);
            ViewHolder viewHolder;

            if (convertView == null){
                viewHolder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_item_row,parent,false);

                viewHolder.tvUrl = convertView.findViewById(R.id.tvUrl);
                viewHolder.tvDate = convertView.findViewById(R.id.tvDate);

                convertView.setTag(R.id.VH,viewHolder);
            }else {
                viewHolder = (ViewHolder)convertView.getTag(R.id.VH);
            }

            viewHolder.tvUrl.setText(favourites.mUrl);
            viewHolder.tvDate.setText((new Date(favourites.mDate).toString()));
            convertView.setTag(R.id.POS, position);
            return convertView;
        }
    }

}
