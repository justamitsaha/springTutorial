package com.saha.amit.cotroller;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.model.Customer;
import com.saha.amit.service.CustomerService;
import com.saha.amit.util.DataMapper;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(
            summary = "Get Customer information",
            description = "This API will use Join fetch to join Customer and Profile to fetch the details" +
                    "in a single query")
    @GetMapping("1/id/{id}")
    public ResponseEntity<CustomerDto> getCustomerProfile(@PathVariable Long id) {
        Customer customer = customerService.getReferenceById(id);
        return ResponseEntity.ok().body(DataMapper.getCustomerProfileModelMapper(customer));
    }

    @Operation(
            summary = "Get Customer information",
            description = "This API will use Join fetch to join Customer and Profile and related orders to fetch the details" +
                    "in a single query")
    @GetMapping("2/order/{id}")
    public ResponseEntity<CustomerDto> getCustomerProfileOrder(@PathVariable Long id) {
        Customer customer = customerService.getCustomerProfileOrder(id);
        return ResponseEntity.ok().body(DataMapper.getCustomerProfileOrderMapper(customer));
    }

    @GetMapping("3/email/{email}")
    public ResponseEntity<List<CustomerDto>> getFromEmail(@PathVariable String email) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerService.findByEmailContaining(email).forEach(customer -> {
            CustomerDto customerDto = DataMapper.getCustomerProfileOrderMapper(customer);
            customerDtoList.add(customerDto);
        });
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("4/moreThanThreeOrders")
    public ResponseEntity<List<CustomerDto>> findCustomersWithMoreThanThreeOrders() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerService.findCustomersWithMoreThanThreeOrders().forEach(customer -> {
            CustomerDto customerDto = DataMapper.getCustomerProfileOrder(customer);
            customerDtoList.add(customerDto);
        });
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("5/moreThanThreeSuccessfulOrders")
    public ResponseEntity<List<CustomerDto>> findCustomersWithMoreThanThreeSuccessfulOrders() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerService.findCustomersWithMoreThanThreeSuccessfulOrders().forEach(customer -> {
            CustomerDto customerDto = DataMapper.getCustomerProfileOrderPaymentMapper(customer);
            customerDtoList.add(customerDto);
        });
        return ResponseEntity.ok().body(customerDtoList);
    }

}
