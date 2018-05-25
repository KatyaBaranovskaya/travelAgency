package by.agency.service.impl;

import by.agency.domain.Tour;
import by.agency.repository.IRepository;
import by.agency.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Slf4j
@Service("tourService")
public class TourServiceImpl implements IService<Tour> {
    @Autowired
    private IRepository<Tour> tourRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tour> getAll() {
        log.info("Get all tours");
        return tourRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tour getEntityById(int id) {
        log.info("Get tour");
        return tourRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Tour tour) {
        log.info("Add tour");
        return tourRepository.add(tour);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        log.info("Delete tour");
        return tourRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Tour tour) {
        log.info("Update tour");
        return tourRepository.update(tour);
    }
}

