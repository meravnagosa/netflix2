import java.util.ArrayList;
public class Series {
    private String name;
    ArrayList<User> watchedBy = new ArrayList<User>();
    private ArrayList<Episode> episods = new ArrayList<Episode>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Episode> getEpisods() {
        return episods;
    }
    public void setEpisods(ArrayList<Episode> episods) {
        this.episods = episods;
    }

    public Series(String name, ArrayList<Episode> episods) {
        super();
        this.name = name;
        this.episods = episods;
    }
    public ArrayList<User> getWatchedBy() {
        return watchedBy;
    }
    public void setWatchedBy(ArrayList<User> watchedBy) {
        this.watchedBy = watchedBy;
    }
    public void addUser(User user) {
        watchedBy.add(user);
    }


}