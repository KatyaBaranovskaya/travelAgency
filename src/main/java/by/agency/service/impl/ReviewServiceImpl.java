package by.agency.service.impl;

import by.agency.domain.Review;
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
 * The ReviewServiceImpl class implements and realizes
 * methods of IService interface.
 *
 * Description of methods:
 * @see IService
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Service("reviewService")
public class ReviewServiceImpl implements IService<Review> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private IRepository<Review> reviewRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Review> getAll() {
        LOGGER.info("Get all reviews");
        return reviewRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Review getEntityById(int id) {
        LOGGER.info("Get review");
        return reviewRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Review review) {
        LOGGER.info("Add review");
        return reviewRepository.add(review);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        LOGGER.info("Delete review");
        return reviewRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Review review) {
        LOGGER.info("Update review");
        return reviewRepository.update(review);
    }
}

