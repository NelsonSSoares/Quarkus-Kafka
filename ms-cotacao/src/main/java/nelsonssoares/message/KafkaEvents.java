package nelsonssoares.message;

import jakarta.enterprise.context.ApplicationScoped;
import nelsonssoares.domain.dto.QuotationDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {
    private final Logger logger = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("quotation-channel")
    Emitter<QuotationDTO> quotationRequest;

    public void sendNewKafkaEvent(QuotationDTO quotationDTO) {
        logger.info("Sending message to Kafka topic: {}", quotationDTO);
        quotationRequest.send(quotationDTO).toCompletableFuture().join();
    }

}
