package co.com.loadapplication.model.gateway.gateways;

import co.com.loadapplication.model.gateway.Gateway;
import reactor.core.publisher.Mono;

public interface GatewayRepository {
    Mono<Gateway> findByDocument(String documentNumber);
}
