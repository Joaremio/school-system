package br.ufrn.imd.dto;


import br.ufrn.imd.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod
) {
}
