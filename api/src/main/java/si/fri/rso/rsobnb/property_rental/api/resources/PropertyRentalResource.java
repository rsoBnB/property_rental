package si.fri.rso.rsobnb.property_rental.api.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.rsobnb.property_rental.PropertyRental;
import si.fri.rso.rsobnb.property_rental.services.PropertyRentalBean;
import si.fri.rso.rsobnb.property_rental.api.configuration.RestProperties;
import org.eclipse.microprofile.metrics.annotation.Metered;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@RequestScoped
@Path("/property_rental")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class PropertyRentalResource {

    @Inject
    private PropertyRentalBean propertyRentalBean;

    @Inject
    private RestProperties restProperties;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Metered
    @Log
    public Response getPropertyRental() {

        List<PropertyRental> propertyRentals = propertyRentalBean.getPropertyRentals(uriInfo);

        return Response.ok(propertyRentals).build();
    }

    @GET
    @Path("/filtered")
    @Log
    public Response getPropertyRentalFiltered() {

        List<PropertyRental> propertyRentals;

        propertyRentals = propertyRentalBean.getPropertyRentalFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(propertyRentals).build();
    }

    @GET
    @Path("/{propertyRentalId}")
    @Log
    public Response getPropertyRental(@PathParam("propertyRentalId") String propertyRentalId) {

        PropertyRental propertyRental = propertyRentalBean.getPropertyRental(propertyRentalId);

        if (propertyRental == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(propertyRental).build();
    }

    @POST
    @Log
    public Response createPropertyRental(PropertyRental propertyRental) {

        if ((propertyRental.getPropertyId() == null || propertyRental.getPropertyId().isEmpty()) || (propertyRental.getRenterId() == null
                || propertyRental.getRenterId().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            propertyRental = propertyRentalBean.createdPropertyRental(propertyRental);
        }

        if (propertyRental.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(propertyRental).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(propertyRental).build();
        }
    }

    @PUT
    @Path("{propertyRentalId}")
    public Response putPropertyRental(@PathParam("propertyRentalId") String propertyRentalId, PropertyRental propertyRental) {

        propertyRental = propertyRentalBean.putPropertyRental(propertyRentalId, propertyRental);

        if (propertyRental == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (propertyRental.getId() != null)
                return Response.status(Response.Status.OK).entity(propertyRental).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{propertyRentalId}")
    public Response deleteUser(@PathParam("propertyRentalId") String propertyRentalId) {

        boolean deleted = propertyRentalBean.deletePropertyRental(propertyRentalId);

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
