package nelsonssoares.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nelsonssoares.service.OpportunityService;

@Path("/api/opportunity")
public class OpportunityController {

    @Inject
    OpportunityService reportService;

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        try{
            return Response.ok(reportService.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=opportunity-report.csv")

                    .build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

}
