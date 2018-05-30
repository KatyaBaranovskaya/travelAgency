package by.agency.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
public abstract class IEntity implements Serializable {
    /**
     * Parameter - unique key of an entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;

    /**
     * Parameter - version of an entity.
     */
    @Version
    @Column (name = "version")
    @Getter @Setter private long version;
}
