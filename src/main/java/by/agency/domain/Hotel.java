package by.agency.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return stars == hotel.stars &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(phone, hotel.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phone, stars);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", stars=" + stars +
                '}';
    }
}
