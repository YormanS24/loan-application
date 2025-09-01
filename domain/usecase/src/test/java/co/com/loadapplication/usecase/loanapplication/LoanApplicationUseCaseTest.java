package co.com.loadapplication.usecase.loanapplication;

import co.com.loadapplication.model.gateway.Gateway;
import co.com.loadapplication.model.gateway.gateways.GatewayRepository;
import co.com.loadapplication.model.loanapplication.LoanApplication;
import co.com.loadapplication.model.loanapplication.gateways.LoanApplicationRepository;
import co.com.loadapplication.model.loantype.LoanType;
import co.com.loadapplication.model.loantype.gateways.LoanTypeRepository;
import co.com.loadapplication.model.status.gateways.StatusRepository;
import co.com.loadapplication.model.utils.LoanApplicationLogger;
import co.com.loadapplication.usecase.loanapplication.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanApplicationUseCaseTest {

    @Mock
    private LoanApplicationRepository loanApplicationRepository;

    @Mock
    private LoanTypeRepository loanTypeRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private LoanApplicationLogger logger;

    @Mock
    private GatewayRepository gatewayRepository;

    @InjectMocks
    private LoanApplicationUseCase loanApplicationUseCase;

    private LoanApplication loanApplication;
    private Gateway gatewayUser;
    private LoanType loanType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        loanApplication = LoanApplication.builder()
                .loanApplicationId(1L)
                .documentNumber("123456")
                .loanTypeId(10L)
                .amount(BigDecimal.valueOf(5000000))
                .build();

        gatewayUser = Gateway.builder()
                .email("test@mail.com")
                .build();

        loanType = LoanType.builder()
                .loanTypeId(10L)
                .name("Personal Loan")
                .build();
    }

    @Test
    void createLoanApplication_shouldSave_whenAllValid() {
        when(gatewayRepository.findByDocument("123456")).thenReturn(Mono.just(gatewayUser));
        when(loanTypeRepository.findLoanTypeById(10L)).thenReturn(Mono.just(loanType));
        when(loanApplicationRepository.saveLoanApplication(any(LoanApplication.class))).thenReturn(Mono.empty());

        StepVerifier.create(loanApplicationUseCase.createLoanApplication(loanApplication))
                .verifyComplete();

        verify(gatewayRepository).findByDocument("123456");
        verify(loanTypeRepository).findLoanTypeById(10L);
        verify(loanApplicationRepository).saveLoanApplication(any(LoanApplication.class));
    }

    @Test
    void createLoanApplication_shouldReturnError_whenUserNotFound() {
        when(gatewayRepository.findByDocument("123456")).thenReturn(Mono.empty());

        StepVerifier.create(loanApplicationUseCase.createLoanApplication(loanApplication))
                .expectError(ResourceNotFoundException.class)
                .verify();

        verify(gatewayRepository).findByDocument("123456");
        verifyNoInteractions(loanTypeRepository);
        verifyNoInteractions(statusRepository);
        verifyNoInteractions(loanApplicationRepository);
    }

    @Test
    void createLoanApplication_shouldReturnError_whenLoanTypeNotFound() {
        when(gatewayRepository.findByDocument("123456")).thenReturn(Mono.just(gatewayUser));
        when(loanTypeRepository.findLoanTypeById(10L)).thenReturn(Mono.empty());

        StepVerifier.create(loanApplicationUseCase.createLoanApplication(loanApplication))
                .expectError(ResourceNotFoundException.class)
                .verify();

        verify(gatewayRepository).findByDocument("123456");
        verify(loanTypeRepository).findLoanTypeById(10L);
        verifyNoInteractions(statusRepository);
        verifyNoInteractions(loanApplicationRepository);
    }

    @Test
    void createLoanApplication_shouldReturnError_whenSaveFails() {
        when(gatewayRepository.findByDocument("123456")).thenReturn(Mono.just(gatewayUser));
        when(loanTypeRepository.findLoanTypeById(10L)).thenReturn(Mono.just(loanType));
        when(loanApplicationRepository.saveLoanApplication(any(LoanApplication.class)))
                .thenReturn(Mono.error(new RuntimeException("DB error")));

        StepVerifier.create(loanApplicationUseCase.createLoanApplication(loanApplication))
                .expectError(RuntimeException.class)
                .verify();

        verify(gatewayRepository).findByDocument("123456");
        verify(loanTypeRepository).findLoanTypeById(10L);
        verify(loanApplicationRepository).saveLoanApplication(any(LoanApplication.class));
    }
}
