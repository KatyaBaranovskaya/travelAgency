package by.agency.repository.jdbc.impl;

import by.agency.domain.Hotel;
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
public class HotelRepositoryImpl implements IRepository<Hotel> {
    private static final String INSERT_HOTEL = "INSERT INTO hotel(name, phone, stars) VALUES (?,?,?)";
    private static final String SELECT_HOTELS = "SELECT id_hotel, name, phone, stars FROM hotel";
    private static final String SELECT_HOTEL_BY_ID = "SELECT id_hotel, name, phone, stars FROM hotel WHERE id_hotel = ?";
    private static final String DELETE_HOTEL_BY_ID = "DELETE FROM hotel WHERE id_hotel = ?";
    private static final String UPDATE_HOTEL_BY_ID = "UPDATE hotel SET name = ?, phone = ?, stars = ? WHERE id_hotel = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Hotel> getAll() {
        return jdbcTemplate.query(
                SELECT_HOTELS,
                (resultSet, i) -> {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id_hotel"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("stars"));

                    return hotel;
                });
    }

    @Override
    public Hotel getEntityById(int id) {
        return jdbcTemplate.queryForObject(
                SELECT_HOTEL_BY_ID,
                new Object[]{id},
                (resultSet, i) -> {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id_hotel"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("stars"));

                    return hotel;
                });
    }


    @Override
    public boolean add(Hotel hotel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(INSERT_HOTEL, new String[] {"id_hotel"});
                    pst.setString(1, hotel.getName());
                    pst.setString(2, hotel.getPhone());
                    pst.setInt(3, hotel.getStars());
                    return pst;
                },
                keyHolder);
        hotel.setId((int) keyHolder.getKey());
        return id != 0;
    }

    @Override
    public boolean removeById(int id) {
        return jdbcTemplate.update(DELETE_HOTEL_BY_ID, id) == 1;
    }

    @Override
    public boolean update(Hotel hotel) {
        return jdbcTemplate.update(UPDATE_HOTEL_BY_ID, hotel.getName(), hotel.getPhone(), hotel.getStars(), hotel.getId()) == 1;
    }
}