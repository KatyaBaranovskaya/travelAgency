package by.agency.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Data
@EqualsAndHashCode(callSuper = false, exclude = "tour")
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
}
