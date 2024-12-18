package com.saha.amit.service;

import com.saha.amit.model.Customer;
import com.saha.amit.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;


    private final Log log = LogFactory.getLog(CustomerService.class);


    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getReferenceById(Long id){
        //return customerRepository.getReferenceById( id);
        return customerRepository.findCustomersById(id);
    }

    public Customer getCustomerProfileOrder(Long id){
        return customerRepository.getCustomerProfileOrder(id);
    }

    public List<Customer> findByEmailContaining(String email){
        log.info("findByEmailContaining -->"+ email);
        /*var customer = profileRepository.findByEmailContaining(email);
        return customerRepository.findByProfileIn(customer);*/
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
