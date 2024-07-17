package nelsonssoares.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nelsonssoares.domain.dto.OpportunityDTO;
import nelsonssoares.domain.dto.ProposalDTO;
import nelsonssoares.domain.dto.QuotationDTO;
import nelsonssoares.domain.entities.OpportunityEntity;
import nelsonssoares.domain.entities.QuotationEntity;
import nelsonssoares.domain.repository.OpportunityRepository;
import nelsonssoares.domain.repository.QuotationRepository;
import nelsonssoares.service.OpportunityService;
import nelsonssoares.utils.CSVHelper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
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
    @Transactional
    @Override
    public void saveQuotation(QuotationDTO quotationDTO) {

        QuotationEntity createQuotation = new QuotationEntity();
        createQuotation.setDate(new Date());
        createQuotation.setCurrencyPrice(quotationDTO.getCurrencyPrice());

        quotationRepository.persist(createQuotation);
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return List.of();
    }

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {

        List<OpportunityDTO> opportunityList = new ArrayList<>();
        opportunityRepository.findAll().list().forEach(item ->{
            opportunityList.add(OpportunityDTO.builder()
                        .proposalId(item.getProposalId())
                        .priceTonne(item.getPriceTonne())
                        .customer(item.getCustomer())
                        .lastDollarQuotation(item.getLastDollarQuotation())
                    .build());
        });


        return CSVHelper.opportunitiesToCSV(opportunityList);
    }
}
