package by.agency.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Hotel is entity class used for storage
 * information about hotel.
 * Hotel extends of IEntity abstract class.
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Entity
@Table(name = "hotel")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_hotel")),
        @AttributeOverride(name = "version", column = @Column(name = "version"))
})
@NamedQuery(name = "getHotel", query = "from Hotel h where h.id = :id")
@Data
public class Hotel extends IEntity {
    @NotNull
    @Size(min = 3)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Size(min = 7)
    @Column(nullable = false)
    private String phone;

    @Min(1)
    @Max(5)
    @NotNull
    @Column(nullable = false)
    private int stars;
}
