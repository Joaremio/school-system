package br.ufrn.imd.service;


import br.ufrn.imd.dto.StudentResponse;
import br.ufrn.imd.enums.TuitionStatus;
import br.ufrn.imd.model.Tuition;
import br.ufrn.imd.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }



}
