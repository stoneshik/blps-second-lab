package lab.blps.controllers;

import jakarta.validation.Valid;
import lab.blps.services.MapTaxRegimeWithFeaturesAndCategory;
import lab.blps.services.choice.ChoiceTaxRegimeService;
import lab.blps.services.choice.MapTaxRegimeChoice;
import lab.blps.services.dto.TaxRegimeChoiceDto;
import lab.blps.services.dto.TaxRegimeWithFeaturesAndCategoryDto;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChoiceTaxRegimeController {
    private final ChoiceTaxRegimeService choiceTaxRegimeService;

    @GetMapping(
            path = "/api/choice-tax-regime",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> choiceTaxRegime(@Valid @RequestBody TaxRegimeChoiceDto taxRegimeChoiceDto) {
        TaxRegimeChoice taxRegimeChoice;
        try {
            taxRegimeChoice = MapTaxRegimeChoice.mapFromDto(taxRegimeChoiceDto);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(
                    new ResponseMessageWrapper("Неправильный формат запроса"),
                    HttpStatus.BAD_REQUEST
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseMessageWrapper("Передана неправильная константа"),
                    HttpStatus.BAD_REQUEST
            );
        }
        List<TaxRegimeWithFeaturesAndCategory> taxRegimes = choiceTaxRegimeService.choice(taxRegimeChoice);
        List<TaxRegimeWithFeaturesAndCategoryDto> taxRegimeDtoList = new ArrayList<>();
        for (TaxRegimeWithFeaturesAndCategory taxRegime : taxRegimes) {
            taxRegimeDtoList.add(MapTaxRegimeWithFeaturesAndCategory.mapToDto(taxRegime));
        }
        return new ResponseEntity<>(taxRegimeDtoList, HttpStatus.OK);
    }
}
