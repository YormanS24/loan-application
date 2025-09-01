package co.com.loadapplication.api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

import static co.com.loadapplication.api.utils.Constants.*;

public record LoanApplicationRequest(

        @NotBlank(message = DOCUMENT_REQUIRED)
        @Size(min = 5, max = 20, message = DOCUMENT_SIZE)
        String documentNumber,

        @NotNull(message = AMOUNT_REQUIRED)
        @DecimalMin(value = "500000.00", message = AMOUNT_MIN)
        @Digits(integer = 15, fraction = 2, message = AMOUNT_INVALID)
        BigDecimal amount,

        @NotNull(message = TERM_REQUIRED)
        @Min(value = 3, message = TERM_MIN)
        @Max(value = 120, message = TERM_MAX)
        Integer term,

        @NotNull(message = LOAN_TYPE_REQUIRED)
        Long loanTypeId) {
}
