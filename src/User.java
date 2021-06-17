import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private  LocalDate date;
    private String username;
    private String passsword;

    private ArrayList<Series> watched = new ArrayList<Series>();
    public User(String username, String passsword, LocalDate date) {
        super();
        this.username = username;
        this.passsword = passsword;
        this.date = LocalDate.now();

    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public ArrayList<Series> getWatched() {
        return watched;
    }
    public void setWatched(ArrayList<Series> watched) {
        this.watched = watched;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasssword() {
        return passsword;
    }
    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
    public void addSeries(Series series) {
        watched.add(series);
    }

    public boolean isDoneWatching(Series series) {
        boolean isDone=true;
        ArrayList<Episode> episods = new ArrayList<Episode>();
        episods=series.getEpisods();
        for(int i=0;i<episods.size();i++) {
            if(!episods.get(i).getWatchedBy().contains(this)) {
                isDone=false;
            }
        }
        return isDone;
    }
}