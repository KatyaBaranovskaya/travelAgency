package by.agency.repository.jpa.impl;

import by.agency.domain.Country;
import by.agency.domain.Review;
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
public class ReviewJPAImpl implements IRepository<Review> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Review> getAll() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Review> query = builder.createQuery(Review.class);
        Root<Review> root = query.from(Review.class);
        query.select(root);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public Review getEntityById(int id) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getReview");
        query.setParameter("id", id);
        return (Review) query.getSingleResult();
    }

    @Override
    public boolean add(Review review) {
        sessionFactory.getCurrentSession().persist(review);
        return getEntityById(review.getId()) != null;
    }

    @Override
    public boolean removeById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM review r WHERE r.id_review = :id")
                .addEntity(Country.class)
                .setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean update(Review review) {
        review.setVersion(getEntityById(review.getId()).getVersion());
        return sessionFactory.getCurrentSession().merge(review) != null;
    }
}