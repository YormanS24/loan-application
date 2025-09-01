package co.com.loadapplication.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("loan_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeEntity {

    @Id
    @Column("loan_type_id")
    private Long loanTypeId;
    private String name;

    @Column("min_amount")
    private BigDecimal minAmount;

    @Column("max_amount")
    private BigDecimal maxAmount;

    @Column("interest_rate")
    private Double interestRate;

    @Column("automatic_validation")
    private Boolean automaticValidation;

}