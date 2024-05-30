package lab.blps.services.choice;

import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceTaxRegimeService {
    private final TaxRegimeRepository taxRegimeRepository;

    public List<TaxRegimeWithFeaturesAndCategory> choice(TaxRegimeChoice taxRegimeChoice) {
        ChoiceFilter chosenFilter = chooseFilter(taxRegimeChoice);
        return chosenFilter.filter(taxRegimeChoice);
    }

    private ChoiceFilter chooseFilter(TaxRegimeChoice taxRegimeChoice) {
        ChoiceFilter chosenFilter;
        if (taxRegimeChoice.getTaxpayerCategories().isEmpty()) {
            if (taxRegimeChoice.getTaxFeatures().isEmpty()) {
                chosenFilter = new ChoiceFilterOnlyByMaxAnnualIncomeThousandsAndMaxNumberEmployees(taxRegimeRepository);
            } else {
                chosenFilter = new ChoiceFilterByTaxFeatures(taxRegimeRepository);
            }
        } else {
            if (taxRegimeChoice.getTaxFeatures().isEmpty()) {
                chosenFilter = new ChoiceFilterByTaxpayerCategory(taxRegimeRepository);
            } else {
                chosenFilter = new ChoiceFilterByTaxpayerCategoryAndTaxFeature(taxRegimeRepository);
            }
        }
        return chosenFilter;
    }
}
