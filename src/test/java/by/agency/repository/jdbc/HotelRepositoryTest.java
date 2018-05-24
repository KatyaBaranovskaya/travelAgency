package by.agency.repository.jdbc;

import by.agency.config.DataConfig;
import by.agency.domain.Hotel;
import by.agency.service.IService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class HotelRepositoryTest {
    private static AnnotationConfigApplicationContext context;
    private static IService<Hotel> service;
    private static Hotel hotel;

    @BeforeClass
    public static void init(){
        System.setProperty("spring.profiles.active", "test");
        context = new AnnotationConfigApplicationContext(DataConfig.class);

        service = (IService<Hotel>) context.getBean("hotelService");
        hotel = (Hotel) context.getBean("hotel");
    }

    @AfterClass
    public static void remove() {
        hotel = null;
        service = null;
        context.close();
    }

    @Test
    public void save() {
        assertTrue(service.add(hotel));
    }

    @Test
    public void delete() {
        assertTrue(service.removeById(1));
    }

    @Test
    public void getHotel() {
        assertEquals(service.getEntityById(hotel.getId()).getName(), hotel.getName());
    }

    @Test
    public void getAll() {
        assertNotNull(service.getAll());
    }

    @Test
    public void update() {
        service.update(hotel);
        assertEquals(service.getEntityById(hotel.getId()).getName(), hotel.getName());
    }
}