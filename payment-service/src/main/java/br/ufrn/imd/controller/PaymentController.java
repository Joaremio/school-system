package br.ufrn.imd.controller;


import br.ufrn.imd.dto.PaymentRequest;
import br.ufrn.imd.dto.PaymentResponse;
import br.ufrn.imd.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private PaymentService paymentService;

    public  PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{tuitionId}")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable Long tuitionId, @RequestBody PaymentRequest data){
        return ResponseEntity.ok(paymentService.createPayment(tuitionId, data));
    }
}
