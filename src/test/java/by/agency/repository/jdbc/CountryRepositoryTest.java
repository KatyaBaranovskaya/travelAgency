package by.agency.repository.jdbc;

import by.agency.config.DataConfig;
import by.agency.domain.Country;
import by.agency.service.IService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class CountryRepositoryTest {
    private static AnnotationConfigApplicationContext context;
    private static IService<Country> service;
    private static Country country;

    @BeforeClass
    public static void init(){
        System.setProperty("spring.profiles.active", "test");
        context = new AnnotationConfigApplicationContext(DataConfig.class);

        service = (IService<Country>) context.getBean("countryService");
        country = (Country) context.getBean("country");
    }

    @AfterClass
    public static void remove() {
        country = null;
        service = null;
        context.close();
    }

    @Test
    public void save() {
        assertTrue(service.add(country));
    }

    @Test
    public void delete() {
        assertTrue(service.removeById(1));
    }

    @Test
    public void getCountry() {
        assertEquals(service.getEntityById(country.getId()).getName(), country.getName());
    }

    @Test
    public void getAll() {
        assertNotNull(service.getAll());
    }

    @Test
    public void update() {
        service.update(country);
        assertEquals(service.getEntityById(country.getId()).getName(), country.getName());
    }
}
