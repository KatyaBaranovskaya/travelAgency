package by.agency.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(tours, user.tours) &&
                Objects.equals(reviews, user.reviews);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), login, password, tours, reviews);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", tours=" + tours +
                ", reviews=" + reviews +
                '}';
    }
}
