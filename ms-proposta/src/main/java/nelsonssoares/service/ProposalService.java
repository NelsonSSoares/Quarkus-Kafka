package nelsonssoares.service;

import jakarta.enterprise.context.ApplicationScoped;
import nelsonssoares.domain.dto.ProposalDetailsDTO;

@ApplicationScoped
public interface ProposalService {
    ProposalDetailsDTO findFullProposal(long id);
    void createNewProposal(ProposalDetailsDTO proposalDetailsDTO);
    void removeProposal(long id);
}
