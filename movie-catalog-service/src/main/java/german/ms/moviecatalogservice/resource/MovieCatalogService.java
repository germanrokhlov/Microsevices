package german.ms.moviecatalogservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import german.ms.moviecatalogservice.model.CatalogItem;
import german.ms.moviecatalogservice.model.MovieInfo;
import german.ms.moviecatalogservice.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;


    public List<CatalogItem> listOfMovies = new ArrayList<CatalogItem>(Arrays.asList(
            new CatalogItem("german", 16),
            new CatalogItem("german", 17) ,
            new CatalogItem("alex",101),
            new CatalogItem("alex",102)

    ));

    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getListById(String userId){
        List<CatalogItem> listOfMoviesById = new ArrayList<>(Arrays.asList());
        for (CatalogItem item: listOfMovies) {
            if (item.getUserId().equals(userId)) {
                Ratings rating = restTemplate.getForObject("http://rating-info-service/ratings/" + item.getMovieId(), Ratings.class);
                MovieInfo movie =  restTemplate.getForObject("http://movie-info-service/movies/" + item.getMovieId(), MovieInfo.class);
                System.out.println(movie);
                CatalogItem theItem = new CatalogItem(userId, movie.getMovieId(), movie.getName(), movie.getDesc(), rating.getRating());
                listOfMoviesById.add(theItem);
            }
        }
        return listOfMoviesById;
    }

    public List<CatalogItem> getFallbackCatalog(String userId){
//        List<CatalogItem> listOfMoviesById = new ArrayList<>(Arrays.asList());
//        for (CatalogItem item: listOfMovies){
//            if(item.getUserId() == userId){
//                item.setName("No Movie");
//                item.setDesc("No Desc");
//                item.setRating(0.0);
//                listOfMoviesById.add(item);
//            }
//        }
        return Arrays.asList(new CatalogItem(userId, 0, "No movie", "No movie", 0));
    }

}
