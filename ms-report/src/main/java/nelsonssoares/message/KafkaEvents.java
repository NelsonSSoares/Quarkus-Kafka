package nelsonssoares.message;


import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nelsonssoares.domain.dto.ProposalDTO;
import nelsonssoares.domain.dto.QuotationDTO;
import nelsonssoares.service.OpportunityService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaEvents {

    private final Logger logger = LoggerFactory.getLogger(KafkaEvents.class);

    @Inject
    OpportunityService opportunityService;


    @Incoming("proposal")
    @Transactional
    public void consumeProposal(ProposalDTO proposal) {
        logger.info("Consuming proposal from Kafka topic: {}", proposal);
        opportunityService.buildOpportunity(proposal);
    }

    @Incoming("quotation")
    @Blocking
    public void consumeQuotation(QuotationDTO quotationDTO) {
        logger.info("Consuming quotation from Kafka topic: {}", quotationDTO);
        opportunityService.saveQuotation(quotationDTO);
    }

}
