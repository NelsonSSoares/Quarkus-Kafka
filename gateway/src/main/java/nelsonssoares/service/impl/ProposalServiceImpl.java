package nelsonssoares.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.ProposalDetailsDTO;
import nelsonssoares.outlayers.client.ProposalRestClient;
import nelsonssoares.service.ProposalService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {

    @Inject
    @RestClient
    ProposalRestClient proposalRestClient;

    @Override
    public ProposalDetailsDTO findFullProposal(long id) {
        return null;
    }

    @Override
    public Response createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        return null;
    }

    @Override
    public Response removeProposal(long id) {
        return null;
    }
}
