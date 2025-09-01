package co.com.loadapplication.api;

import co.com.loadapplication.api.dto.LoanApplicationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/solicitud",
                    produces = { "application/json" },
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "createLoanApplication",
                    operation = @Operation(
                            operationId = "CrearSolicitud",
                            summary = "Crear una solicitud de préstamo para el usuario",
                            requestBody = @RequestBody(
                                    required = true,
                                    description = "Crea una solicitud de préstamo en el sistema",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = LoanApplicationRequest.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Solicitud de préstamo creada"),
                                    @ApiResponse(responseCode = "403", description = "Acceso denegado"),
                                    @ApiResponse(responseCode = "404", description = "No existe el tipo de préstamo")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/v1/solicitud"), handler::createLoanApplication);
    }
}
