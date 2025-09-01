package co.com.loadapplication.api.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String DOCUMENT_REQUIRED = "El documento de identidad es obligatorio";
    public static final String DOCUMENT_SIZE = "El documento debe tener entre 5 y 20 caracteres";

    public static final String AMOUNT_REQUIRED = "El monto solicitado es obligatorio";
    public static final String AMOUNT_MIN = "El monto mínimo permitido es 500,000";
    public static final String AMOUNT_INVALID = "El monto debe ser un valor numérico válido con hasta 2 decimales";

    public static final String TERM_REQUIRED = "El plazo es obligatorio";
    public static final String TERM_MIN = "El plazo mínimo es de 3 meses";
    public static final String TERM_MAX = "El plazo máximo es de 120 meses";

    public static final String LOAN_TYPE_REQUIRED = "El tipo de préstamo es obligatorio";

}
