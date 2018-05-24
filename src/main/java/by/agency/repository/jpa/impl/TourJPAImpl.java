package by.agency.repository.jpa.impl;

import by.agency.domain.Country;
import by.agency.domain.Tour;
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
public class TourJPAImpl implements IRepository<Tour> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Tour> getAll() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Tour> query = builder.createQuery(Tour.class);
        Root<Tour> root = query.from(Tour.class);
        query.select(root);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public Tour getEntityById(int id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getTour");
        query.setParameter("id", id);
        return (Tour) query.getSingleResult();
    }

    @Override
    public boolean add(Tour tour) {
        sessionFactory.getCurrentSession().persist(tour);
        return getEntityById(tour.getId()) != null;
    }

    @Override
    public boolean removeById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM tour t WHERE t.id_tour = :id")
                .addEntity(Country.class)
                .setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean update(Tour tour) {
        tour.setVersion(getEntityById(tour.getId()).getVersion());
        return sessionFactory.getCurrentSession().merge(tour) != null;
    }
}