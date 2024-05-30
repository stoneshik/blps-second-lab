package lab.blps.repositories;

import lab.blps.bd.entites.TaxFeatures;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategories;
import lab.blps.bd.entites.enums.TaxFeatureEnum;
import lab.blps.bd.entites.enums.TaxpayerCategoryEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRegimeRepository extends CrudRepository<TaxRegime, Long> {
    @Query(
            "from TaxRegime tr where " +
            "(tr.maxAnnualIncomeThousands is null or :maxAnnualIncomeThousands <= tr.maxAnnualIncomeThousands) " +
            "and (tr.maxNumberEmployees is null or :maxNumberEmployees <= tr.maxNumberEmployees)"
    )
    List<TaxRegime> findByMaxAnnualIncomeThousandsAndMaxNumberEmployees(
            @Param("maxAnnualIncomeThousands") Long maxAnnualIncomeThousands,
            @Param("maxNumberEmployees") Long maxNumberEmployees
    );

    @Query(
            "from TaxRegime tr where " +
            "(tr.maxAnnualIncomeThousands is null or :maxAnnualIncomeThousands <= tr.maxAnnualIncomeThousands) " +
            "and (tr.maxNumberEmployees is null or :maxNumberEmployees <= tr.maxNumberEmployees)" +
            "and tr.id in :filteredTaxRegimeIds"
    )
    List<TaxRegime> findByMaxAnnualIncomeThousandsAndMaxNumberEmployeesAndFilteredTaxRegimeIds(
            @Param("maxAnnualIncomeThousands") Long maxAnnualIncomeThousands,
            @Param("maxNumberEmployees") Long maxNumberEmployees,
            @Param("filteredTaxRegimeIds") List<Long> filteredTaxRegimeIds
    );

    @Query(
            "from TaxpayerCategories tc where tc.taxpayerCategoryEnum in :taxpayerCategories"
    )
    List<TaxpayerCategories> findTaxpayerCategories(
            @Param("taxpayerCategories") List<TaxpayerCategoryEnum> taxpayerCategories
    );

    @Query(
            "from TaxFeatures tf where tf.taxFeatureEnum in :taxFeatures"
    )
    List<TaxFeatures> findTaxFeatures(
            @Param("taxFeatures") List<TaxFeatureEnum> taxFeatures
    );

    @Query(
            "from TaxFeatures tf where tf.taxFeatureEnum in :taxFeatures and tf.taxRegime.id in :filteredTaxRegimeIds"
    )
    List<TaxFeatures> findTaxFeaturesByFilteredTaxRegimeIds(
            @Param("taxFeatures") List<TaxFeatureEnum> taxFeatures,
            @Param("filteredTaxRegimeIds") List<Long> filteredTaxRegimeIds
    );
}
