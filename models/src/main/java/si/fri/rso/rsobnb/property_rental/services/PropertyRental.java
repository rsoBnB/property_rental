package si.fri.rso.rsobnb.property_rental;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "property_rental")
@NamedQueries(value =
        {
                @NamedQuery(name = "PropertyRental.getAll", query = "SELECT r FROM property_rental r"),
                @NamedQuery(name = "PropertyRental.findByUser", query = "SELECT r FROM property_rental r WHERE r.realEstateId = " + ":realEstateId")
        })
@UuidGenerator(name = "idGenerator")
public class PropertyRental {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "realestate_id")
    private String realEstateId;

    @Column(name = "renter_id")
    private String renterId;

    private Integer duration;

    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(String realEstateId) {
        this.realEstateId = realEstateId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}