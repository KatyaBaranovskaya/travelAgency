package by.agency.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * IEntity is the abstract base class for travel contexts
 * which allows an application to define traveling, user and review entities.
 * An Entities object encapsulates id-information needed
 * for the various rendering operations that Java supports.
 *
 * @author      Katsiaryna Baranovskaya
 * @version     1.0
 */

@MappedSuperclass
public abstract class IEntity{
    /**
     * Parameter - unique key of an entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Parameter - version of an entity.
     */
    @Version
    @Column (name = "version")
    private long version;

    /**
     * @return the value of current id.
     */
    public int getId() {
        return id;
    }

    /**Set id value.
     *@param id the key of entity
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the value of current version.
     */
    public long getVersion() {
        return version;
    }

    /**Set version value.
     *@param version the version of entity
     */
    public void setVersion(long version) {
        this.version = version;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IEntity entity = (IEntity) o;
        return id == entity.id;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * @see java.lang.Object#toString() ()
     */
    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
