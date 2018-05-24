package by.agency.repository.jpa.impl;

import by.agency.domain.Country;
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
public class CountryJPAImpl implements IRepository<Country> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Country> getAll() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Country> query = builder.createQuery(Country.class);
        Root<Country> root = query.from(Country.class);
        query.select(root);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public Country getEntityById(int id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getCountry");
        query.setParameter("id", id);
        return (Country) query.getSingleResult();
    }

    @Override
    public boolean add(Country country) {
        sessionFactory.getCurrentSession().persist(country);
        return getEntityById(country.getId()) != null;
    }

    @Override
    public boolean removeById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM country c WHERE c.id_country = :id")
                .addEntity(Country.class)
                .setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean update(Country country) {
        country.setVersion(getEntityById(country.getId()).getVersion());
        return sessionFactory.getCurrentSession().merge(country) != null;
    }
}