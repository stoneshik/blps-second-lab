package lab.blps.controllers;

import jakarta.validation.Valid;
import lab.blps.dto.MessageResponseDto;
import lab.blps.exceptions.IncorrectEnumConstant;
import lab.blps.exceptions.WrongFormatUserRequestException;
import lab.blps.main.dto.TaxRegimeAddRequestDto;
import lab.blps.main.dto.TaxRegimeDeleteRequestDto;
import lab.blps.main.dto.TaxRegimeUpdateRequestDto;
import lab.blps.main.services.CrudTaxRegimeService;
import lab.blps.main.services.entities.TaxRegimeAddRequest;
import lab.blps.main.services.entities.TaxRegimeUpdateRequest;
import lab.blps.main.services.entities.map.MapTaxRegimeAddRequest;
import lab.blps.main.services.entities.map.MapTaxRegimeUpdateRequest;
import lab.blps.security.dto.request.AddAmountRequestDto;
import lab.blps.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final CrudTaxRegimeService crudTaxRegimeService;

    @PostMapping("/amount-request/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAmountRequest(@Valid @RequestBody AddAmountRequestDto addAmountRequestDto) {
        userService.addAmountRequest(
            addAmountRequestDto.getUserId(),
            addAmountRequestDto.getAmountRequest()
        );
        return ResponseEntity.ok(new MessageResponseDto("Пользователю успешно добавлено количество запросов!"));
    }

    @PostMapping("/tax-regime/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTaxRegime(@Valid @RequestBody TaxRegimeAddRequestDto taxRegimeAddRequestDto) {
        TaxRegimeAddRequest taxRegimeAddRequest;
        try {
            taxRegimeAddRequest = MapTaxRegimeAddRequest.mapFromDto(taxRegimeAddRequestDto);
        } catch (NullPointerException e) {
            throw new WrongFormatUserRequestException("Error: Incorrect format of tax regime");
        } catch (IllegalArgumentException e) {
            throw new IncorrectEnumConstant("Error: Incorrect constant value in tax regime");
        }
        crudTaxRegimeService.create(taxRegimeAddRequest);
        return ResponseEntity.ok(new MessageResponseDto("Пользователю успешно добавлено количество запросов!"));
    }

    @PostMapping("/tax-regime/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTaxRegime(@Valid @RequestBody TaxRegimeUpdateRequestDto taxRegimeUpdateRequestDto) {
        TaxRegimeUpdateRequest taxRegimeUpdateRequest;
        try {
            taxRegimeUpdateRequest = MapTaxRegimeUpdateRequest.mapFromDto(taxRegimeUpdateRequestDto);
        } catch (NullPointerException e) {
            throw new WrongFormatUserRequestException("Error: Incorrect format of tax regime");
        } catch (IllegalArgumentException e) {
            throw new IncorrectEnumConstant("Error: Incorrect constant value in tax regime");
        }
        crudTaxRegimeService.update(taxRegimeUpdateRequest);
        return ResponseEntity.ok(new MessageResponseDto("Информация об налоговом режиме успешно добавлена!"));
    }

    @DeleteMapping("/tax-tegime/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTaxRegime(@Valid @RequestBody TaxRegimeDeleteRequestDto taxRegimeDeleteRequestDto) {
        crudTaxRegimeService.delete(taxRegimeDeleteRequestDto.getId());
        return ResponseEntity.ok(new MessageResponseDto("Информация об налоговом режиме успешно удалена!"));
    }
}
