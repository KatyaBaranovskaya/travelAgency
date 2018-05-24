package by.agency.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * The Review is entity class used for storage
 * information about review.
 * Review extends of IEntity abstract class.
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@Entity
@Table(name = "review")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_review")),
        @AttributeOverride(name = "version", column = @Column(name = "version"))
})
@NamedQuery(name = "getReview", query = "from Review r where r.id = :id")
public class Review extends IEntity {
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tour")
    private Tour tour;

    @NotNull
    @Column(name = "id_user", nullable = false)
    private int idUser;

    @NotNull
    @Column(nullable = false)
    @Size(min = 3, max = 2000)
    private String content;

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return idUser == review.idUser &&
                Objects.equals(tour, review.tour) &&
                Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tour, idUser, content);
    }

    @Override
    public String toString() {
        return "Review{" +
                "tour=" + tour +
                ", idUser=" + idUser +
                ", content='" + content + '\'' +
                '}';
    }
}
