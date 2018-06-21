package com.example.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

@Component
public class UserDao {

	@Autowired
	private DataSource dataSource;

	public void saveUser(User user) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return;
		}
		try {
			String sql = "INSERT IGNORE INTO user (user_id, user_name, first_name, last_name, password, creation_time) VALUES (?,?,?,?,?,?)"; // SQL injection
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getPassword());
			statement.setDate(6, (Date) user.getCreationDate());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE USERS
	public void deleteUser(User user) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (conn == null) {
			return;
		}
		try {
			String sql = "DELETE FROM user WHERE user_id = ?"; // SQL injection
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// UPDATE USERS
	public void updateUser(User user) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (conn == null) {
			return;
		}

		try {
			String sql = "UPDATE user SET user_name=?, first_name=?, last_name=?, password=? WHERE user_id = ?"; // SQL
																																		// injection
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getUserId());
			if (statement.execute()) {
				System.out.println("User" + user.getUserId() + "has been updated succesfully.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SEARCH USER by ID
	public User getUserById(String userId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return null;
		}
		User user = null;
		try {
			String sql = "SELECT * FROM user WHERE user_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setCreationDate(rs.getDate("creation_time"));
			} else {
				System.out.println("Not Found!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// SEARCH USER by NAME
	public List<User> getUserByName(String name) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return null;
		}
		
		List<User> result = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM user WHERE user_name = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setCreationDate(rs.getDate("creation_time"));
				result.add(user);
			} 
			if (result.isEmpty()) {
				System.out.println("Not Found!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * Search Users by Department Id
	 * Return a List of User Objects
	 */
	public List<UserDTO> getUserByDpt(String dptId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return null;
		}
		
		List<UserDTO> result = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM user_department LEFT JOIN user ON user_department.user_id = user.user_id WHERE dpt_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dptId);

			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setUserId(rs.getString("user_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setCreationDate(rs.getDate("creation_time"));
				dto.setDptId(rs.getString("dpt_id"));
				result.add(dto);
			} 
			if (result.isEmpty()) {
				System.out.println("Not Found!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
