package com.saha.amit.cotroller;

import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.Customer;
import com.saha.amit.model.Profile;
import com.saha.amit.service.CustomerService;
import org.modelmapper.ModelMapper;
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


    @GetMapping("id/{id}")
    public ResponseEntity<CustomerDto> getReferenceById(@PathVariable Long id){
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerService.getReferenceById(id);
        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        customerDto.setProfileDto(modelMapper.map(customer.getProfile(), ProfileDto.class));
        customerDto.getProfileDto().setAddressDto(modelMapper.map(customer.getProfile().getAddress(), AddressDto.class));
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<String> getFromEmail(@PathVariable String email){
        return ResponseEntity.ok().body(customerService.findByEmailContaining(email).toString());
    }

}
