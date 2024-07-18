package nelsonssoares.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.ProposalDetailsDTO;
import nelsonssoares.service.ProposalService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/api/proposal")
@Authenticated
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProposalController {
    private final Logger logger = LoggerFactory.getLogger(ProposalController.class);

    @Inject
    ProposalService proposalService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user","manager"})
    public ProposalDetailsDTO findDetailsProposal(@PathParam("id") Long id){
        logger.info("Getting proposal by id: {}", id);
        return proposalService.findFullProposal(id);
    }

    @POST
    @RolesAllowed("proposal-customer")
    @Transactional
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        logger.info("Creating new proposal: {}", proposalDetailsDTO);
        try {
            proposalService.createNewProposal(proposalDetailsDTO);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Error creating proposal: {}", e.getMessage());
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("manager")
    @Transactional
    public Response removeProposal(@PathParam("id") Long id) {
        logger.info("Removing proposal by id: {}", id);
        try {
            proposalService.removeProposal(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Error removing proposal: {}", e.getMessage());
            return Response.serverError().build();
        }
    }



}
