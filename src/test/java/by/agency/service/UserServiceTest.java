package by.agency.service;

import by.agency.domain.User;
import by.agency.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserServiceImpl userService;

    private User user;

    @Before
    public void init(){
        userService = mock(UserServiceImpl.class);
        user = new User();
        user.setId(1);
        user.setLogin("katya");
        user.setPassword("222");
    }

    @After
    public void remove() {
        user = null;
    }

    @Test
    public void save() {
        when(userService.add(user)).thenReturn(true);
        userService.add(user);
        verify(userService).add(user);
    }

    @Test
    public void readTest() {
        when(userService.getEntityById(user.getId())).thenReturn(user);
        userService.getEntityById(user.getId());
        verify(userService).getEntityById(user.getId());
    }

    @Test
    public void readAllTest() {
        userService.getAll();
        verify(userService).getAll();
    }

    @Test
    public void deleteTest() {
        when(userService.removeById(user.getId())).thenReturn(true);
        userService.removeById(user.getId());
        verify(userService).removeById(user.getId());
    }

    @Test
    public void update() {
        when(userService.update(user)).thenReturn(true);
        userService.update(user);
        verify(userService).update(user);
    }
}
