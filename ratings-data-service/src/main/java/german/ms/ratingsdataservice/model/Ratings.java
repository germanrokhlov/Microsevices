package german.ms.ratingsdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ratings {

    @JsonProperty (value = "id")
    private int movieId;
    @JsonProperty (value = "vote_average")
    private double rating;


    public Ratings(){

    }
    public Ratings(int movieId, double rating) {
        this.movieId = movieId;
        this.rating = rating;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String toString (){
    return movieId + ":" +rating;

    }
}
