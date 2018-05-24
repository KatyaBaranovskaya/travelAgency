package by.agency.service.impl;

import by.agency.domain.Hotel;
import by.agency.repository.IRepository;
import by.agency.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

@Service("hotelService")
public class HotelServiceImpl implements IService<Hotel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Autowired
    private IRepository<Hotel> hotelRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Hotel> getAll() {
        LOGGER.info("Get all hotels");
        return hotelRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Hotel getEntityById(int id) {
        LOGGER.info("Get hotel");
        return hotelRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Hotel hotel) {
        LOGGER.info("Add hotel");
        return hotelRepository.add(hotel);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        LOGGER.info("Delete hotel");
        return hotelRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Hotel hotel) {
        LOGGER.info("Update hotel");
        return hotelRepository.update(hotel);
    }
}

