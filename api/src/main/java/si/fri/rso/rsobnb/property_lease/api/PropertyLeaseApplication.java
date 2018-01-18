package si.fri.rso.rsobnb.property_lease.api;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService
public class PropertyLeaseApplication extends Application {
}
