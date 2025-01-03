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

    /**
     * Since we are joining customer with Order hence for same customer there will be multiple rs rows.
     * To prevent multiple Customer being added only currentCustomer is null, If currentCustomer and
     * if currentCustomer is null and customerUuid != lastCustomerUuid, then currentCustomer is added
     * OrderDto is kept out of the check so that all orders of the customers is added in currentCustomer
     * @param rs the {@code ResultSet} to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return  com.saha.amit.dto.CustomerProfileOrderDto
     * @throws SQLException in case of any Exception
     */
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

            //Below is kept out of the if statement so that multiple orders gets added
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