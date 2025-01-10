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

public class CustomerProfileOrderCountResultSetExtractor implements ResultSetExtractor<List<CustomerProfileOrderDto>> {

    /**
     * Since we are joining customer with orders, for the same customer there will be multiple result set rows.
     * To prevent duplicate customers being added, data is added to a Map.
     *
     * @param rs the ResultSet to extract data from. Implementations should
     *           not close this: it will be closed by the calling JdbcTemplate.
     * @return List of CustomerProfileOrderDto
     * @throws SQLException         in case of SQL issues
     * @throws DataAccessException  in case of Spring JDBC issues
     */
    @Override
    public List<CustomerProfileOrderDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, CustomerProfileOrderDto> customerMap = new HashMap<>();

        while (rs.next()) {
            Long customerUuid = rs.getLong("customer_uuid");
            CustomerProfileOrderDto customer = customerMap.get(customerUuid);

            // Check if the customer already exists in the map
            if (customer == null) {
                ProfileDto profileDto = new ProfileDto(
                        rs.getLong("profile_uuid"),
                        rs.getString("email"),
                        rs.getString("profile_name"),
                        rs.getString("phone_number"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip_code")
                );

                customer = new CustomerProfileOrderDto();
                customer.setCustomerUuid(customerUuid);
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setOrderCount(rs.getInt("order_count")); // Set the order count
                customer.setProfile(profileDto);
                customer.setOrders(new ArrayList<>());

                customerMap.put(customerUuid, customer);
            }

            // Add order details to the orders list
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
