package com.saha.amit.mapper;

import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProfileDto;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CustomerProfileOrderCountResultSetExtractor implements RowMapper<CustomerProfileOrderDto> {
    @Override
    public CustomerProfileOrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerProfileOrderDto dto = new CustomerProfileOrderDto();
        dto.setCustomerUuid(rs.getLong("customer_uuid"));
        dto.setCustomerName(rs.getString("customer_name"));
        dto.setOrderCount(rs.getInt("order_count"));

        ProfileDto profile = new ProfileDto(
                rs.getLong("profile_uuid"),
                rs.getString("email"),
                rs.getString("profile_name"),
                rs.getString("phone_number"),
                rs.getString("street"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("zip_code")
        );
        dto.setProfile(profile);

        OrderDto order = new OrderDto(
                rs.getString("order_uuid"),
                rs.getString("order_number")
        );
        dto.setOrders(Collections.singletonList(order)); // Each row contains one order
        return dto;
    }
}


