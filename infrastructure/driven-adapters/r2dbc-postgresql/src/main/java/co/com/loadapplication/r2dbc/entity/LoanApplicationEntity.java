package co.com.loadapplication.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "loan_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationEntity {

    @Id
    @Column("loan_application_id")
    private Long loanApplicationId;

    @Column("application_date")
    private LocalDate applicationDate;
    private BigDecimal amount;
    private Integer term;
    private String email;

    @Column("status_id")
    private Long statusId;

    @Column("loan_type_id")
    private Long loanTypeId;
}
