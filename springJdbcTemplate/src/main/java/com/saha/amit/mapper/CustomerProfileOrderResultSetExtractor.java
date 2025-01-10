package com.saha.amit.mapper;

import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProfileDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerProfileOrderResultSetExtractor implements ResultSetExtractor<List<CustomerProfileOrderDto>> {

    @Override
    public List<CustomerProfileOrderDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, CustomerProfileOrderDto> customerMap = new HashMap<>();

        while (rs.next()) {
            Long customerUuid = rs.getLong("customer_uuid");
            CustomerProfileOrderDto customer = customerMap.get(customerUuid);

            if (customer == null) {
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

                customer = new CustomerProfileOrderDto();
                customer.setCustomerUuid(customerUuid);
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setProfile(profileDto);
                customer.setOrders(new ArrayList<>());

                customerMap.put(customerUuid, customer);
            }

            String orderUuid = rs.getString("order_uuid");
            if (orderUuid != null) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderUuid(orderUuid);
                orderDto.setOrderNumber(rs.getString("order_number"));

                customer.getOrders().add(orderDto);
            }
        }

        return new ArrayList<>(customerMap.values());
    }
}
