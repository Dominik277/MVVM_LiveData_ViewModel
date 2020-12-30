package view.model;

public class Favourites {

    public long mId;
    public String mUrl;
    public long mDate;

    public Favourites(long id, String name, long date) {
        this.mId = id;
        this.mUrl = name;
        this.mDate = date;
    }

    public Favourites(Favourites favourites){
        mId = favourites.mId;
        mUrl = favourites.mUrl;
        mDate = favourites.mDate;
    }

}
