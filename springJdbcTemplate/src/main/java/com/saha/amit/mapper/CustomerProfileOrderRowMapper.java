package com.saha.amit.mapper;

import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProfileDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerProfileOrderRowMapper implements RowMapper<CustomerProfileOrderDto> {

    @Override
    public CustomerProfileOrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<CustomerProfileOrderDto> customerList = new ArrayList<>();
        CustomerProfileOrderDto currentCustomer = null;
        Long lastCustomerUuid = null;

        while (rs.next()) {
            Long customerUuid = rs.getLong("customer_uuid");

            if (currentCustomer == null || !customerUuid.equals(lastCustomerUuid)) {
                ProfileDto profileDto = new ProfileDto(
                        rs.getLong("profile_uuid"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip_code")
                );

                currentCustomer = new CustomerProfileOrderDto();
                currentCustomer.setCustomerUuid(customerUuid);
                currentCustomer.setCustomerName(rs.getString("customer_name"));
                currentCustomer.setProfile(profileDto);
                currentCustomer.setOrders(new ArrayList<>());

                customerList.add(currentCustomer);
                lastCustomerUuid = customerUuid;
            }

            String orderUuid = rs.getString("order_uuid");
            if (orderUuid != null) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderUuid(orderUuid);
                orderDto.setOrderNumber(rs.getString("order_number"));

                currentCustomer.getOrders().add(orderDto);
            }
        }
        return customerList.isEmpty() ? null : customerList.get(0); // Return the first element if list is not empty
    }
}