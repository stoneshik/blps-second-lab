package lab.blps.main.services;

import lab.blps.main.bd.entites.TaxFeatures;
import lab.blps.main.bd.entites.TaxRegimes;
import lab.blps.main.bd.entites.TaxpayerCategories;
import lab.blps.main.bd.entites.enums.TaxFeatureEnum;
import lab.blps.main.bd.entites.enums.TaxpayerCategoryEnum;
import lab.blps.main.repositories.TaxFeaturesRepository;
import lab.blps.main.repositories.TaxRegimesRepository;
import lab.blps.main.repositories.TaxpayerCategoriesRepository;
import lab.blps.main.services.entities.TaxRegimeAddRequest;
import lab.blps.main.services.entities.TaxRegimeUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrudTaxRegimeService {
    private final TaxRegimesRepository taxRegimesRepository;
    private final TaxFeaturesRepository taxFeaturesRepository;
    private final TaxpayerCategoriesRepository taxpayerCategoriesRepository;

    @Transactional
    public void create(TaxRegimeAddRequest taxRegimeAddRequest) {
        TaxRegimes taxRegimes = new TaxRegimes();
        taxRegimes.setTitle(taxRegimeAddRequest.getTitle());
        taxRegimes.setDescription(taxRegimeAddRequest.getDescription());
        taxRegimes.setMaxAnnualIncomeThousands(taxRegimeAddRequest.getMaxAnnualIncomeThousands());
        taxRegimes.setMaxNumberEmployees(taxRegimeAddRequest.getMaxNumberEmployees());
        TaxRegimes newTaxRegime = taxRegimesRepository.saveAndFlush(new TaxRegimes());
        if (taxRegimeAddRequest.getTaxFeatures() != null) {
            saveTaxFeatures(newTaxRegime, taxRegimeAddRequest.getTaxFeatures());
        }
        if (taxRegimeAddRequest.getTaxpayerCategories() != null) {
            saveTaxpayerCategories(newTaxRegime, taxRegimeAddRequest.getTaxpayerCategories());
        }
    }

    @Transactional
    public void update(TaxRegimeUpdateRequest taxRegimeUpdateRequest) {
        TaxRegimes taxRegimes = new TaxRegimes();
        taxRegimes.setId(taxRegimeUpdateRequest.getId());
        taxRegimes.setTitle(taxRegimeUpdateRequest.getTitle());
        taxRegimes.setDescription(taxRegimeUpdateRequest.getDescription());
        taxRegimes.setMaxAnnualIncomeThousands(taxRegimeUpdateRequest.getMaxAnnualIncomeThousands());
        taxRegimes.setMaxNumberEmployees(taxRegimeUpdateRequest.getMaxNumberEmployees());
        TaxRegimes newTaxRegime = taxRegimesRepository.saveAndFlush(new TaxRegimes());
        if (taxRegimeUpdateRequest.getTaxFeatures() != null) {
            saveTaxFeatures(newTaxRegime, taxRegimeUpdateRequest.getTaxFeatures());
        }
        if (taxRegimeUpdateRequest.getTaxpayerCategories() != null) {
            saveTaxpayerCategories(newTaxRegime, taxRegimeUpdateRequest.getTaxpayerCategories());
        }
    }

    public void delete(Long taxRegimeId) {
        taxRegimesRepository.delete(taxRegimeId);
    }

    private void saveTaxFeatures(TaxRegimes newTaxRegime, List<TaxFeatureEnum> taxFeaturesEnum) {
        for (TaxFeatureEnum taxFeatureEnum : taxFeaturesEnum) {
            TaxFeatures taxFeatures = new TaxFeatures();
            taxFeatures.setTaxRegimes(newTaxRegime);
            taxFeatures.setTaxFeatureEnum(taxFeatureEnum);
            taxFeaturesRepository.save(taxFeatures);
        }
    }

    private void saveTaxpayerCategories(TaxRegimes newTaxRegime, List<TaxpayerCategoryEnum> taxpayerCategoryEnums) {
        for (TaxpayerCategoryEnum taxpayerCategoryEnum : taxpayerCategoryEnums) {
            TaxpayerCategories taxpayerCategories = new TaxpayerCategories();
            taxpayerCategories.setTaxRegimes(newTaxRegime);
            taxpayerCategories.setTaxpayerCategoryEnum(taxpayerCategoryEnum);
            taxpayerCategoriesRepository.save(taxpayerCategories);
        }
    }
}
