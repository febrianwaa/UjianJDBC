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
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;



@RestController
@RequestMapping("/worker")
public class WorkerController {

	
	@Autowired
	JdbcTemplate jdbc ;
	
	public List<Worker> getWorker() {
		String sql = "Select * from Worker";
		List <Worker> worker =  jdbc.query(sql,new WorkerRowMapper());
		return worker;
	}
	
	@GetMapping("/")
    public List<Worker> list() {
        return getWorker();
    }
	
	
	@PostMapping("/")
	public String add(@RequestBody Worker worker) {
		int sukses = jdbc.update("insert into worker(worker_id,first_name,last_name,salary,joining_date,department) values ("+worker.getWorker_id()+",'"+worker.getFirst_name()+"','"+worker.getLast_name()+"',"+worker.getSalary()+",'"+worker.getJoining_date()+"','"+worker.getDepartment()+"')");
		String hasil;
		
		if(sukses == 1) {
			hasil = "Insert data berhasil";
		}else {
			hasil = "Insert data gagal";
		}
		return hasil;
	}
	
	
	@DeleteMapping("/{worker_id}")
    public int delete(@PathVariable int worker_id) {
		return jdbc.update("delete from `worker` where `worker_id` = "+worker_id+"");
	}
	
	
	
	
	public int updateWorker(int worker_id, Worker worker) {
		return jdbc.update("UPDATE `worker` SET `first_name` = '"+worker.getFirst_name()+"',`last_name`='"+worker.getLast_name()+"',`salary`="+worker.getSalary()+",`joining_date`='"+worker.getJoining_date()+"',`department`='"+worker.getDepartment()+"' where `worker_id` = "+worker_id+"  ");
	}
	
	
	@PutMapping("/{worker_id}")
    public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable int worker_id) {
	 try {
            updateWorker(worker_id, worker);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
 	}
	
//5 ORANG GAJI TERTINGGI------------------------------------------------------------------------------------
	
	public List<Worker> getSalaryHigh() {
		String sql = "Select * from Worker order by `salary` DESC limit 5";
		List <Worker> worker =  jdbc.query(sql,new WorkerRowMapper());
		return worker;
	}
	
	@GetMapping("/salaryhigh")
    public List<Worker> salaryhigh() {
        return getSalaryHigh();
    }
	
	
//GAJI YANG SAMA---------------------------------------------------------------------------------------------	
	
	public List<Worker> getSalarySame() {
		String sql = "Select * from Worker order by `salary` DESC limit 2";
		List <Worker> worker =  jdbc.query(sql,new WorkerRowMapper());
		return worker;
	}
	
	@GetMapping("/salarysame")
    public List<Worker> SalarySame() {
        return getSalarySame();
    }
	
	
	
}
