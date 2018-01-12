package si.fri.rso.rsobnb.property_rental;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@Entity(name = "property_rental")
@NamedQueries(value =
        {
                @NamedQuery(name = "PropertyRental.getAll", query = "SELECT r FROM property_rental r"),
                @NamedQuery(name = "PropertyRental.findByUser", query = "SELECT r FROM property_rental r WHERE r.renterId = " + ":renterId")
        })
@UuidGenerator(name = "idGenerator")
public class PropertyRental {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "property_id")
    private String propertyId;

    @Column(name = "renter_id")
    private String renterId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "date")
    private String date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}