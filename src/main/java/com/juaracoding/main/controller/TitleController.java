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

import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;


@RestController
@RequestMapping("/title")
public class TitleController {

	
	@Autowired
	JdbcTemplate jdbc ;
	
	public List<Title> getTitle() {
		String sql = "Select * from Title";
		List <Title> title =  jdbc.query(sql,new TitleRowMapper());
		return title;
	}
	
	@GetMapping("/")
    public List<Title> list() {
        return getTitle();
    }
	
	
	@PostMapping("/")
	public String add(@RequestBody Title title) {
		int sukses = jdbc.update("insert into title (worker_ref_id,worker_title,affected_from) values ("+title.getWorker_ref_id()+",'"+title.getWorker_title()+"','"+title.getAffected_from()+"')");
		String hasil;
		
		if(sukses == 1) {
			hasil = "Insert data berhasil";
		}else {
			hasil = "Insert data gagal";
		}
		return hasil;
	}
	
	
	@DeleteMapping("/{worker_ref_id}")
    public int delete(@PathVariable int worker_ref_id) {
		return jdbc.update("delete from `title` where `worker_ref_id` = "+worker_ref_id+"");
	}
	
	
	
	
	public int updateTitle(int worker_ref_id, Title title) {
		return jdbc.update("UPDATE `title` SET `worker_title` = '"+title.getWorker_title()+"',`affected_from`='"+title.getAffected_from()+"' where `worker_ref_id` = "+worker_ref_id+"  ");
	}
	
	
	@PutMapping("/{worker_ref_id}")
    public ResponseEntity<?> update(@RequestBody Title title, @PathVariable int worker_ref_id) {
	 try {
            updateTitle(worker_ref_id, title);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
 	}
	
	
	
	
}
