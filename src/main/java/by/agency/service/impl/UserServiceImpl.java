package by.agency.service.impl;

import by.agency.domain.User;
import by.agency.repository.IRepository;
import by.agency.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Slf4j
@Service("userService")
public class UserServiceImpl implements IService<User> {
    @Autowired
    private IRepository<User> userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        log.info("Get all users");
        return userRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getEntityById(int id) {
        log.info("Get user");
        return userRepository.getEntityById(id);
    }

    @Override
    @Transactional
    public boolean add(User user) {
        log.info("Add user");
        return userRepository.add(user);
    }

    @Override
    @Transactional
    public boolean removeById(int id) {
        log.info("Delete user");
        return userRepository.removeById(id);
    }

    @Override
    @Transactional
    public boolean update(User user) {
        log.info("Update user");
        return userRepository.update(user);
    }
}

