package nelsonssoares.outlayers.entrypoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.ProposalDetailsDTO;
import nelsonssoares.service.ProposalService;

@ApplicationScoped
@Path("/api/trade")
public class ProposalController {

    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user","manager"})
    public Response getProposalDetailsById(@PathParam("id") long id) {
       try{
              return Response.ok(proposalService.findFullProposal(id), MediaType.APPLICATION_JSON).build();
       }catch (Exception e){
              return Response.serverError().build();
       }
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {

        int status = proposalService.createNewProposal(proposalDetailsDTO).getStatus();
        if(status > 199 || status < 205){

            return Response.ok().build();

        }else {
            return Response.status(status).build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id) {
        int status = proposalService.removeProposal(id).getStatus();

        if(status > 199 || status < 205){

            return Response.ok().build();

        }else {
            return Response.status(status).build();
        }
    }

}
