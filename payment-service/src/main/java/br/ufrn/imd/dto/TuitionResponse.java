package br.ufrn.imd.dto;

import br.ufrn.imd.enums.TuitionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TuitionResponse(
        Long id,
        Long studentId,
        String studentName,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal remainingAmount,
        TuitionStatus status,
        LocalDate dueDate
) {
}
