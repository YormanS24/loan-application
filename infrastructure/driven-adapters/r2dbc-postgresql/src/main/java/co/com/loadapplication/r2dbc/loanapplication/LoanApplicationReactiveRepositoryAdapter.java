package co.com.loadapplication.r2dbc.loanapplication;

import co.com.loadapplication.model.loanapplication.LoanApplication;
import co.com.loadapplication.model.loanapplication.gateways.LoanApplicationRepository;
import co.com.loadapplication.r2dbc.entity.LoanApplicationEntity;
import co.com.loadapplication.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class LoanApplicationReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        LoanApplication,
        LoanApplicationEntity,
        Long,
        LoanApplicationReactiveRepository
        > implements LoanApplicationRepository {
    public LoanApplicationReactiveRepositoryAdapter(LoanApplicationReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, LoanApplication.class));
    }

    @Override
    public Mono<Void> saveLoanApplication(LoanApplication loanApplication) {
        return repository.save(this.toData(loanApplication))
                .then();
    }
}
