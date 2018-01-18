package si.fri.rso.rsobnb.property_lease.api.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.rsobnb.property_lease.PropertyLease;
import si.fri.rso.rsobnb.property_lease.api.configuration.RestProperties;
import org.eclipse.microprofile.metrics.annotation.Metered;
import si.fri.rso.rsobnb.property_lease.services.PropertyLeaseBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@RequestScoped
@Path("/property_lease")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class PropertyLeaseResource {

    @Inject
    private PropertyLeaseBean propertyLeaseBean;

    @Inject
    private RestProperties restProperties;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Metered
    @Log
    public Response getPropertyLease() {

        List<PropertyLease> propertyLeases = propertyLeaseBean.getPropertyLease(uriInfo);

        return Response.ok(propertyLeases).build();
    }

    @GET
    @Path("/filtered")
    @Log
    public Response getPropertyLeaseFiltered() {

        List<PropertyLease> propertyLeases;

        propertyLeases = propertyLeaseBean.getPropertyLeaseFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(propertyLeases).build();
    }

    @GET
    @Path("/{propertyLeaseId}")
    @Log
    public Response getPropertyLease(@PathParam("propertyLeaseId") String propertyLeaseId) {

        PropertyLease propertyLease = propertyLeaseBean.getPropertyLease(propertyLeaseId);

        if (propertyLease == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(propertyLease).build();
    }

    @POST
    @Log
    public Response createPropertyLease(PropertyLease propertyLease) {

        if ((propertyLease.getRealEstateId() == null || propertyLease.getRealEstateId().isEmpty()) || (propertyLease.getRenterId() == null
                || propertyLease.getRenterId().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            propertyLease = propertyLeaseBean.createdPropertyLease(propertyLease);
        }

        if (propertyLease.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(propertyLease).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(propertyLease).build();
        }
    }

    @PUT
    @Path("{propertyLeaseId}")
    public Response putPropertyLease(@PathParam("propertyLeaseId") String propertyLeaseId, PropertyLease propertyLease) {

        propertyLease = propertyLeaseBean.putPropertyLease(propertyLeaseId, propertyLease);

        if (propertyLease == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (propertyLease.getId() != null)
                return Response.status(Response.Status.OK).entity(propertyLease).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{propertyLeaseId}")
    public Response deleteUser(@PathParam("propertyLeaseId") String propertyLeaseId) {

        boolean deleted = propertyLeaseBean.deletePropertyLease(propertyLeaseId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        return Response.ok().build();
    }

    @GET
    @Path("healthy")
    public Response getHealth() {
        restProperties.isHealthy();
        return Response.ok().entity(restProperties.isHealthy()).build();
    }
}
