package by.agency.repository.jdbc;

import by.agency.config.DataConfig;
import by.agency.domain.Tour;
import by.agency.service.IService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class TourRepositoryTest {
    private static AnnotationConfigApplicationContext context;
    private static IService<Tour> service;
    private static Tour tour;

    @BeforeClass
    public static void init(){
        System.setProperty("spring.profiles.active", "test");
        context = new AnnotationConfigApplicationContext(DataConfig.class);

        service = (IService<Tour>) context.getBean("tourService");
        tour = (Tour) context.getBean("tour");
    }

    @AfterClass
    public static void remove() {
        tour = null;
        service = null;
        context.close();
    }

    @Test
    public void save() {
        assertTrue(service.add(tour));
    }

    @Test
    public void delete() {
        assertTrue(service.removeById(1));
    }

//    @Test
//    public void getTour() {
//        assertEquals(service.getEntityById(tour.getId()).getCost(), tour.getCost());
//    }

    @Test
    public void getAll() {
        assertNotNull(service.getAll());
    }

    @Test
    public void update() {
        service.update(tour);
        assertEquals(service.getEntityById(tour.getId()).getDuration(), tour.getDuration());
    }
}