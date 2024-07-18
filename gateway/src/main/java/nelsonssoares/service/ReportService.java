package nelsonssoares.service;

import nelsonssoares.domain.dto.OpportunityDTO;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface ReportService {
    ByteArrayOutputStream generayeCSVOppotunityReport();

    List<OpportunityDTO> getOpportunityData();
}
