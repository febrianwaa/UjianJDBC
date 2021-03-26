package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class KPRRowMapper implements RowMapper<KPR>{

	@Override
	public KPR mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new KPR(rs.getInt("angsuranke"), rs.getString("tanggal"), rs.getDouble("totalAngsuran"), rs.getDouble("angsuranPokok"), rs.getDouble("angsuranBunga"), rs.getDouble("sisaPinjaman"));
	}

}