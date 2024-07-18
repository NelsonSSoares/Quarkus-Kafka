package nelsonssoares.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.ProposalDetailsDTO;

@ApplicationScoped
public interface ProposalService {
    ProposalDetailsDTO findFullProposal(@PathParam("id") long id);
    Response createNewProposal(ProposalDetailsDTO proposalDetailsDTO);
    Response removeProposal(long id);
}
