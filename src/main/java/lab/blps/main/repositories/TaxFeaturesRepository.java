package lab.blps.main.repositories;

import lab.blps.main.bd.entites.TaxFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxFeaturesRepository extends JpaRepository<TaxFeatures, Long> {}

