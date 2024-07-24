package nelsonssoares.outlayers.entrypoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nelsonssoares.service.ReportService;

import java.util.Date;

@ApplicationScoped
@Path("/api/opportunity")
public class ReportController {

    @Inject
    ReportService reportService;

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed({"user","manager"})
    public Response generateReport(){
      try{
            return Response.ok(reportService.generayeCSVOppotunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment; filename="+ new Date() + "opportunity-report.csv")
                    .build();
      }catch (Exception e){
          return Response.serverError().build();
      }
    }

    @GET
    @Path("/data")
    @RolesAllowed({"user","manager"})
    public Response generateOpportunityData(){
        try{
            return Response.ok(reportService.getOpportunityData(), MediaType.APPLICATION_JSON).build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }
}


