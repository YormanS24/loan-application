package co.com.loadapplication.model.loantype.gateways;

import co.com.loadapplication.model.loantype.LoanType;
import reactor.core.publisher.Mono;

public interface LoanTypeRepository {
    Mono<LoanType> findLoanTypeById(Long id);
}
