package com.saha.amit;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id){
        return ResponseEntity.ok().body(customerService.get(id));
    }

}
