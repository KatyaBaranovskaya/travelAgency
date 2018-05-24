package by.agency.repository.jdbc.impl;

import by.agency.domain.Country;
import by.agency.domain.Hotel;
import by.agency.domain.Tour;
import by.agency.domain.TourType;
import by.agency.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Profile({"jdbc", "test"})
public class TourRepositoryImpl implements IRepository<Tour> {
    private static final String INSERT_TOUR = "INSERT INTO tour(photo, date, duration, id_country, id_hotel, " +
            "type, description, cost) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_TOURS = "SELECT id_tour, photo, date, duration, id_country, id_hotel, type, " +
            "description, cost FROM tour";
    private static final String SELECT_TOUR_BY_ID = "SELECT id_tour, photo, date, duration, id_country, id_hotel, type, " +
            "description, cost FROM tour WHERE id_hotel = ?";
    private static final String DELETE_TOUR_BY_ID = "DELETE FROM tour WHERE id_tour = ?";
    private static final String UPDATE_TOUR_BY_ID = "UPDATE tour SET photo = ?, date = ?, duration = ?, id_country = ?, " +
            "id_hotel = ?, type = ?, description = ?, cost = ? WHERE id_tour = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tour> getAll() {
        return jdbcTemplate.query(
                SELECT_TOURS,
                (resultSet, i) -> {
                    Tour tour = new Tour();
                    Country country = new Country();
                    Hotel hotel = new Hotel();
                    country.setId(resultSet.getInt("id_country"));
                    hotel.setId(resultSet.getInt("id_hotel"));

                    tour.setId(resultSet.getInt("id_tour"));
                    tour.setPhoto(resultSet.getString("photo"));
                    tour.setDate(resultSet.getDate("date").toLocalDate());
                    tour.setDuration(resultSet.getInt("duration"));
                    tour.setCountry(country);
                    tour.setHotel(hotel);
                    tour.setType(TourType.valueOf(resultSet.getString("type")));
                    tour.setDescription(resultSet.getString("description"));
                    tour.setCost(resultSet.getBigDecimal("cost"));

                    return tour;
                });
    }

    @Override
    public Tour getEntityById(int id) {
        return jdbcTemplate.queryForObject(
                SELECT_TOUR_BY_ID,
                new Object[]{id},
                (resultSet, i) -> {
                    Tour tour = new Tour();
                    tour.setId(resultSet.getInt("id_tour"));
                    tour.setPhoto(resultSet.getString("photo"));
                    tour.setDate(resultSet.getDate("date").toLocalDate());
                    tour.setDuration(resultSet.getInt("duration"));
                    tour.setType(TourType.valueOf(resultSet.getString("type")));
                    tour.setDescription(resultSet.getString("description"));
                    tour.setCost(resultSet.getBigDecimal("cost"));

                    Country country = new Country();
                    Hotel hotel = new Hotel();
                    country.setId(resultSet.getInt("id_country"));
                    hotel.setId(resultSet.getInt("id_hotel"));
                    tour.setCountry(country);
                    tour.setHotel(hotel);

                    return tour;
                });
    }


    @Override
    public boolean add(Tour tour) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(INSERT_TOUR, new String[] {"id_tour"});
                    pst.setString(1, tour.getPhoto());
                    pst.setDate(2, Date.valueOf(tour.getDate()));
                    pst.setInt(3, tour.getDuration());
                    pst.setInt(4, tour.getCountry().getId());
                    pst.setInt(5, tour.getHotel().getId());
                    pst.setString(6, tour.getType().toString());
                    pst.setString(7, tour.getDescription());
                    pst.setBigDecimal(8, tour.getCost());
                    return pst;
                },
                keyHolder);
        tour.setId((int) keyHolder.getKey());
        return id != 0;
    }

    @Override
    public boolean removeById(int id) {
        return jdbcTemplate.update(DELETE_TOUR_BY_ID, id) == 1;
    }

    @Override
    public boolean update(Tour tour) {
        return jdbcTemplate.update(UPDATE_TOUR_BY_ID, tour.getPhoto(), Date.valueOf(tour.getDate()),
                tour.getDuration(), tour.getCountry().getId(), tour.getHotel().getId(), tour.getType().toString(),
                tour.getDescription(), tour.getCost(), tour.getId()) == 1;
    }
}