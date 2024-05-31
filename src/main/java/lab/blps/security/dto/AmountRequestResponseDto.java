package lab.blps.security.dto;

import lombok.Data;

@Data
public class AmountRequestResponseDto {
    private Long userId;
    private Integer amountRequest;
    public AmountRequestResponseDto(Long userId, Integer amountRequest) {
        this.userId = userId;
        this.amountRequest = amountRequest;
    }
}
