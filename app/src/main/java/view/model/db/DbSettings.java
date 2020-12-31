package view.model.db;

import android.provider.BaseColumns;

//ova klasa nam predstavlja kao kontejner unutar kojeg smo definirali razne elemente
//koji su sadrzani unutar baze podataka te smo ih pohranili u razna imena kako bi ih
//kasnije lakse referencirali,npr. imamo String DB_NAME unutar kojeg smo pohranili
//ime baze podataka koje zelimo, također sve varijable su static zbog toga da ne moramo
//instancirati ovu klasu kako bi dohvatili sve ove varijable
public class DbSettings {

    //ovdje smo kreirali varijablu DB_NAME tipa String unutar koje smo pohranili tekst
    //"favourites.db" i svaki sljedeci puta kada zelimo negdje navesti ime baze podataka
    //to cemo uraditi pozivanjem varijable DB_NAME
    public static final String DB_NAME = "favourites.db";

    //ovdje smo stvorili varijablu tipa int DB_VERSION unutar koje smo pohranili broj
    //verzije baze podataka, ubuduce kada budemo trebali reci koja je verzija baze podataka
    //pozvat cemo varijablu DB_VERSION a ne direktno broj
    public static final int DB_VERSION = 1;

    //ovo je inner ili nested klasa unutar koje se nalaze svi elementi koji ce biti sadrzani
    //u tablici, kao sto vidimo imamo varijablu tipa String TABLE unutar koje smo pohranili
    //ime koje zelimo da nasa tablica ima, nakon toga imamo prvi stupac u tablici, a to je
    //URL i na kraju imamo jos jedan stupac koji je zasluzan za pohranjivanje datuma, također
    //sve varijable su tipa static zbog toga da bi im se moglo pristupati bez da moramo
    //instancirati ovu klasu
    public class DBEntry implements BaseColumns{

        public static final String TABLE = "fav";
        public static final String COL_FAV_URL = "url";
        public static final String COL_FAV_DATE = "date";
    }

}
