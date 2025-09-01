package co.com.loadapplication.model.loanapplication.gateways;

import co.com.loadapplication.model.loanapplication.LoanApplication;
import reactor.core.publisher.Mono;

public interface LoanApplicationRepository {
    Mono<Void> saveLoanApplication(LoanApplication loanApplication);
}
