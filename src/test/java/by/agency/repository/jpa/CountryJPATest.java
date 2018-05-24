package by.agency.repository.jpa;

import by.agency.domain.Country;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CountryJPATest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        Country country = new Country();
        country.setId(4);
        country.setName(null);

        Set<ConstraintViolation<Country>> constraintViolations =
                validator.validate(country);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void countryIsValid() {
        Country country = new Country();
        country.setId(4);
        country.setName("Belarus");

        Set<ConstraintViolation<Country>> constraintViolations =
                validator.validate(country);

        assertEquals(0, constraintViolations.size());
    }
}