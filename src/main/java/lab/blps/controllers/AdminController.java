package lab.blps.controllers;

import jakarta.validation.Valid;
import lab.blps.dto.MessageResponseDto;
import lab.blps.security.dto.request.AddAmountRequestDto;
import lab.blps.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    @PostMapping("/add-amount-request")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAmountRequest(@Valid @RequestBody AddAmountRequestDto addAmountRequestDto) {
        userService.addAmountRequest(
            addAmountRequestDto.getUserId(),
            addAmountRequestDto.getAmountRequest()
        );
        return ResponseEntity.ok(new MessageResponseDto("Пользователю успешно добавлено количество запросов!"));
    }
}
