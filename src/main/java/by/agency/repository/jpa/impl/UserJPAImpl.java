package by.agency.repository.jpa.impl;

import by.agency.domain.Country;
import by.agency.domain.User;
import by.agency.repository.IRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Profile("jpa")
public class UserJPAImpl implements IRepository<User> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAll() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public User getEntityById(int id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getUser");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean add(User user) {
        sessionFactory.getCurrentSession().save(user);
        return getEntityById(user.getId()) != null;
    }

    @Override
    public boolean removeById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM users u WHERE u.id_user = :id")
                .addEntity(Country.class)
                .setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean update(User user) {
        user.setVersion(getEntityById(user.getId()).getVersion());
        return sessionFactory.getCurrentSession().merge(user) != null;
    }
}