package nelsonssoares.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nelsonssoares.domain.entities.OpportunityEntity;

@ApplicationScoped
public class OpportunityRepository implements PanacheRepository<OpportunityEntity> {
}
