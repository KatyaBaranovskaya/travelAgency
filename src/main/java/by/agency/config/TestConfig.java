package by.agency.config;

import by.agency.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

@Configuration
@Profile("test")
public class TestConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:init-data.sql")
                .build();
    }

    @Bean("country")
    public Country initCountry() {
        Country country = new Country();
        country.setId(1);
        country.setName("Brazil");
        return country;
    }

    @Bean("hotel")
    public Hotel initHotel() {
        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Europe");
        hotel.setPhone("80299699134");
        hotel.setStars(5);
        return hotel;
    }


    @Bean("tour")
    public Tour initTour() {
        Tour tour = new Tour();
        tour.setId(1);
        tour.setPhoto("image.jpg");
        tour.setDate(LocalDate.now());
        tour.setDuration(7);
        tour.setCountry(initCountry());
        tour.setHotel(initHotel());
        tour.setType(TourType.VACATION);
        tour.setDescription("Good");
        tour.setCost(BigDecimal.valueOf(126.4));
        return tour;
    }


    @Bean("review")
    public Review initReview() {
        Review review = new Review();
        review.setId(1);
        review.setTour(initTour());
        review.setIdUser(2);
        review.setContent("Great");
        return review;
    }

    @Bean("user")
    public User initUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("katya1997");
        user.setPassword("9699134aa");
        user.setTours(new HashSet<>());
        user.setReviews(new HashSet<>());
        return user;
    }
}