package com.saha.amit.util;

import com.saha.amit.dto.*;
import com.saha.amit.model.Customer;
import com.saha.amit.model.Product;
import com.saha.amit.model.Profile;
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
        profileDto.setName(customer.getProfile().getName());
        profileDto.setAddressDto(addressDto);
        List<OrderDto> orderDtoList = new ArrayList<>();
        customer.getOrders().forEach(orders -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderUuid(orders.getOrderUuid());
            orderDto.setOrderNumber(orders.getOrderNumber());
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentUuid(orders.getPayment().getPaymentUuid());
            paymentDto.setPaymentStatus(orders.getPayment().getPaymentStatus());
            orderDtoList.add(orderDto);
        });
        return new CustomerDto(customer.getCustomerUuid(),
                customer.getCustomerName(),
                profileDto,
                orderDtoList);
    }

    public static CustomerDto getCustomerProfileOrderMapper(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        Profile profile = customer.getProfile();
        customerDto.setProfileDto(modelMapper.map(profile, ProfileDto.class));
        customerDto.getProfileDto().setAddressDto(modelMapper.map(customer.getProfile().getAddress(), AddressDto.class));
        List<OrderDto> ordersList = new ArrayList<>();
        customer.getOrders().forEach(orders -> {
            OrderDto orderDto = modelMapper.map(orders, OrderDto.class);
            ordersList.add(orderDto);
        });
        customerDto.setOrderDto(ordersList);
        return customerDto;
    }

    public static CustomerDto getCustomerProfileOrderPaymentMapper(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        Profile profile = customer.getProfile();
        customerDto.setProfileDto(modelMapper.map(profile, ProfileDto.class));
        customerDto.getProfileDto().setAddressDto(modelMapper.map(customer.getProfile().getAddress(), AddressDto.class));
        List<OrderDto> ordersList = new ArrayList<>();
        customer.getOrders().forEach(orders -> {
            OrderDto orderDto = modelMapper.map(orders, OrderDto.class);
            PaymentDto paymentDto = modelMapper.map(orders.getPayment(),PaymentDto.class);
            orderDto.setPaymentDto(paymentDto);
            ordersList.add(orderDto);
        });
        customerDto.setOrderDto(ordersList);
        return customerDto;
    }

    public static ProductDto getProductDtoWithCategory(Product product){
        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }



}
