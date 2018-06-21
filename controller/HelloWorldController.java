package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.User;

@RestController

public class HelloWorldController {
	@GetMapping("/helloworld")
	public String helloworld() {
		return "helloworld";
	}

	@Autowired
	UserDao dao;
	@Autowired
	DepartmentDao dptDao;

	@GetMapping("/add")
	public void add() {
		User user = new User();
		user.setUserId("001");
		user.setUserName("Judy");
		user.setPassword("12345");
		user.setFirstName("xuan");
		user.setLastName("wang");
		user.setCreationDate(new Date(0));
		dao.saveUser(user);
	}

	@GetMapping("/delete")
	public void delete() {
		User user = new User();
		user.setUserId("001");
		user.setUserName("Judy");
		user.setPassword("12345");
		user.setFirstName("xuan");
		user.setLastName("wang");
		user.setCreationDate(new Date(0));
		dao.deleteUser(user);

	}

	@GetMapping("/update")
	public void update() {
		User user = new User();
		user.setUserId("002");
		user.setUserName("Lily");
		user.setPassword("12345");
		dao.updateUser(user);
	}

	@GetMapping("/searchId")
	public void searchById() {
		User user = null;
		user = dao.getUserById("001");
		System.out.println(user.getUserName());
	}

	@GetMapping("/searchName")
	public void searchByName() {
		List<User> user = new ArrayList<>();
		user = dao.getUserByName("Judy");
		if (user != null) {
			for (User i : user) {
				System.out.println(i.getUserId());
			}
		}
	}

	@GetMapping("/addDpt")
	public void saveDpt() {
		Department dpt = new Department("001", "Information");
		dptDao.saveDpt(dpt);
	}

	@GetMapping("/searchUserByDpt")
	public void searchUserByDpt() {
		List<UserDTO> res = new ArrayList<>();
		String dptId = "001";
		res = dao.getUserByDpt(dptId);
		if (!res.isEmpty()) {
			for (UserDTO i : res) {
				System.out.println(i.getUserId() + i.getDptId() + i.getUserName());
			}
		}
	}
}
