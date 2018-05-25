package by.agency.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Data
@EqualsAndHashCode(callSuper = false)
public class Country extends IEntity {
    @NotNull
    @Size(min = 3, max = 60)
    @Column(nullable = false)
    private String name;
}
