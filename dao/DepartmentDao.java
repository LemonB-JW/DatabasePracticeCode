package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Department;

@Component
public class DepartmentDao {
	@Autowired
	private DataSource datasource;

	/*
	 * Add Department Info into table Department
	 */
	public void saveDpt(Department dpt) {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return;
		}
		try {
			String sql = "INSERT IGNORE INTO department (dpt_id, dpt_name) VALUES (?,?) ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dpt.getDptId());
			statement.setString(2, dpt.getDptName());
			if (statement.execute()) {
				System.out.println("Successfully Inserted!");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Delete Department Info from table department by dpt_id
	 */
	public void deleteDpt(Department dpt) {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return;
		}
		try {
			String sql = "DELETE FROM department WHERE dpt_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dpt.getDptId());
			if (statement.execute()) {
				System.out.println("Successfully Deleted!");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Update Department Info in table department
	 */
	public void updateDpt(Department dpt) {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			return;
		}
		try {
			String sql = "UPDATE department SET dpt_name=? WHERE dpt_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dpt.getDptName());
			statement.setString(2, dpt.getDptId());
			if (statement.execute()) {
				System.out.println(dpt.getDptId() + "Has Been Successfully Updates!");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Search Department Info by dpt_id
	 */
	public Department searchDptById(String dptId) {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			System.out.println("Database Connection Failed.");
			return null;
		}		
		Department result = null;
		try {
			String sql = "SELECT * FROM department WHERE dpt_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dptId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				result = new Department(rs.getString("dpt_id"), rs.getString("dpt_name"));
			} else {
				System.out.println("No Match!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	/*
	 * Search Department Info by dpt_name
	 */
	public List<Department> searchDptByName(String dptName) {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			System.out.println("Database Connection Failed.");
			return null;
		}		
		List<Department> result = new ArrayList<>();
		try {
			String sql = "SELECT * FROM department WHERE dpt_Name = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dptName);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				result.add(new Department(rs.getString("dpt_id"), rs.getString("dpt_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result.isEmpty()) {
			System.out.println("No Match!");
		}
		return result;
	}
}
