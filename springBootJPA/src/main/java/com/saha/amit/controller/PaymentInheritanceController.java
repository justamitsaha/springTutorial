package com.saha.amit.controller;

import com.saha.amit.entity.inheritance.singleTable.Check;
import com.saha.amit.entity.inheritance.singleTable.CreditCard;
import com.saha.amit.entity.inheritance.singleTable.Payment;
import com.saha.amit.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class PaymentInheritanceController {

    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping("createCreditCardPayment")
    public ResponseEntity<Payment> createCreditCardPayment(@RequestBody CreditCard creditCard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(creditCard));
    }

    @PostMapping("createCheckPayment")
    public ResponseEntity<Payment> createCheckPayment(@RequestBody Check check) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(check));
    }

}
