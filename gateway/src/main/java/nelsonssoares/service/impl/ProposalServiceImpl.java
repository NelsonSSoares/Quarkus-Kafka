package nelsonssoares.service.impl;

import jakarta.ws.rs.core.Response;
import nelsonssoares.domain.dto.ProposalDetailsDTO;
import nelsonssoares.service.ProposalService;

public class ProposalServiceImpl implements ProposalService {
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
