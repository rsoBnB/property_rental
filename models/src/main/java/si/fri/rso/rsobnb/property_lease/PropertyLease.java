package si.fri.rso.rsobnb.property_lease;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@Entity(name = "property_lease")
@NamedQueries(value =
        {
                @NamedQuery(name = "PropertyLease.getAll", query = "SELECT r FROM property_lease r"),
                @NamedQuery(name = "PropertyLease.findByUser", query = "SELECT r FROM property_lease r WHERE r.realEstateId = " + ":realEstateId")
        })
@UuidGenerator(name = "idGenerator")
public class PropertyLease {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "realestate_id")
    private String realEstateId;

    @Column(name = "renter_id")
    private String renterId;

    @Column(name = "leaser_id")
    private String leaserId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "date")
    private String date;

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

    public String getLeaserId() {
        return leaserId;
    }

    public void setLeaserId(String leaserId) {
        this.leaserId = leaserId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}