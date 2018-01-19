package si.fri.rso.rsobnb.property_rental.services.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.rso.rsobnb.property_rental.services.configuration.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@Health
@ApplicationScoped
public class PropertyLeaseServiceHealthCheck implements HealthCheck{

    @Inject
    private RestProperties restProperties;

    private Logger log = Logger.getLogger(PropertyLeaseServiceHealthCheck.class.getName());

    @Override
    public HealthCheckResponse call() {

        if (restProperties.isHealthy()) {
            return HealthCheckResponse.named(PropertyLeaseServiceHealthCheck.class.getSimpleName()).up().build();
        } else {
            return HealthCheckResponse.named(PropertyLeaseServiceHealthCheck.class.getSimpleName()).down().build();
        }

    }
}
