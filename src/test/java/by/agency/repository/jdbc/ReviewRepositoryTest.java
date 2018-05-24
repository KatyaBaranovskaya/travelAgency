package by.agency.repository.jdbc;

import by.agency.config.DataConfig;
import by.agency.domain.Review;
import by.agency.service.IService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class ReviewRepositoryTest {
    private static AnnotationConfigApplicationContext context;
    private static IService<Review> service;
    private static Review review;

    @BeforeClass
    public static void init(){
        System.setProperty("spring.profiles.active", "test");
        context = new AnnotationConfigApplicationContext(DataConfig.class);

        service = (IService<Review>) context.getBean("reviewService");
        review = (Review) context.getBean("review");
    }

    @AfterClass
    public static void remove() {
        review = null;
        service = null;
        context.close();
    }

    @Test
    public void save() {
        assertTrue(service.add(review));
    }

    @Test
    public void delete() {
        assertTrue(service.removeById(1));
    }

    @Test
    public void getReview() {
        assertEquals(service.getEntityById(review.getId()).getContent(), review.getContent());
    }

    @Test
    public void getAll() {
        assertNotNull(service.getAll());
    }

    @Test
    public void update() {
        service.update(review);
        assertEquals(service.getEntityById(review.getId()).getContent(), review.getContent());
    }
}