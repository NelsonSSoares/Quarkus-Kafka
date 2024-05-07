package nelsonssoares.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nelsonssoares.domain.dto.ProposalDTO;
import nelsonssoares.domain.dto.ProposalDetailsDTO;
import nelsonssoares.domain.entity.ProposalEntity;
import nelsonssoares.domain.repository.ProposalRepository;
import nelsonssoares.message.KafkaEvent;
import nelsonssoares.service.ProposalService;

import java.util.Date;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaEvent;

    // se nao funcionar, retornar para a proposal parte 2
    @Inject
    ObjectMapper objectMapper;

    @Override
    public ProposalDetailsDTO findFullProposal(long id) {

        ProposalEntity proposalEntity = proposalRepository.findById(id);
        return objectMapper.convertValue(proposalEntity, ProposalDetailsDTO.class);
    }

    @Override
    public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        ProposalDTO proposal = buildAndSaveNewProposal(proposalDetailsDTO);
        kafkaEvent.sendNewKafkaEvent(proposal);
    }


    @Override
    @Transactional
    public void removeProposal(long id) {
        proposalRepository.deleteById(id);
    }


    private ProposalDTO buildAndSaveNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        ProposalEntity proposalEntity = objectMapper.convertValue(proposalDetailsDTO, ProposalEntity.class);
        proposalEntity.setCreated(new Date());
        proposalRepository.persist(proposalEntity);
        return objectMapper.convertValue(proposalEntity, ProposalDTO.class);
    }
}
