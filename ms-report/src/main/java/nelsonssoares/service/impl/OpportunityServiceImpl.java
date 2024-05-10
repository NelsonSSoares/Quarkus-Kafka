package nelsonssoares.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nelsonssoares.domain.dto.OpportunityDTO;
import nelsonssoares.domain.dto.ProposalDTO;
import nelsonssoares.domain.dto.QuotationDTO;
import nelsonssoares.domain.entities.OpportunityEntity;
import nelsonssoares.domain.entities.QuotationEntity;
import nelsonssoares.domain.repository.OpportunityRepository;
import nelsonssoares.domain.repository.QuotationRepository;
import nelsonssoares.service.OpportunityService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    @Override
    public void buildOpportunity(ProposalDTO proposalDTO) {
        List<QuotationEntity> quotation = quotationRepository.findAll().list();
        Collections.reverse(quotation);

        OpportunityEntity opportunityEntity = new OpportunityEntity();
        opportunityEntity.setProposalId(proposalDTO.getProposalId());
        opportunityEntity.setCustomer(proposalDTO.getCustomer());
        opportunityEntity.setPriceTonne(proposalDTO.getPriceTonne());
        opportunityEntity.setLastDollarQuotation(quotation.get(0).getCurrencyPrice());
        opportunityEntity.setDate(new Date());

        opportunityRepository.persist(opportunityEntity);

    }

    @Override
    public void saveQuotation(QuotationDTO quotationDTO) {

    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return List.of();
    }
}
