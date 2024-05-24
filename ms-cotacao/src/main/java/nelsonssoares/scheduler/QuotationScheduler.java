package nelsonssoares.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nelsonssoares.message.KafkaEvents;
import nelsonssoares.service.QuotationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class QuotationScheduler {

    private final Logger logger = LoggerFactory.getLogger(KafkaEvents.class);

    @Inject
    QuotationService quotationService;

    @Transactional
    @Scheduled(every="35s", identity = "task-job")
    void schedule(){
        logger.info("Executing Scheduler...");

        quotationService.getCurrencyPrice();
    }

}
