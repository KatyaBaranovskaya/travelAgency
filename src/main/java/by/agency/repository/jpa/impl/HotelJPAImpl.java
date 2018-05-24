package by.agency.repository.jpa.impl;

import by.agency.domain.Country;
import by.agency.domain.Hotel;
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
public class HotelJPAImpl implements IRepository<Hotel> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Hotel> getAll() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Hotel> query = builder.createQuery(Hotel.class);
        Root<Hotel> root = query.from(Hotel.class);
        query.select(root);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public Hotel getEntityById(int id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getHotel");
        query.setParameter("id", id);
        return (Hotel) query.getSingleResult();
    }

    @Override
    public boolean add(Hotel hotel) {
        sessionFactory.getCurrentSession().persist(hotel);
        return getEntityById(hotel.getId()) != null;
    }

    @Override
    public boolean removeById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM hotel h WHERE h.id_hotel = :id")
                .addEntity(Country.class)
                .setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean update(Hotel hotel) {
        hotel.setVersion(getEntityById(hotel.getId()).getVersion());
        return sessionFactory.getCurrentSession().merge(hotel) != null;
    }
}