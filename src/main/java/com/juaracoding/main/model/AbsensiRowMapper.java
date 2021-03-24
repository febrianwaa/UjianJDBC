package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AbsensiRowMapper implements RowMapper<Absensi> {

	@Override
	public Absensi mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		
		
		
		Absensi absensi = new Absensi();
		absensi.setId(rs.getInt("id"));
		absensi.setNik(rs.getString("nik"));
		absensi.setStart_date(rs.getString("start_date"));
		absensi.setEnd_date(rs.getString("end_date"));
				
				
		// TODO Auto-generated method stub
		return absensi;
	}
	
	
	
	
}
