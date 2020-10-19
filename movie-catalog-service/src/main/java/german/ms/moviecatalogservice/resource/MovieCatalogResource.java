package german.ms.moviecatalogservice.resource;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import german.ms.moviecatalogservice.model.CatalogItem;
import german.ms.moviecatalogservice.model.MovieInfo;
import german.ms.moviecatalogservice.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class MovieCatalogResource {


    public List<CatalogItem> listOfMovies = new ArrayList<CatalogItem>(Arrays.asList(
            new CatalogItem("german", 16),
            new CatalogItem("german", 17) ,
            new CatalogItem("alex",101),
            new CatalogItem("alex",102)

    ));

@Autowired
 private MovieCatalogService movieCatalog;

    @RequestMapping ("/catalog/{userId}")
    public List<CatalogItem> getMoviesAndRatingsById (@PathVariable ("userId") String userId){
        return getListById(userId);
    }



    public List<CatalogItem> getListById(String userId){
        List<CatalogItem> listOfMoviesById = new ArrayList<>(Arrays.asList());
        for (CatalogItem item: listOfMovies) {
            if (item.getUserId().equals(userId)) {
                MovieInfo movie = movieCatalog.getMovieInfobyId(item.getMovieId());
                Ratings rating = movieCatalog.getRatingById(item.getMovieId());
                System.out.println(movie);
                System.out.println(rating);
                CatalogItem theItem = new CatalogItem(userId, movie.getMovieId(), movie.getName(), movie.getDesc(), rating.getRating());
                listOfMoviesById.add(theItem);
            }
        }
        return listOfMoviesById;
    }


}
