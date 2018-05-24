package by.agency.repository.jdbc.impl;

import by.agency.domain.Review;
import by.agency.domain.Tour;
import by.agency.domain.User;
import by.agency.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;

@Repository
@Profile({"jdbc", "test"})
public class UserRepositoryImpl implements IRepository<User> {
    private static final String INSERT_USER = "INSERT INTO users(login, password) VALUES (?, ?)";
    private static final String SELECT_USERS = "SELECT id_user, login, password FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT id_user, login, password FROM users WHERE id_user = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id_user = ?";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET login = ?, password = ? WHERE id_user = ?";
    private static final String SELECT_TOURS_QUERY = "SELECT id_tour FROM tour_user WHERE id_user = ?";
    private static final String SELECT_REVIEWS_QUERY = "SELECT id_review FROM review WHERE id_user = ?";
    private static final String INSERT_USER_TOURS = "INSERT INTO tour_user(id_user, id_tour) VALUES (?, ?)";
    private static final String UPDATE_USER_TOURS = "UPDATE tour_user SET id_tour = ? WHERE id_user = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                SELECT_USERS,
                (resultSet, i) -> {
                    User user = new User();
                    user.setId(resultSet.getInt("id_user"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setTours(new HashSet<>(getTours(resultSet.getInt("id_user"))));
                    user.setReviews(new HashSet<>(getReviews(resultSet.getInt("id_user"))));

                    return user;
                });
    }

    @Override
    public User getEntityById(int id) {
        return jdbcTemplate.queryForObject(
                SELECT_USER_BY_ID,
                new Object[]{id},
                (resultSet, i) -> {
                    User user = new User();
                    user.setId(resultSet.getInt("id_user"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));

                    user.setTours(new HashSet<>(getTours(user.getId())));
                    user.setReviews(new HashSet<>(getReviews(user.getId())));

                    return user;
                });
    }

    @Override
    public boolean add(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(INSERT_USER, new String[] {"id_user"});
                    pst.setString(1, user.getLogin());
                    pst.setString(2, user.getPassword());

                    return pst;
                },
                keyHolder);
        user.setId((int) keyHolder.getKey());

        if(user.getTours() != null) {
            addUserTours(user);
        }

        return id != 0;

    }

    @Override
    public boolean removeById(int id) {
        return jdbcTemplate.update(DELETE_USER_BY_ID, id) == 1;
    }

    @Override
    public boolean update(User user) {
        return jdbcTemplate.update(UPDATE_USER_BY_ID, user.getLogin(), user.getPassword(), user.getId()) == 1;
    }

    private List<Tour> getTours(int id) {
        return jdbcTemplate.query(
                SELECT_TOURS_QUERY,
                new Object[]{id},
                (resultSet, i) -> {
                    Tour tour = new Tour();
                    tour.setId(resultSet.getInt("id_tour"));

                    return tour;
                });
    }

    private List<Review> getReviews(int id) {
        return jdbcTemplate.query(
                SELECT_REVIEWS_QUERY,
                new Object[]{id},
                (resultSet, i) -> {
                    Review review = new Review();
                    review.setId(resultSet.getInt("id_review"));

                    return review;
                });
    }

    private void addUserTours(User user) {
        for (Tour tour : user.getTours()) {
            jdbcTemplate.update(INSERT_USER_TOURS, user.getId(), tour.getId());
        }
    }
}