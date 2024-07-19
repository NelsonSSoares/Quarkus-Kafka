package nelsonssoares.service;

import nelsonssoares.domain.dto.OpportunityDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportService {
    ByteArrayInputStream generayeCSVOppotunityReport();

    List<OpportunityDTO> getOpportunityData();
}
