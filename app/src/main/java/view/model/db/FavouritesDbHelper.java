package view.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

//ovu klasu mozemo zamisliti kao nekakvog pomagaca koji nam pomaze u tome da kreiramo
//tablicu unutar baze podataka i da unesemo podatke u tablicu, da bi to sve bilo moguce
//prvo moramo navesti da nasljeđujemo klasu SQLiteOpenHelper klasu koja je zaduzena za
//kontrolu svega gore navedenoga.Posto smo nasljedili ovu klasu moramo implementirati dvije
//metode koje su obavezne i jednu koja je po izboru, obavezne metode su onCreate() i onUpgrade()
//a ova koja nije obavezna je onOpen()
public class FavouritesDbHelper extends SQLiteOpenHelper {

    public FavouritesDbHelper(@Nullable Context context) {
        super(context,DbSettings.DB_NAME,null,DbSettings.DB_VERSION);
    }

    //ovdje imamo metodu koja je zasluzena za kreiranje tablice unutar baze podataka, unutar
    //ove metode smo naveli sve query-e koje bi naveli unutar bilo koje SQL baze podataka, jer
    //ovo je direktan SQLite, nije kao npr Room library koji nam omogucava abstraction layer preko
    //SQLite-a koji nam omogucava da se izbjegne sav ovaj boilerplate kod tako sto ga on kreira
    //umjesto nas i znatno nam olaksava posao
    @Override
    public void onCreate(SQLiteDatabase db) {

        //ovdje smo kreirali varijablu createTable tipa String unutar koje smo pohranili cijelu
        //naredbu za kreiranje tablice u nasoj bazi podataka s tocno onim stupcima kako mi zelimo
        //i kada opet zelimo kreirati jos jednu tablicu unutar baze podataka samo pozovemo varijablu
        //createTable
        //CREATE TABLE --> ovo je SQL naredba koja se poziva kada zelimo kreirati tablicu, znaci ona
        //                 sama po sebi ne radi mnogo nego se s pomocu nje samo deklarira da ce se
        //                 kreirati nova tablica u bazi podataka i ona je pocetna tocka tog procesa
        //DbSettings.DBEntry.TABLE --> u klasi DbSettings smo kreirali inner klasu DBEntry unutar koje
        //                             se nalaze static varijable koje nam svaka predstavlja ime tablice
        //                             te imena dvaju stupaca te smo pomocu ove naredbe pristupili varijabli
        //                             unutar koje je spremljeno ime koje zelimo dati tablici
        String createTable = "CREATE TABLE " + DbSettings.DBEntry.TABLE + " ( " +

                //DbSettings.DBEntry._ID --> iako ove varijable nema, ona ni ne treba biti jer je definirana
                //                           kao INTEGER PRIMARY KEY AUTOINCREMENT, znaci ova naredba nam kreira
                //                           unutar tablice jedan stupac s imenom ID koji ce biti jedinstven za
                //                           svaki redak, to ce biti identifikacijski broj
                //INTEGER PRIMARY KEY AUTOINCREMENT --> ova naredba nam govori da ce taj stupac biti tipa integer
                //                                      i da ce se svakim novim retkom povecavati, a poceti ce od
                //                                      nule
                DbSettings.DBEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                //DbSettings.DBEntry.COL_FAV_URL --> u klasi DbSettings smo kreirali inner klasu DBEntry unutar koje
                //                                   se nalaze static varijable koje nam svaka predstavlja ime tablice
                //                                   te imena dvaju stupaca te smo pomocu ove naredbe pristupili varijabli
                //                                   unutar koje je spremljeno ime koje zelimo dati jednom stupcu unutar
                //                                   kojeg ce biti pohranjena URl lokacija
                //TEXT NOT NULL --> s ovom naredbom smo definirali da u ovom stupcu mora biti tekst, znaci ne smije biti
                //                  broj te također da ovaj stupac nikad ne smije biti prazan
                DbSettings.DBEntry.COL_FAV_URL + " TEXT NOT NULL, " +

                //DbSettings.DBEntry.COL_FAV_DATE --> u klasi DbSettings smo kreirali inner klasu DBEntry unutar koje
                //                                    se nalaze static varijable koje nam svaka predstavlja ime tablice
                //                                    te imena dvaju stupaca te smo pomocu ove naredbe pristupili varijabli
                //                                    unutar koje je spremljeno ime koje zelimo dati jednom stupcu unutar
                //                                    kojeg ce biti pohranjen datum
                //INTEGER NOT NULL --> ovom naredbom smo definirali da ovaj stupac također ne smije biti prazan, da uvijek
                //                     mora biti popunjen i to integer-om
                DbSettings.DBEntry.COL_FAV_DATE + " INTEGER NOT NULL);";

        //prethodno gore smo sve deklarirali kako ce se odvijati radnja kreiranja tablice unutar baze podataka i koje sve
        //elemente ce imati ta tablica, ali to je samo definirano i stoji tako, treba nam neka metoda koja sluzi da bi
        //upogonili sve to gore navedeno, a upravo to je zadatak metode execSQL() kojoj predajemo String varijablu unutar
        //koje smo definirali sve ono sto je potrebno za kreiranje tablice
        db.execSQL(createTable);
    }

    //ova metoda se poziva onda kada zelimo azurirati nasu tablicu unutar baze podataka, ova metoda se izvrsava na taj nacin
    //da se prvo potpuno izbrise cijela prethodna tablica koju smo imali, a zatim se pozove metoda onCreate() koja izvrsava
    //sve ono sto smo prethodno naveli u toj metodi
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DbSettings.DBEntry.TABLE);
        onCreate(db);
    }
}
