
package com.juaracoding.main.controller;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.dao.DaoBiodata;

import com.juaracoding.main.model.KPR;
import com.juaracoding.main.model.KPRDataPost;
import com.juaracoding.main.model.KPRRowMapper;

@RestController
@RequestMapping("/KPR")
public class KPRController {

	
	@Autowired
	JdbcTemplate jdbc;

	
	
	public List<KPR> getKPR() {

		String sql = " CALL `ulangBulan`('2021-03-24 00:00:00.000000', '15000000', '1.2', '12')";

		List<KPR> kpr = jdbc.query(sql, new KPRRowMapper());

		return kpr;

	}

	

	 @PostMapping("/showKpr")
	    public List<KPR> lstKpr(@RequestBody KPRDataPost dataKpr) {
		 
	    	 String sql = " CALL `ulangBulan`('"+dataKpr.getDf()+"', '"+dataKpr.getPlatfond()+"', '"+dataKpr.getBunga()+"', '"+dataKpr.getLamapijaman()+"')";

			List<KPR> kpr = jdbc.query(sql, new KPRRowMapper());

			return kpr;		
	    }
	 
	 
	 @GetMapping("/kpr")
	    public List<KPR> kpr() {
	        return getKPR();
	    }
	 
	
	
	
	
	
	
}
