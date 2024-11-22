package com.saha.amit.service;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.Customer;
import com.saha.amit.repository.CustomerRepository;
import org.apache.catalina.mapper.Mapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    private final Log log = LogFactory.getLog(CustomerService.class);


    public CustomerDto save(CustomerDto customerDto) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDto.class);
    }

    public CustomerDto get(Long id){
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerRepository.getReferenceById( id);
        log.info("Get Customer" +customer.toString());
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setProfileDto(modelMapper.map(customer.getProfile(), ProfileDto.class));
        return  customerDto;
    }
}
