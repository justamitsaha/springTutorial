package com.saha.amit.util;

import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.Customer;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    /**
     * If we transform all the params of Customer to CustomerDTO then in turn
     * this will trigger Hibernate queries for Order, payments etc. to prevent this new method is created
     * @param Customer
     * @return CustomerDto
     */
    public static CustomerDto getCustomerProfile(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        customerDto.setProfileDto(modelMapper.map(customer.getProfile(), ProfileDto.class));
        customerDto.getProfileDto().setAddressDto(modelMapper.map(customer.getProfile().getAddress(), AddressDto.class));
        return customerDto;
    }

    public static CustomerDto getCustomer(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        customerDto.setProfileDto(modelMapper.map(customer.getProfile(), ProfileDto.class));
        customerDto.getProfileDto().setAddressDto(modelMapper.map(customer.getProfile().getAddress(), AddressDto.class));
        List<OrderDto> ordersList = new ArrayList<>();
        customer.getOrders().forEach(orders -> {
            OrderDto orderDto = modelMapper.map(orders, OrderDto.class);
            ordersList.add(orderDto);
        });
        customerDto.setOrderDto(ordersList);
        return customerDto;
    }
}
