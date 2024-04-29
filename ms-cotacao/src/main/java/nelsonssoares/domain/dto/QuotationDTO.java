package nelsonssoares.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class QuotationDTO {
    private Date date;
    private String currencyPrice;
}
