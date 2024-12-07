package com.saha.amit.cotroller;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.model.Customer;
import com.saha.amit.service.CustomerService;
import com.saha.amit.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("id/{id}")
    public ResponseEntity<CustomerDto> getReferenceById(@PathVariable Long id){
        Customer customer = customerService.getReferenceById(id);
        return ResponseEntity.ok().body(DataMapper.getCustomer(customer));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<CustomerDto>> getFromEmail(@PathVariable String email){
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerService.findByEmailContaining(email).forEach(customer -> {
            CustomerDto customerDto = DataMapper.getCustomer(customer);
            customerDtoList.add(customerDto);
        });
        return ResponseEntity.ok().body(customerDtoList);
    }

}
