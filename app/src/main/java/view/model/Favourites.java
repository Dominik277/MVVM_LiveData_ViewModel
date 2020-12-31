package view.model;

//ovo nam je klasicna POJO klasa unutar koje imamo glavne podatke koji su nam potrebni
//za aplikaciju, kao sto vidimo imamo tri varijable, a svaka varijabla nam predstavlja
//jedan stupac u tablici
public class Favourites {

    //tri varijable koja nam svaka predstavlja jedan redak unutar tablice, znaci nasa
    //tablica ce sadrzavati tri stupca,id,url i datum
    public long mId;
    public String mUrl;
    public long mDate;

    //također imamo i nas konstruktor u cije parametre unosimo ono sto zelimo da pojedini
    //objekt ima, a to mozemo zamisliti da nam jedan objekt u memoriji zapravo predstavlja
    //jedan redak u tablici i mi kada kreiramo objekt te mu dodjelimo sve podatke u obliku
    //argumenata zapravo kreiramo jedan readak u tablici
    public Favourites(long id, String name, long date) {
        this.mId = id;
        this.mUrl = name;
        this.mDate = date;
    }

    //ovo nam je isto konstruktor ali kojemu kao argument predajemo objekt klase Favourites
    //zapravo na ovaj nacin radimo sve isto kao i u gornjem konstruktoru, samo sto u gornjem
    //konstruktoru unosimo kao argumente vrijednosti za varijable, a ovdje unosimo objekt kao
    //argument
    public Favourites(Favourites favourites){
        mId = favourites.mId;
        mUrl = favourites.mUrl;
        mDate = favourites.mDate;
    }

}
