package nelsonssoares.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nelsonssoares.client.CurrencyPriceClient;
import nelsonssoares.domain.dto.CurrencyPriceDTO;
import nelsonssoares.domain.dto.QuotationDTO;
import nelsonssoares.domain.entities.QuotationEntity;
import nelsonssoares.domain.repository.QuotationRepository;
import nelsonssoares.message.KafkaEvents;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class QuotationService {

    @Inject
    @RestClient
    CurrencyPriceClient currencyPriceClient;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;

    public void getCurrencyPrice(){
        CurrencyPriceDTO currencyPriceInfo = currencyPriceClient.getPriceByPair("USD-BRL");

        if(updateInfoPrice(currencyPriceInfo)){
            kafkaEvents.sendNewKafkaEvent(QuotationDTO
                    .builder()
                    .currencyPrice(String.valueOf(new BigDecimal(currencyPriceInfo.getUSDBRL().getBid())))
                    .date(new Date())
                    .build());
        }

    }

    private boolean updateInfoPrice(CurrencyPriceDTO currencyInfo) {

        BigDecimal currentPrice = new BigDecimal(currencyInfo.getUSDBRL().getBid());
        boolean updated = false;
        //AtomicBoolean updated = new AtomicBoolean(false);

        List<QuotationEntity> quotationList = quotationRepository.findAll().list();

        if (quotationList.isEmpty()) {
            saveQuotation(currencyInfo);

            updated = true;
        } else {
            QuotationEntity lastQuotation = quotationList.get(quotationList.size() - 1);
            if (currentPrice.floatValue() > lastQuotation.getCurrencyPrice().floatValue()) {

                updated = true;
                saveQuotation(currencyInfo);
            }
        }

        return updated;
    }
    private void saveQuotation(CurrencyPriceDTO currencyinfo) {
        QuotationEntity quotation = new QuotationEntity();

        quotation.setCurrencyPrice(new BigDecimal(currencyinfo.getUSDBRL().getBid()));
        quotation.setDate(new Date());
        quotation.setPctChange(currencyinfo.getUSDBRL().getPctChange());
        quotation.setPair("USD-BRL");

        quotationRepository.persist(quotation);
    }
}
