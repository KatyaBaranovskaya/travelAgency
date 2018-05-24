package by.agency.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tour tour = (Tour) o;
        return duration == tour.duration &&
                Objects.equals(photo, tour.photo) &&
                Objects.equals(date, tour.date) &&
                Objects.equals(hotel, tour.hotel) &&
                type == tour.type &&
                Objects.equals(description, tour.description) &&
                Objects.equals(cost, tour.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), photo, date, duration, hotel, type, description, cost);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "photo='" + photo + '\'' +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                ", country=" + country +
                ", hotel=" + hotel +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
