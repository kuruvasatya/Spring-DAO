package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pojo.Employee;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {

	@Autowired
	DataSource dataSource;
	Connection con;
	PreparedStatement stmnt;
	String SQL;
	
	public  EmployeeDAOImp() {
		System.out.println("I am in emp dao imp");
	}

	@Override
	public boolean insert(Employee emp) {
		try {
			con = dataSource.getConnection();
			SQL = "insert into employee values(?,?,?);";
			stmnt = con.prepareStatement(SQL);
			stmnt.setString(1, emp.getEmpId());
			stmnt.setString(2, emp.getEmpName());
			stmnt.setDouble(3, emp.getEmpSalary());
			stmnt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(String empId) {
		try {
			con = dataSource.getConnection();
			SQL = "delete from employee where empId =?;";
			stmnt = con.prepareStatement(SQL);
			stmnt.setString(1, empId);
			stmnt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Employee search(String empId) {
		try {
			con = dataSource.getConnection();
			SQL = "select * from employee where empId =?;";
			stmnt = con.prepareStatement(SQL);
			stmnt.setString(1, empId);
			ResultSet rs = stmnt.executeQuery();
			Employee emp = new Employee();
			while(rs.next())
			{
				emp.setEmpId(rs.getString(1));
				emp.setEmpName(rs.getString(2));
				emp.setEmpSalary(rs.getDouble(3));
			}
			return emp;
		} catch (Exception e) {
			return null;
		}
	}

}
