package by.agency;

import by.agency.config.DataConfig;
import by.agency.domain.*;
import by.agency.service.IService;
import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "jpa");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DataConfig.class);

        IService<Country> reviewIService = (IService<Country>) context.getBean("countryService");

        Country country = new Country();
       // country.setId(5);
        country.setName("Strana");

        Hotel hotel = new Hotel();
        hotel.setId(5);
        hotel.setName("sss111tAAt");
        hotel.setPhone("80299699134");
        hotel.setStars(5);

        Tour tour = new Tour();
        tour.setId(16);
        tour.setPhoto("!!!AA111image.jpg");
        tour.setDate(LocalDate.now());
        tour.setDuration(7);
        tour.setCountry(country);
        tour.setHotel(hotel);
        tour.setType(TourType.VACATION);
        tour.setDescription("Good");
        tour.setCost(BigDecimal.valueOf(126.4));
        tour.setVersion(4);

        Review review = new Review();
        review.setId(13);
        review.setTour(tour);
        review.setIdUser(33);
        review.setContent("!!!555gg111ddd");

        User user = new User();
       // user.setId(33);
        user.setLogin("AAA11!KATYA");
        user.setPassword("9699134aa");
        Set<Tour> tours = new HashSet<>();
        tours.add(tour);
        user.setTours(tours);
        Set<Review> reviews = new HashSet<>();
        reviews.add(review);
        user.setReviews(reviews);

        //reviewIService.add(country);
        reviewIService.getEntityById(3);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("by.agency.domain.Country").getSize();
        System.out.println(size);

         //System.out.println(reviewIService.getEntityById(3));
    }
}