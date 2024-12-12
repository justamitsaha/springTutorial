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

    public static CustomerDto getCustomerProfileMapper(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        customerDto.setProfileDto(modelMapper.map(customer.getProfile(), ProfileDto.class));
        customerDto.getProfileDto().setAddressDto(modelMapper.map(customer.getProfile().getAddress(), AddressDto.class));
        return customerDto;
    }

    /**
     * If we transform all the params of Customer to CustomerDTO then in turn
     * this will trigger Hibernate queries for Order, payments etc. to prevent this new method is created
     * @param customer
     * @return CustomerDto
     */
    public static CustomerDto getCustomerProfileOrder(Customer customer){
        AddressDto addressDto = new AddressDto(customer.getProfile().getAddress().getStreet(),
                customer.getProfile().getAddress().getCity(),
                customer.getProfile().getAddress().getState(),
                customer.getProfile().getAddress().getZipCode());
        ProfileDto  profileDto = new ProfileDto();
        profileDto.setProfileUuid(customer.getProfile().getProfileUuid());
        profileDto.setPhoneNumber(customer.getProfile().getPhoneNumber());
        profileDto.setEmail(customer.getProfile().getEmail());
        profileDto.setAddressDto(addressDto);
        List<OrderDto> orderDtoList = new ArrayList<>();
        customer.getOrders().forEach(orders -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderUuid(orders.getOrderUuid());
            orderDto.setOrderNumber(orderDto.getOrderNumber());
            orderDtoList.add(orderDto);
        });
        return new CustomerDto(customer.getCustomerUuid(),
                customer.getName(),
                profileDto,
                orderDtoList);
    }

    public static CustomerDto getCustomerProfileOrderMapper(Customer customer){
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
