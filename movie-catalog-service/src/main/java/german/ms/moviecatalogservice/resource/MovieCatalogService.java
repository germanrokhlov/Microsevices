package german.ms.moviecatalogservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import german.ms.moviecatalogservice.model.CatalogItem;
import german.ms.moviecatalogservice.model.MovieInfo;
import german.ms.moviecatalogservice.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand (fallbackMethod = "fallbackgetRatingbyId")
    public Ratings getRatingById(int movieId) {

        Ratings rating = restTemplate.getForObject("http://rating-info-service/ratings/" + movieId, Ratings.class);
        return rating;
    }

    public Ratings fallbackgetRatingbyId(int movieId){
        return new Ratings(movieId, 0);
    }

    @HystrixCommand (fallbackMethod = "fallbackgetMoviebyId")
    public MovieInfo getMovieInfobyId(int movieId) {

        MovieInfo movie = restTemplate.getForObject("http://movie-info-service/movies/" + movieId, MovieInfo.class);
        return movie;

    }

    public MovieInfo fallbackgetMoviebyId (int movieId){
        return new MovieInfo(movieId, "Not found", "Not found");
    }
}
