package by.agency.service.impl;

import by.agency.domain.User;
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
 * The UserServiceImpl class implements and realizes
 * methods of IService interface.
 *
 * Description of methods:
 * @see IService
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Service("userService")
public class UserServiceImpl implements IService<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IRepository<User> userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> getAll() {
        LOGGER.info("Get all users");
        return userRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User getEntityById(int id) {
        LOGGER.info("Get user");
        return userRepository.getEntityById(id);
    }

// TODO   (rollbackFor = Exception.class)

    @Override
    @Transactional
    public boolean add(User user) {
        LOGGER.info("Add user");
        return userRepository.add(user);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        LOGGER.info("Delete user");
        return userRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(User user) {
        LOGGER.info("Update user");
        return userRepository.update(user);
    }
}

