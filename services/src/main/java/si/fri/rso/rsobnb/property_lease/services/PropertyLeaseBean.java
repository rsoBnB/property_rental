package si.fri.rso.rsobnb.property_lease.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.rsobnb.property_lease.PropertyLease;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class PropertyLeaseBean {

    private Logger log = Logger.getLogger(PropertyLeaseBean.class.getName());

    @Inject
    private EntityManager em;

    public List<PropertyLease> getPropertyLease(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, PropertyLease.class, queryParameters);

    }

    public List<PropertyLease> getPropertyLeaseFilter(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(em, PropertyLease.class, queryParameters);
    }

    public PropertyLease getPropertyLease(String propertyLeaseId) {


        PropertyLease propertyLease = em.find(PropertyLease.class, propertyLeaseId);

        if (propertyLease == null) {
            throw new NotFoundException();
        }

        return propertyLease;
    }


    public PropertyLease createdPropertyLease(PropertyLease propertyLease) {

        try {
            beginTx();
            em.persist(propertyLease);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return propertyLease;
    }

    public PropertyLease putPropertyLease(String propertyLeaseId, PropertyLease propertyLease) {

        PropertyLease r = em.find(PropertyLease.class, propertyLeaseId);

        if (r == null) {
            return null;
        }

        try {
            beginTx();
            propertyLease.setId(r.getId());
            propertyLease = em.merge(propertyLease);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return propertyLease;
    }

    public boolean deletePropertyLease(String propertyLeaseId) {

        PropertyLease propertyLease = em.find(PropertyLease.class, propertyLeaseId);

        if (propertyLease != null) {
            try {
                beginTx();
                em.remove(propertyLease);
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
