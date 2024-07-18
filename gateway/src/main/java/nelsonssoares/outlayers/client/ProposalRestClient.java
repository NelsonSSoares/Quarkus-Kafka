package nelsonssoares.outlayers.client;

import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.ProposalDetailsDTO;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/proposal")
@RegisterRestClient
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
@RegisterClientHeaders
@ApplicationScoped
public interface ProposalRestClient {

    @GET
    @Path("/{id}")
    ProposalDetailsDTO getProposalDetailsById(@PathParam("id") long id);

    @POST
    Response createProposal(ProposalDetailsDTO proposalDetailsDTO);

    @DELETE
    @Path("/{id}")
    Response removeProposal(@PathParam("id") long id);

}
