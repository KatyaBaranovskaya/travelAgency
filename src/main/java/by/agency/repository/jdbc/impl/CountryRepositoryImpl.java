package by.agency.repository.jdbc.impl;

import by.agency.domain.Country;
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
public class CountryRepositoryImpl implements IRepository<Country> {
    private static final String INSERT_COUNTRY = "INSERT INTO country(name) VALUES (?)";
    private static final String SELECT_COUNTRIES = "SELECT id_country, name FROM country";
    private static final String SELECT_COUNTRY_BY_ID = "SELECT id_country, name FROM country WHERE id_country = ?";
    private static final String DELETE_COUNTRY_BY_ID = "DELETE FROM country WHERE id_country = ?";
    private static final String UPDATE_COUNTRY_BY_ID = "UPDATE country SET name = ? WHERE id_country = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query(
                SELECT_COUNTRIES,
                (resultSet, i) -> {
                    Country country = new Country();
                    country.setId(resultSet.getInt("id_country"));
                    country.setName(resultSet.getString("name"));

                    return country;
                });
    }

    @Override
    public Country getEntityById(int id) {
        return jdbcTemplate.queryForObject(
                SELECT_COUNTRY_BY_ID,
                new Object[]{id},
                (resultSet, i) -> {
                    Country country = new Country();
                    country.setId(resultSet.getInt("id_country"));
                    country.setName(resultSet.getString("name"));

                    return country;
                });
    }

    @Override
    public boolean add(Country country) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id = jdbcTemplate.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(INSERT_COUNTRY, new String[] {"id_country"});
                    pst.setString(1, country.getName());
                    return pst;
                },
                keyHolder);
        country.setId((int) keyHolder.getKey());
        return id != 0;

    }

    @Override
    public boolean removeById(int id) {
        return jdbcTemplate.update(DELETE_COUNTRY_BY_ID, id) == 1;
    }

    @Override
    public boolean update(Country country) {
        return jdbcTemplate.update(UPDATE_COUNTRY_BY_ID, country.getName(), country.getId()) == 1;
    }
}
