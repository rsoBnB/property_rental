package si.fri.rso.rsobnb.property_rental.services;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService
public class PropertyRentalApplication extends Application {
}
