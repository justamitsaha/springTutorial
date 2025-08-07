package com.saha.amit.cotroller;

import com.saha.amit.constants.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.model.Customer;
import com.saha.amit.service.CustomerService;
import com.saha.amit.util.DataMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    private final Log log = LogFactory.getLog(CustomerController.class);

    @Operation(
            summary = AppConstants.GET_CUSTOMER_PROFILE_WITH_ID_SUMMARY,
            description = AppConstants.GET_CUSTOMER_PROFILE_WITH_ID_DESCRIPTION)
    @GetMapping("1/profile/{id}")
    public ResponseEntity<CustomerDto> getCustomerProfile(@PathVariable Long id, @RequestParam(name = "key", required = false) Boolean flag) {
        if (null != flag ? flag : false) {
            log.info("Fetching Data with JPQL");
            Customer customer = customerService.findCustomersById(id);
            return ResponseEntity.ok().body(DataMapper.getCustomerProfileModelMapper(customer));
        } else {
            log.info("Fetching Data with Projections");
            return ResponseEntity.ok().body(customerService.findCustomersByIdProjections(id));
        }
    }

    @Operation(
            summary = AppConstants.GET_CUSTOMER_PROFILE_ORDER_WITH_ID_SUMMARY,
            description = AppConstants.GET_CUSTOMER_PROFILE_ORDER_WITH_ID_DESCRIPTION)
    @GetMapping("2/profileOrder/{id}")
    public ResponseEntity<CustomerDto> findCustomerProfileOrderDataNative(@PathVariable Long id, @RequestParam(name = "key", required = false) Boolean key) {
        if (null != key ? key : new Random().nextBoolean()) {
            log.info("Fetching Data with JPQL");
            Customer customer = customerService.findCustomerProfileOrderData(id);
            return ResponseEntity.ok().body(DataMapper.getCustomerProfileOrderMapper(customer));
        } else {
            log.info("Fetching Data with native query");
            return ResponseEntity.ok().body(customerService.findCustomerProfileOrderDataNative(id));
        }
    }

    @GetMapping("3/profileOrder/{email}")
    public ResponseEntity<List<CustomerDto>> findByEmailContainingWithOrders(@PathVariable String email) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerService.findByEmailContainingWithOrders(email).forEach(customer -> {
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
