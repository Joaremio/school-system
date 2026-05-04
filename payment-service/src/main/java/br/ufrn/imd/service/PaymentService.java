package br.ufrn.imd.service;
import br.ufrn.imd.dto.PaymentRequest;
import br.ufrn.imd.dto.PaymentResponse;
import br.ufrn.imd.enums.TuitionStatus;
import br.ufrn.imd.model.Payment;
import br.ufrn.imd.model.Tuition;
import br.ufrn.imd.repository.PaymentRepository;
import br.ufrn.imd.repository.TuitionRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private TuitionRepository tuitionRepository;

    public PaymentService(PaymentRepository paymentRepository,  TuitionRepository tuitionRepository) {
        this.paymentRepository = paymentRepository;
        this.tuitionRepository = tuitionRepository;
    }

    public PaymentResponse createPayment(Long tuitionId, PaymentRequest data){
        Tuition tuition = tuitionRepository.findById(tuitionId)
                .orElseThrow(() -> new RuntimeException("Tuition not found"));

        if (tuition.getStatus() == TuitionStatus.PAGO) {
            throw new RuntimeException("Tuition already paid");
        }

        BigDecimal currentPaid = tuition.getPaidAmount();

        BigDecimal newTotalPaid = currentPaid.add(data.amount());


        if (newTotalPaid.compareTo(tuition.getTotalAmount()) > 0) {
            throw new RuntimeException("Payment exceeds tuition amount");
        }

        Payment payment = new Payment();
        payment.setAmount(data.amount());
        payment.setMethod(data.paymentMethod());
        payment.setTuition(tuition);
        payment.setPaymentDate(LocalDateTime.now());

        Payment newPayment = paymentRepository.save(payment);

        tuition.setPaidAmount(newTotalPaid);

        boolean totalPayment = newPayment.getAmount().equals(tuition.getTotalAmount());

        if(!totalPayment){
            tuition.setPaidAmount(newPayment.getAmount());
            tuition.setStatus(TuitionStatus.PARCIAL);
        }else{
            tuition.setStatus(TuitionStatus.PAGO);
        }

        tuitionRepository.save(tuition);

        return new PaymentResponse(
            newPayment.getId(),
                tuition.getId(),
                tuition.getStatus(),
                tuition.getPaidAmount(),
                newPayment.getMethod(),
                 newPayment.getPaymentDate()
        );
    }



}
