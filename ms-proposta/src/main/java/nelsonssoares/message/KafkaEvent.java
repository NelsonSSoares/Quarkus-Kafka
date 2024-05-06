package nelsonssoares.message;

import jakarta.enterprise.context.ApplicationScoped;
import nelsonssoares.domain.dto.ProposalDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvent {

    private final Logger logger = LoggerFactory.getLogger(KafkaEvent.class);

    @Channel("proposal")
    Emitter<ProposalDTO> proposalRequest;

    public void sendNewKafkaEvent(ProposalDTO proposalDTO) {
        logger.info("Sending message to Kafka topic: {}", proposalDTO);
        proposalRequest.send(proposalDTO).toCompletableFuture().join();
    }
}
