package br.ufrn.imd.dto;

import br.ufrn.imd.enums.PaymentMethod;
import br.ufrn.imd.enums.TuitionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long tuitionId,
        TuitionStatus tuitionStatus,
        BigDecimal paidAmount,
        PaymentMethod paymentMethod,
        LocalDateTime paymentDate

) {
}
