package lab.blps.services.entities;

import lab.blps.bd.entites.enums.TaxFeatureEnum;
import lab.blps.bd.entites.enums.TaxpayerCategoryEnum;
import lombok.Data;

import java.util.List;

@Data
public class TaxRegimeChoice {
    private List<TaxpayerCategoryEnum> taxpayerCategories;
    private List<TaxFeatureEnum> taxFeatures;
    private Long maxAnnualIncomeThousands;
    private Long maxNumberEmployees;
}
