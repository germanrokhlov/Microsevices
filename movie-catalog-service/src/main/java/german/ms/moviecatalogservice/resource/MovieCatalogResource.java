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

//    @Autowired
//    private RestTemplate restTemplate;

@Autowired
 private MovieCatalogService movieCatalog;

    @RequestMapping ("/catalog/{userId}")
    public List<CatalogItem> getMoviesAndRatingsById (@PathVariable ("userId") String userId){
        return movieCatalog.getListById(userId);
    }

}
