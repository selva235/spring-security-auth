package com.authorization.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.authorization.user.entity.user;
import com.authorization.user.service.Userservice;
import io.swagger.annotations.ApiOperation;

@RestController
public class Usercontroller {
	
	
	 @Autowired
	 Userservice userService;
	
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 

    public Usercontroller(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	@PostMapping(value = "/saveUser")
	@ApiOperation(value = "Save User", produces = "application/json" ,consumes ="application/json")
	public ResponseEntity<user> saveUser(@RequestBody user user,HttpServletRequest request) throws Exception {		
		System.out.println("<<<<<<<<<<<<"+user.toString());		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		
		return new ResponseEntity<user>(userService.saveUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteUser")
	@ApiOperation(value = "Save User", produces = "application/json" ,consumes ="application/json")
	public ResponseEntity<String> deleteUser(@RequestBody user user,HttpServletRequest request) throws Exception {		
		return new ResponseEntity<String>(userService.deleteUser(user), HttpStatus.OK);
	}

	@GetMapping(value = "/Userlist")
	@ApiOperation(value = "Save User", produces = "application/json" ,consumes ="application/json")
	public ResponseEntity<List> Userlist(HttpServletRequest request) throws Exception {
		return new ResponseEntity<List>(userService.Userlist(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/UserlistbyParam")
	@ApiOperation(value = "Save User", produces = "application/json" ,consumes ="application/json")
	public ResponseEntity<List> UserlistbyParam(@RequestBody user user,HttpServletRequest request) throws Exception {
		return new ResponseEntity<List>(userService.UserlistbyParam(user), HttpStatus.OK);
	}
	
	
}
