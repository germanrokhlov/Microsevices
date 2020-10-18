package german.ms.moviecatalogservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieInfo {

    @JsonProperty (value = "id")
    private int movieId;
    @JsonProperty (value = "original_title")
    private String name;
    @JsonProperty (value = "overview")
    private String desc;

    public MovieInfo(int movieId, String name, String desc) {
        this.movieId = movieId;
        this.name = name;
        this.desc = desc;
    }

    public MovieInfo(){}

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

    public String toString(){
        return movieId + " : " + name + " : " + desc;
    }
}
