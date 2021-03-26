package com.juaracoding.main.controller;



import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Absensi;
import com.juaracoding.main.model.AbsensiRowMapper;
import com.juaracoding.main.model.Biodata;
import com.juaracoding.main.model.BiodataRowMapper;




@RestController
@RequestMapping("/absensi")
public class AbsensiController {

	@Autowired
	JdbcTemplate jdbc ;
	
	public List<Absensi> getAbsensi() {
		String sql = "Select * from Absensi";
		List <Absensi> absensi =  jdbc.query(sql,new AbsensiRowMapper());
		return absensi;
	}
	
	public List<Absensi> getId (int id){
		String sql = "Select * from absensi where id = "+id+"";
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());
		return absensi;
	}
	
	public List<Absensi> getStart_date (String start_date ){
		String sql = "Select * from absensi where start_date = '"+start_date+"'";
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());
		return absensi;
	}
	
	public List<Absensi> getEnd_date (String end_date ){
		String sql = "Select * from absensi where end_date LIKE '"+end_date+"%'";
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());
		return absensi;
	}
	
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public int insertAbsensi(Absensi absensi) {
		return jdbc.update("insert into absensi(id,nik,start_date,end_date) values ("+absensi.getId()+",'"+absensi.getNik()+"','"+absensi.getStart_date()+"','"+absensi.getEnd_date()+"')");
		
	}
	
	public int updateAbsensi(int id, Absensi absensi) {
		return jdbc.update("UPDATE absensi SET `nik` = '"+absensi.getNik()+"',`start_date`='"+absensi.getStart_date()+"',`end_date`='"+absensi.getEnd_date()+"'   ");
	}

	public int deleteBiodata (int id) {
		return jdbc.update("delete from `absensi` where `id` = "+id+"");
	}
	
	
//--------------------------------------------------------------------------------------------------	
	
	@PostMapping("/")
	public String add(@RequestBody Absensi absensi) {
		if(this.insertAbsensi(absensi) == 1) {
			return "Insert data berhasil";
		}else {
			return "Insert data gagal";
		}
	}

//------------------------------------------------------------------------------------------------------------------------	
	
 @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
	 	deleteBiodata(id);
 }
 
 //--------------------------------------------------------------------------------------------------------------
 
 @GetMapping("/")
    public List<Absensi> list() {
        return getAbsensi();
    }
 
 @GetMapping("/{id}")
 	public List<Absensi> cariId (@PathVariable int id ){
		return getId(id);	 
 }
 
 @GetMapping("/start_date/{start_date}")
	public List<Absensi> cariStart_date (@PathVariable String start_date ){
		return getStart_date(start_date);	 
}
 
 
 @GetMapping("/end_date/{end_date}")
	public List<Absensi> cariEnd_date (@PathVariable String end_date ){
		return getEnd_date(end_date);	 
}
 
 
 
 
//------------------------------------------------------------------------------------------------------- 
 
 @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Absensi absensi, @PathVariable int id) {
	 try {
            updateAbsensi(id, absensi);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
 	}
	
	
	
	
	
	
	
	
	
	
	
	
}
