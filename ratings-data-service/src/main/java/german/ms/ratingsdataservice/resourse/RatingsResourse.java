package german.ms.ratingsdataservice.resourse;


import german.ms.ratingsdataservice.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RatingsResourse {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

//    List<Ratings> ratingsList = new ArrayList<>(Arrays.asList(
//       new Ratings(16,5),
//       new Ratings(17, 4),
//       new Ratings (101, 1),
//       new Ratings (102, 1)
//    ));

@RequestMapping("/ratings/{movieId}")
    public Ratings getRatings (@PathVariable ("movieId") int movieId){
    return getRatingsById(movieId);
}


public  Ratings getRatingsById (int movieId){
//    Ratings rating = new Ratings();
//    for (Ratings item : ratingsList){
//        if (item.getMovieId() == movieId){
//            rating = item;
//        }
//    }
//    return rating;
    Ratings theRating = new Ratings();
    theRating = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=e5f5d50d5626caca6acadd3b067673a2",Ratings.class);
    return theRating;


}

}


