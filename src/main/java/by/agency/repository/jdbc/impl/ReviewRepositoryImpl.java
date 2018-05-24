package by.agency.repository.jdbc.impl;

import by.agency.domain.Review;
import by.agency.domain.Tour;
import by.agency.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Profile({"jdbc", "test"})
public class ReviewRepositoryImpl implements IRepository<Review> {
    private static final String INSERT_REVIEW = "INSERT INTO review(id_tour, id_user, content) VALUES (?, ?, ?)";
    private static final String SELECT_REVIEWS = "SELECT id_review, id_tour, id_user, content FROM review";
    private static final String SELECT_REVIEW_BY_ID = "SELECT id_review, id_tour, id_user, content FROM review " +
            "WHERE id_review = ?";
    private static final String DELETE_REVIEW_BY_ID = "DELETE FROM review WHERE id_review = ?";
    private static final String UPDATE_REVIEW_BY_ID = "UPDATE review SET id_tour = ?, id_user = ?, content = ? WHERE id_review = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> getAll() {
        return jdbcTemplate.query(
                SELECT_REVIEWS,
                (resultSet, i) -> {
                    Review review = new Review();
                    Tour tour = new Tour();

                    tour.setId(resultSet.getInt("id_tour"));
                    review.setId(resultSet.getInt("id_review"));
                    review.setTour(tour);
                    review.setIdUser(resultSet.getInt("id_user"));
                    review.setContent(resultSet.getString("content"));

                    return review;
                });
    }

    @Override
    public Review getEntityById(int id) {
        return jdbcTemplate.queryForObject(
                SELECT_REVIEW_BY_ID,
                new Object[]{id},
                (resultSet, i) -> {
                    Tour tour = new Tour();
                    tour.setId(resultSet.getInt("id_tour"));

                    Review review = new Review();
                    review.setId(resultSet.getInt("id_review"));
                    review.setContent(resultSet.getString("content"));
                    review.setTour(tour);
                    review.setIdUser(resultSet.getInt("id_user"));

                    return review;
                });
    }

    @Override
    public boolean add(Review review) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(INSERT_REVIEW, new String[] {"id_review"});
                    pst.setInt(1, review.getTour().getId());
                    pst.setInt(2, review.getIdUser());
                    pst.setString(3, review.getContent());
                    return pst;
                },
                keyHolder);
        review.setId((int) keyHolder.getKey());
        return id != 0;
    }

    @Override
    public boolean removeById(int id) {
        return jdbcTemplate.update(DELETE_REVIEW_BY_ID, id) == 1;
    }

    @Override
    public boolean update(Review review) {
        return jdbcTemplate.update(UPDATE_REVIEW_BY_ID, review.getTour().getId(), review.getIdUser(),
                review.getContent(), review.getId()) == 1;
    }
}