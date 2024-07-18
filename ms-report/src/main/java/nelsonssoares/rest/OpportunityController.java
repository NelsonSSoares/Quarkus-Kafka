package nelsonssoares.rest;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.OpportunityDTO;
import nelsonssoares.service.OpportunityService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/api/opportunity")
@Authenticated
public class OpportunityController {

    @Inject
    OpportunityService reportService;

    @Inject
    JsonWebToken jwt;


    @GET
    @Path("/data")
    @RolesAllowed({"user","manager"})
    public List<OpportunityDTO> generateReport(){
        return reportService.generateOpportunityData();
    }

}
