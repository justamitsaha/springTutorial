package com.saha.amit.mapper;

import com.saha.amit.dto.OrderDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDtoRowMapper implements RowMapper<OrderDto> {
    @Override
    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderUuid(rs.getString("order_uuid"));
        orderDto.setOrderNumber(rs.getString("order_number"));
        return orderDto;
    }
}
