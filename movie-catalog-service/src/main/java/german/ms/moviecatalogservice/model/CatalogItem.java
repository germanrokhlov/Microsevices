package german.ms.moviecatalogservice.model;

public class CatalogItem {

    private String userId;
    private int movieId;
    private String name;
    private String desc;
    private double rating;

    public CatalogItem(String userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public CatalogItem(String userId, int movieId, String name, String desc, double rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public CatalogItem(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
