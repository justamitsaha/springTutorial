package com.saha.amit.service;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.Customer;
import com.saha.amit.model.Profile;
import com.saha.amit.repository.CustomerRepository;
import com.saha.amit.repository.ProfileRepository;
import org.apache.catalina.mapper.Mapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProfileRepository profileRepository;

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
//        var customer = profileRepository.findByEmailContaining(email);
//        return customerRepository.findByProfileIn(customer);
        return customerRepository.findByEmailContainingWithOrders(email);
    }

    public List<Customer> findCustomersWithMoreThanFiveOrders(){
        if (new Random().nextBoolean()){
            log.info("findCustomersWithMoreThanFiveOrders JPA");
            return customerRepository.findCustomersWithMoreThanFiveOrders();
        }else {
            log.info("findCustomersWithMoreThanFiveOrdersNative");
            return customerRepository.findCustomersWithMoreThanFiveOrdersNative();
        }
    }


    public List<Customer> findCustomersWithMoreThanFiveSuccessfulOrders(){
        return customerRepository.findCustomersWithMoreThanFiveSuccessfulOrders();
    }
}
