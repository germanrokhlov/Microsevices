package german.ms.moviecatalogservice.resource;

import german.ms.moviecatalogservice.model.CatalogItem;
import german.ms.moviecatalogservice.model.MovieInfo;
import german.ms.moviecatalogservice.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;


    public List<CatalogItem> listOfMovies = new ArrayList<CatalogItem>(Arrays.asList(
            new CatalogItem("german", 16),
            new CatalogItem("german", 17) ,
            new CatalogItem("alex",101),
            new CatalogItem("alex",102)

    ));

    @RequestMapping ("/catalog/{userId}")
    public List<CatalogItem> getMoviesAndRatingsById (@PathVariable ("userId") String userId){
        return getListById(userId);
    }

    public List<CatalogItem> getListById(String userId){
        List<CatalogItem> listOfMoviesById = new ArrayList<>(Arrays.asList());

        for (CatalogItem item: listOfMovies) {
            if (item.getUserId().equals(userId)) {
                Ratings rating = restTemplate.getForObject("http://rating-info-service/ratings/" + item.getMovieId(), Ratings.class);
                MovieInfo movie =  restTemplate.getForObject("http://movie-info-service/movies/" + item.getMovieId(), MovieInfo.class);
                System.out.println(movie);
                CatalogItem theItem = new CatalogItem(userId, item.getMovieId(), movie.getName(), movie.getDesc(), rating.getRating());
                listOfMoviesById.add(theItem);
            }
        }

        return listOfMoviesById;
   }

}
