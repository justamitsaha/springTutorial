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
        return customerRepository.getReferenceById( id);
    }

    public List<Customer> findByEmailContaining(String email){
        log.info("findByEmailContaining -->"+ email);
//        var customer = profileRepository.findByEmailContaining(email);
//        return customerRepository.findByProfileIn(customer);
        return customerRepository.findByEmailContainingWithOrders(email);
    }
}
