package by.agency.repository.jdbc;

import by.agency.config.DataConfig;
import by.agency.domain.User;
import by.agency.service.IService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    private static AnnotationConfigApplicationContext context;
    private static IService<User> service;
    private static User user;

    @BeforeClass
    public static void init(){
        System.setProperty("spring.profiles.active", "test");
        context = new AnnotationConfigApplicationContext(DataConfig.class);

        service = (IService<User>) context.getBean("userService");
        user = (User) context.getBean("user");
    }

    @AfterClass
    public static void remove() {
        user = null;
        service = null;
        context.close();
    }

    @Test
    public void save() {
        assertTrue(service.add(user));
    }

    @Test
    public void delete() {
        assertTrue(service.removeById(1));
    }

    @Test
    public void getUser() {
        assertEquals(service.getEntityById(user.getId()).getLogin(), user.getLogin());
    }

    @Test
    public void getAll() {
        assertNotNull(service.getAll());
    }

    @Test
    public void update() {
        service.update(user);
        assertEquals(service.getEntityById(user.getId()).getLogin(), user.getLogin());
    }
}