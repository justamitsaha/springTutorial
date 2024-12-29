package com.saha.amit.service;

import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.Customer;
import com.saha.amit.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;


    private final Log log = LogFactory.getLog(CustomerService.class);


    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomersById(Long id){
        return customerRepository.findCustomersById(id);
    }

    public CustomerDto findCustomersByIdProjections(Long id){
        return customerRepository.findCustomersByIdProjections(id);
    }

    public Customer findCustomerProfileOrderData(Long id){
        return customerRepository.findCustomerProfileOrderData(id);
    }

    public CustomerDto findCustomerProfileOrderDataNative(Long id) {
        List<Object[]> results = customerRepository.findCustomerProfileOrderDataNative(id);

        if (results.isEmpty()) {
            return null;
        }

        Object[] firstResult = results.get(0);

        ProfileDto profileDto = new ProfileDto(
                (Long) firstResult[2], (String) firstResult[3], (String) firstResult[4],
                (String) firstResult[5], new AddressDto((String) firstResult[6], (String) firstResult[7], (String) firstResult[8], (String) firstResult[9])
        );

        List<OrderDto> orderDtos = results.stream()
                .map(result -> new OrderDto((String) result[10], (String) result[11]))
                .collect(Collectors.toList());

        return new CustomerDto((Long) firstResult[0], (String) firstResult[1], profileDto, orderDtos);
    }


    public List<Customer> findByEmailContainingWithOrders(String email){
        log.info("findByEmailContaining -->"+ email);
        return customerRepository.findByEmailContainingWithOrders(email);
    }

    public List<Customer> findCustomersWithMoreThanThreeOrders(){
        if (new Random().nextBoolean()){
            log.info("findCustomersWithMoreThanThreeOrders JPA");
            return customerRepository.findCustomersWithMoreThanThreeOrders();
        }else {
            log.info("findCustomersWithMoreThanThreeOrdersNative");
            return customerRepository.findCustomersWithMoreThanThreeOrdersNative();
        }
    }


    public List<Customer> findCustomersWithMoreThanThreeSuccessfulOrders(){
        return customerRepository.findCustomersWithMoreThanThreeSuccessfulOrders();
    }
}
