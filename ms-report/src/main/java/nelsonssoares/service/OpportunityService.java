package nelsonssoares.service;

import jakarta.enterprise.context.ApplicationScoped;
import nelsonssoares.domain.dto.OpportunityDTO;
import nelsonssoares.domain.dto.ProposalDTO;
import nelsonssoares.domain.dto.QuotationDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface OpportunityService {
    void buildOpportunity(ProposalDTO proposalDTO);
    void saveQuotation(QuotationDTO quotationDTO);
    List<OpportunityDTO> generateOpportunityData();
    ByteArrayInputStream generateCSVOpportunityReport();

}
