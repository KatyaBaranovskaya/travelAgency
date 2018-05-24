package by.agency.repository;

import by.agency.domain.IEntity;

import java.util.List;

/**
 * The IRepository is the interface
 * which contains CRUD methods.
 *
 * @author Katsiaryna Baranovskaya
 * @version 1.0
 */
public interface IRepository<T extends IEntity> {
    /**
     * @return list of Entities
     */
    List<T> getAll();

    /**
     * @param id of IEntity
     * @return object of IEntity
     */
    T getEntityById(int id);

    /**
     * @param temp - object of entity
     * @return object of IEntity
     */
    boolean add(T temp);

    /**
     * @param id of IEntity
     * @return object of IEntity
     */
    boolean removeById(int id);

    /**
     * @param temp - object of entity
     * @return object of IEntity
     */
    boolean update(T temp);
}
