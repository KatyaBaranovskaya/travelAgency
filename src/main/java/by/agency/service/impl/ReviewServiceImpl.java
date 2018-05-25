package by.agency.service.impl;

import by.agency.domain.Review;
import by.agency.repository.IRepository;
import by.agency.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The ReviewServiceImpl class implements and realizes
 * methods of IService interface.
 *
 * Description of methods:
 * @see IService
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Slf4j
@Service("reviewService")
public class ReviewServiceImpl implements IService<Review> {
    @Autowired
    private IRepository<Review> reviewRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Review> getAll() {
        log.info("Get all reviews");
        return reviewRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Review getEntityById(int id) {
        log.info("Get review");
        return reviewRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Review review) {
        log.info("Add review");
        return reviewRepository.add(review);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        log.info("Delete review");
        return reviewRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Review review) {
        log.info("Update review");
        return reviewRepository.update(review);
    }
}

