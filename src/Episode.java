import java.util.ArrayList;

public class Episode {
    private String name;
    private String summary;
    private String releaseDate;
    ArrayList<User> watchedBy = new ArrayList<User>();
    public Episode(String name, String summary, String releaseDate) {
        super();
        this.name = name;
        this.summary = summary;
        this.releaseDate = releaseDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<User> getWatchedBy() {
        return watchedBy;
    }
    public void setWatchedBy(ArrayList<User> watchedBy) {
        this.watchedBy = watchedBy;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void remarkAsWatched(User user){
        watchedBy.add(user);
    }
}