package by.agency.service.impl;

import by.agency.domain.Country;
import by.agency.repository.IRepository;
import by.agency.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Slf4j
@Service("countryService")
public class CountryServiceImpl implements IService<Country> {
    @Autowired
    private IRepository<Country> countryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Country> getAll() {
        log.info("Get all countries");
        return countryRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country getEntityById(int id) {
        log.info("Get country");
        return countryRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(Country country) {
        log.info("Add country");
        return countryRepository.add(country);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        log.info("Delete country");
        return countryRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(Country country) {
        log.info("Update country");
        return countryRepository.update(country);
    }
}

