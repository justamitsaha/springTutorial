package com.saha.amit.mapper;


import com.saha.amit.dto.ProfileDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements RowMapper<ProfileDto> {

    @Override
    public ProfileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileUuid(rs.getLong("profile_uuid"));
        profileDto.setEmail(rs.getString("email"));
        profileDto.setName(rs.getString("name"));
        profileDto.setPhoneNumber(rs.getString("phone_number"));
        profileDto.setStreet(rs.getString("street"));
        profileDto.setCity(rs.getString("city"));
        profileDto.setState(rs.getString("state"));
        profileDto.setZipCode(rs.getString("zip_code"));
        return profileDto;
    }
}

