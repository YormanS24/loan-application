package co.com.loadapplication.api.mapper;

import co.com.loadapplication.api.dto.LoanApplicationRequest;
import co.com.loadapplication.api.dto.LoanApplicationResponse;
import co.com.loadapplication.model.loanapplication.LoanApplication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {

    LoanApplication toModel(LoanApplicationRequest loanApplicationRequest);

    LoanApplicationResponse toResponse(LoanApplication loanApplication);

}
