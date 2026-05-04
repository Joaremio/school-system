package br.ufrn.imd.controller;


import br.ufrn.imd.dto.PaymentRequest;
import br.ufrn.imd.dto.PaymentResponse;
import br.ufrn.imd.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private PaymentService paymentService;

    public  PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{tuitionId}")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable Long tuitionId, PaymentRequest data){
        return ResponseEntity.ok(paymentService.createPayment(tuitionId, data));
    }
}
