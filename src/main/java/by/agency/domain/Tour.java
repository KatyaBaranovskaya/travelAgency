package by.agency.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * The Tour is entity class used for storage
 * information about tour.
 * Tour extends of IEntity abstract class.
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Entity
@Table(name = "tour")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_tour")),
        @AttributeOverride(name = "version", column = @Column(name = "version"))
})
@NamedQuery(name = "getTour", query = "from Tour t where t.id = :id")
@Data
@ToString(exclude = "users")
@EqualsAndHashCode(callSuper = false, exclude = {"country", "hotel", "users"})
public class Tour extends IEntity {
    @Column
    private String photo;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @Min(3)
    @NotNull
    @Column(nullable = false)
    private int duration;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_hotel", nullable = false)
    private Hotel hotel;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TourType type;

    @NotNull
    @Column(nullable = false)
    @Size(min = 3, max = 2000)
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal cost;

    @ManyToMany(mappedBy = "tours")
    private Set<User> users;
}
