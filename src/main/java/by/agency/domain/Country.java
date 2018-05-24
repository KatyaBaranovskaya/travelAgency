package by.agency.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import org.hibernate.annotations.Cache;

/**
 * The Country is entity class used for storage
 * information about country.
 * Country extends of IEntity abstract class.
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Entity
@Table(name = "country")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_country")),
        @AttributeOverride(name = "version", column = @Column(name = "version"))
})
@NamedQuery(name = "getCountry", query = "from Country c where c.id = :id")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country extends IEntity {
    @NotNull
    @Size(min = 3, max = 60)
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
