package com.saha.amit.controller;

import com.saha.amit.entity.relationShip.oneMany.Customer;
import com.saha.amit.entity.relationShip.oneMany.PhoneNumber;
import com.saha.amit.model.CustomerPhoneRecord;
import com.saha.amit.model.PhoneDetails;
import com.saha.amit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customerPhone")
public class OneManyRelationController {
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/saveCustomerPhone")
    public ResponseEntity<CustomerPhoneRecord> saveCustomerPhone(@RequestBody CustomerPhoneRecord customerPhoneRecord) {
        Customer customer = new Customer();
        customer.setName(customerPhoneRecord.getName());

/*        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        customerPhoneRecord.getPhoneDetailsArrayList().forEach(phoneDetails -> {
            PhoneNumber phoneNumber = PhoneNumber.builder()
                    .number(phoneDetails.getNumber())
                    .type(phoneDetails.getType())
                    .customer(customer)
                    .build();
            phoneNumberList.add(phoneNumber);
        });
        customer.setNumbers(phoneNumberList);*/

        customerPhoneRecord.getPhoneDetailsArrayList().forEach(phoneDetails -> {
            PhoneNumber phoneNumber = PhoneNumber.builder()
                    .number(phoneDetails.getNumber())
                    .type(phoneDetails.getType())
                    .build();
            customer.addPhone(phoneNumber);
        });
        customerRepository.save(customer);
        customerPhoneRecord.setId(customer.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerPhoneRecord);
    }

    @GetMapping("/getPhoneByName")
    public ResponseEntity<List<CustomerPhoneRecord>> getPhoneByName(@RequestParam String name) {
        List<Customer> customerList = customerRepository.findByNameContaining(name);
        System.out.println(customerList.size());
        List<CustomerPhoneRecord> customerPhoneRecordArrayList = new ArrayList<>();

        customerList.forEach(customer -> {
            CustomerPhoneRecord customerPhoneRecord = new CustomerPhoneRecord();
            customerPhoneRecord.setId(customer.getId());
            customerPhoneRecord.setName(customer.getName());
            List<PhoneDetails> phoneDetailsList = new ArrayList<>();
            customer.getNumbers().forEach(phoneNumber -> {
                PhoneDetails phoneDetails = new PhoneDetails();
                phoneDetails.setNumber(phoneNumber.getNumber());
                phoneDetails.setType(phoneNumber.getType());
                phoneDetailsList.add(phoneDetails);
            });
            customerPhoneRecord.setPhoneDetailsArrayList(phoneDetailsList);
            customerPhoneRecordArrayList.add(customerPhoneRecord);
        });
        return ResponseEntity.status(HttpStatus.FOUND).body(customerPhoneRecordArrayList);
    }
}




