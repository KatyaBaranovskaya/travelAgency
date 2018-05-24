package by.agency.service;

import by.agency.domain.Country;
import by.agency.domain.Hotel;
import by.agency.service.impl.HotelServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {
    private HotelServiceImpl hotelService;

    private Hotel hotel;

    @Before
    public void init(){
        hotelService = mock(HotelServiceImpl.class);
        hotel = new Hotel();
        Country country = new Country();
        hotel.setId(1);
        hotel.setName("Europe");
        hotel.setPhone("+375299699134");
        country.setId(1);
        country.setName("Belarus");
        hotel.setStars(5);
    }

    @After
    public void remove() {
        hotel = null;
    }

    @Test
    public void save() {
        when(hotelService.add(hotel)).thenReturn(true);
        hotelService.add(hotel);
        verify(hotelService).add(hotel);
    }

    @Test
    public void readTest() {
        when(hotelService.getEntityById(hotel.getId())).thenReturn(hotel);
        hotelService.getEntityById(hotel.getId());
        verify(hotelService).getEntityById(hotel.getId());
    }

    @Test
    public void readAllTest() {
        hotelService.getAll();
        verify(hotelService).getAll();
    }

    @Test
    public void deleteTest() {
        when(hotelService.removeById(hotel.getId())).thenReturn(true);
        hotelService.removeById(hotel.getId());
        verify(hotelService).removeById(hotel.getId());
    }

    @Test
    public void update() {
        when(hotelService.update(hotel)).thenReturn(true);
        hotelService.update(hotel);
        verify(hotelService).update(hotel);
    }
}
