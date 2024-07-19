package nelsonssoares.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import nelsonssoares.domain.dto.OpportunityDTO;
import nelsonssoares.service.ReportService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {
    @Override
    public ByteArrayOutputStream generayeCSVOppotunityReport() {
        return null;
    }

    @Override
    public List<OpportunityDTO> getOpportunityData() {
        return List.of();
    }
}
