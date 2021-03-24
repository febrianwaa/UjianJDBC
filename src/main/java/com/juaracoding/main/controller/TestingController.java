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
import com.juaracoding.main.model.Biodata;
import com.juaracoding.main.model.BiodataRowMapper;



@RestController
@RequestMapping("/biodata")
public class TestingController {
	
	@Autowired
	JdbcTemplate jdbc ;
	
	public List<Biodata> getBiodata() {
		
		String sql = "Select * from Biodata";
		
		List <Biodata> biodata =  jdbc.query(sql,new BiodataRowMapper());
		
		return biodata;
		
		
	}
	
	public int insertBiodata(Biodata biodata) {
		return jdbc.update("insert into biodata(nik,nama,alamat,id_salary) values ('"+biodata.getNik()+"','"+biodata.getNama()+"','"+biodata.getAlamat()+"',"+biodata.getId_salary()+")");
		
	}
	
	public int updateBiodata(String nik, Biodata biodata) {
		return jdbc.update("UPDATE biodata SET `nama` = '"+biodata.getNama()+"',`alamat`='"+biodata.getAlamat()+"',`id_salary`='"+biodata.getId_salary()+"'   ");
	}

	public int deleteBiodata (String nik) {
		return jdbc.update("delete from `biodata` where `nik` = '"+nik+"'");
	}
	
	
	
	
	@PostMapping("/")
	public String add(@RequestBody Biodata biodata) {
		if(this.insertBiodata(biodata) == 1) {
			return "Insert data berhasil";
		}else {
			return "Insert data gagal";
		}
	}
	
	
	@DeleteMapping("/{nik}")
    public void delete(@PathVariable String nik) {
	 	deleteBiodata(nik);
 }
 
 
 @GetMapping("/")
    public List<Biodata> list() {
        return getBiodata();
    }
 
 @PutMapping("/{nik}")
    public ResponseEntity<?> update(@RequestBody Biodata biodata, @PathVariable String nik) {
	 try {
            updateBiodata(nik, biodata);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
 	}
	
	
	
	
	
	/*@RequestMapping("/insert")
	public String insertBiodata () {
		
		Biodata biodata = new Biodata();
		biodata.setNik("2222222");
		biodata.setNama("Chelsea");
		biodata.setAlamat("Bogor");
		biodata.setId_salary(123);
		
		if(this.insertBiodata(biodata) == 1) {
			return "Insert data berhasil";
		}else {
			return "Insert data gagal";
		}
	}*/
		
 
}