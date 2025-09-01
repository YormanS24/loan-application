package co.com.loadapplication.api;

import co.com.loadapplication.api.dto.LoanApplicationRequest;
import co.com.loadapplication.api.mapper.LoanApplicationMapper;
import co.com.loadapplication.api.utils.ValidateDtos;
import co.com.loadapplication.usecase.loanapplication.LoanApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final LoanApplicationUseCase loanApplicationUseCase;
    private final LoanApplicationMapper loanApplicationMapper;
    private final ValidateDtos validateDtos;

    public Mono<ServerResponse> createLoanApplication(ServerRequest request) {
        return validateDtos.validate(request, LoanApplicationRequest.class)
                .flatMap(loanApplicationRequest -> loanApplicationUseCase.createLoanApplication(loanApplicationMapper.toModel(loanApplicationRequest))
                        .then(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("{\"message\": \"Solicitud creada con exito\"}")));
    }
}
