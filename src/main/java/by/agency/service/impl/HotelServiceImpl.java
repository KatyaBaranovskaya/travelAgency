package by.agency.service.impl;

import by.agency.domain.Hotel;
import by.agency.repository.IRepository;
import by.agency.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The HotelServiceImpl class implements and realizes
 * methods of IService interface.
 *
 * Description of methods:
 * @see IService
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Slf4j
@Service("hotelService")
public class HotelServiceImpl implements IService<Hotel> {
    @Autowired
    private IRepository<Hotel> hotelRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Hotel> getAll() {
        log.info("Get all hotels");
        return hotelRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Hotel getEntityById(int id) {
        log.info("Get hotel");
        return hotelRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Hotel hotel) {
        log.info("Add hotel");
        return hotelRepository.add(hotel);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        log.info("Delete hotel");
        return hotelRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Hotel hotel) {
        log.info("Update hotel");
        return hotelRepository.update(hotel);
    }
}

