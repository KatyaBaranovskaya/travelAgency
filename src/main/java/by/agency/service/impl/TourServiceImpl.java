package by.agency.service.impl;

import by.agency.domain.Tour;
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
 * The TourServiceImpl class implements and realizes
 * methods of IService interface.
 *
 * Description of methods:
 * @see IService
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Service("tourService")
public class TourServiceImpl implements IService<Tour> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

    @Autowired
    private IRepository<Tour> tourRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tour> getAll() {
        LOGGER.info("Get all tours");
        return tourRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Tour getEntityById(int id) {
        LOGGER.info("Get tour");
        return tourRepository.getEntityById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean add(Tour tour) {
        LOGGER.info("Add tour");
        return tourRepository.add(tour);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        LOGGER.info("Delete tour");
        return tourRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Tour tour) {
        LOGGER.info("Update tour");
        return tourRepository.update(tour);
    }
}

