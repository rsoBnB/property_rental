package si.fri.rso.rsobnb.property_rental.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

import si.fri.rso.rsobnb.property_rental.PropertyRental;

@ApplicationScoped
public class PropertyRentalBean {

    private Logger log = Logger.getLogger(PropertyRentalBean.class.getName());

    @Inject
    private EntityManager em;

    public List<PropertyRental> getPropertyRentals(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, PropertyRental.class, queryParameters);

    }

    public List<PropertyRental> getPropertyRentalFilter(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(em, PropertyRental.class, queryParameters);
    }

    public PropertyRental getPropertyRental(String propertyRentalId) {


        PropertyRental propertyRental = em.find(PropertyRental.class, propertyRentalId);

        if (propertyRental == null) {
            throw new NotFoundException();
        }

        return propertyRental;
    }


    public PropertyRental createdPropertyRental(PropertyRental propertyRental) {

        try {
            beginTx();
            em.persist(propertyRental);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return propertyRental;
    }

    public PropertyRental putPropertyRental(String propertyRentalId, PropertyRental propertyRental) {

        PropertyRental r = em.find(PropertyRental.class, propertyRentalId);

        if (r == null) {
            return null;
        }

        try {
            beginTx();
            propertyRental.setId(r.getId());
            propertyRental = em.merge(propertyRental);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return propertyRental;
    }

    public boolean deletePropertyRental(String propertyRentalId) {

        PropertyRental propertyRental = em.find(PropertyRental.class, propertyRentalId);

        if (propertyRental != null) {
            try {
                beginTx();
                em.remove(propertyRental);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
