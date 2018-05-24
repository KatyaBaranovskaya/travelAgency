package by.agency.service.impl;

import by.agency.domain.Country;
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
 * The CountryServiceImpl class implements and realizes
 * methods of IService interface.
 *
 * Description of methods:
 * @see IService
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Service("countryService")
public class CountryServiceImpl implements IService<Country> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private IRepository<Country> countryRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Country> getAll() {
        LOGGER.info("Get all countries");
        return countryRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Country getEntityById(int id) {
        LOGGER.info("Get country");
        return countryRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Country country) {
        LOGGER.info("Add country");
        return countryRepository.add(country);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        LOGGER.info("Delete country");
        return countryRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Country country) {
        LOGGER.info("Update country");
        return countryRepository.update(country);
    }
}

