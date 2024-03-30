package com.saha.amit.controller;

import com.saha.amit.entity.inheritance.singleTable.CheckSingleTable;
import com.saha.amit.entity.inheritance.singleTable.CreditCardSingleTable;
import com.saha.amit.entity.inheritance.singleTable.PaymentSingleTable;
import com.saha.amit.entity.inheritance.tablePerClass.CheckTablePerClass;
import com.saha.amit.entity.inheritance.tablePerClass.CreditCardTablePerClass;
import com.saha.amit.entity.inheritance.tablePerClass.PaymentTablePerClass;
import com.saha.amit.repository.PaymentSingleTableRepository;
import com.saha.amit.repository.PaymentTablePerClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class PaymentInheritanceController {

    @Autowired
    PaymentSingleTableRepository paymentRepository;
    @Autowired
    PaymentTablePerClassRepository paymentTablePerClassRepository;

    @PostMapping("createCreditCardPaymentSingleTable")
    public ResponseEntity<PaymentSingleTable> createCreditCardPaymentSingleTable(@RequestBody CreditCardSingleTable creditCard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(creditCard));
    }

    @PostMapping("createCheckPaymentSingleTable")
    public ResponseEntity<PaymentSingleTable> createCheckPaymentSingleTable(@RequestBody CheckSingleTable check) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(check));
    }

    @PostMapping("createCreditCardPaymentTablePerClass")
    public ResponseEntity<PaymentTablePerClass> createCreditCardPaymentTablePerClass(@RequestBody CreditCardTablePerClass creditCardTablePerClass) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentTablePerClassRepository.save(creditCardTablePerClass));
    }

    @PostMapping("createCheckPaymentTablePerClass")
    public ResponseEntity<PaymentTablePerClass> createCheckPaymentTablePerClass(@RequestBody CheckTablePerClass checkTablePerClass) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentTablePerClassRepository.save(checkTablePerClass));
    }

}
