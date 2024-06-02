package lab.blps.main.services.entities.map;

import lab.blps.main.bd.entites.enums.TaxFeatureEnum;
import lab.blps.main.bd.entites.enums.TaxpayerCategoryEnum;
import lab.blps.main.dto.TaxRegimeAddRequestDto;
import lab.blps.main.services.entities.TaxRegimeAddRequest;

import java.util.ArrayList;
import java.util.List;

public class MapTaxRegimeAddRequest {
    public static TaxRegimeAddRequest mapFromDto(TaxRegimeAddRequestDto taxRegimeAddRequestDto) {
        TaxRegimeAddRequest taxRegimeAddRequest = new TaxRegimeAddRequest();
        List<TaxpayerCategoryEnum> taxpayerCategories = new ArrayList<>();
        for (String taxpayerCategory : taxRegimeAddRequestDto.getTaxpayerCategories()) {
            taxpayerCategories.add(TaxpayerCategoryEnum.valueOf(taxpayerCategory.toUpperCase()));
        }
        taxRegimeAddRequest.setTaxpayerCategories(taxpayerCategories);
        List<TaxFeatureEnum> taxFeatures = new ArrayList<>();
        for (String taxFeature : taxRegimeAddRequestDto.getTaxFeatures()) {
            taxFeatures.add(TaxFeatureEnum.valueOf(taxFeature.toUpperCase()));
        }
        taxRegimeAddRequest.setTaxFeatures(taxFeatures);
        taxRegimeAddRequest.setTitle(taxRegimeAddRequestDto.getTitle());
        taxRegimeAddRequest.setDescription(taxRegimeAddRequestDto.getDescription());
        taxRegimeAddRequest.setMaxAnnualIncomeThousands(taxRegimeAddRequestDto.getMaxAnnualIncomeThousands());
        taxRegimeAddRequest.setMaxNumberEmployees(taxRegimeAddRequestDto.getMaxNumberEmployees());
        return taxRegimeAddRequest;
    }
}
