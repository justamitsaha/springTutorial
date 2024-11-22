package com.saha.amit;

import com.saha.amit.dto.AddressDto;
import com.saha.amit.model.Address;
import org.modelmapper.ModelMapper;

public class Test {
    public static void main(String[] args) {
        Address address = new Address("a", "b", "c", "d");
        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        System.out.println(addressDto);
        addressDto.setCity("Lauru");
        Address address1 = modelMapper.map(addressDto, Address.class);
        System.out.println(address1);
    }
}
