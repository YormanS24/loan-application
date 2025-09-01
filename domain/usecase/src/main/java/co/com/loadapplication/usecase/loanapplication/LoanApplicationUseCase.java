package co.com.loadapplication.usecase.loanapplication;

import co.com.loadapplication.model.gateway.gateways.GatewayRepository;
import co.com.loadapplication.model.loanapplication.LoanApplication;
import co.com.loadapplication.model.loanapplication.gateways.LoanApplicationRepository;
import co.com.loadapplication.model.loantype.gateways.LoanTypeRepository;
import co.com.loadapplication.model.utils.LoanApplicationLogger;
import co.com.loadapplication.usecase.loanapplication.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class LoanApplicationUseCase {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanApplicationLogger logger;
    private final GatewayRepository gatewayRepository;

    public Mono<Void> createLoanApplication(LoanApplication loanApplication) {
        logger.info("Iniciando creación de solicitud de préstamo");

        return gatewayRepository.findByDocument(loanApplication.getDocumentNumber())
                .doOnSubscribe(sub -> logger.debug("Buscando el usuario con documento: " + loanApplication.getDocumentNumber()))
                .switchIfEmpty(Mono.defer(() -> {
                    logger.error("No existe el usuario buscado con documento: {}", null);
                    return Mono.error(new ResourceNotFoundException(
                            "No existe el usuario con documento: " + loanApplication.getDocumentNumber()));
                }))
                .flatMap(user -> {
                    loanApplication.setEmail(user.getEmail());
                    logger.info("Email del usuario asignado: " + user.getEmail());

                    return loanTypeRepository.findLoanTypeById(loanApplication.getLoanTypeId())
                            .doOnSubscribe(sub -> logger.debug("Buscando tipo de préstamo con id: " + loanApplication.getLoanTypeId()))
                            .doOnNext(type -> logger.info("Tipo de préstamo encontrado: " + type.getName()))
                            .switchIfEmpty(Mono.defer(() -> {
                                logger.error("El tipo de préstamo con id = " + loanApplication.getLoanTypeId() + " no existe", null);
                                return Mono.error(new ResourceNotFoundException("El tipo de préstamo no existe"));
                            }))
                            .flatMap(statusId -> {
                                loanApplication.setStatusId(1L); // Estado "Pendiente"
                                loanApplication.setApplicationDate(LocalDate.now());

                                logger.info("Preparando solicitud antes de guardar: " + loanApplication);
                                return loanApplicationRepository.saveLoanApplication(loanApplication)
                                        .doOnSuccess(saved -> logger.info("Solicitud guardada con éxito: " + loanApplication))
                                        .doOnError(err -> logger.error("Error al guardar la solicitud", err));
                            });
                })
                .then()
                .doOnTerminate(() -> logger.info("Proceso de creación de solicitud finalizado"));
    }
}
