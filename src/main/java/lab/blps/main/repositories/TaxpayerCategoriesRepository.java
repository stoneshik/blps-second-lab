package lab.blps.main.repositories;

import lab.blps.main.bd.entites.TaxpayerCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxpayerCategoriesRepository extends JpaRepository<TaxpayerCategories, Long> {}
