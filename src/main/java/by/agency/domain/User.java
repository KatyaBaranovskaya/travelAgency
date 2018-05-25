package by.agency.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * The User is entity class used for storage
 * information about user.
 * User extends of IEntity abstract class.
 *
 * @author Katsiaryna Baranovskaya
 * @version 1.0
 */

@Entity
@Table(name = "users")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_user")),
        @AttributeOverride(name = "version", column = @Column(name = "version"))
})
@NamedQuery(name = "getUser", query = "from User u where u.id = :id")
@Data
@ToString(exclude = {"tours", "reviews"})
@EqualsAndHashCode(callSuper = false, exclude = {"tours", "reviews"})
public class User extends IEntity {
    @Column
    @NotNull
    @Size(min = 3, max = 20)
    private String login;

    @NotNull
    @Size(min = 6, max = 18)
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tour_user", joinColumns = @JoinColumn(name = "id_user",
            referencedColumnName = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_tour",
            referencedColumnName = "id_tour"))
    private Set<Tour> tours;

    @OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Review> reviews;
}
