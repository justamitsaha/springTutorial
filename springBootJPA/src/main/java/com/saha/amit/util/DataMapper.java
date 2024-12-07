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
