package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pojo.Employee;

@Repository
public class EmployeeDAOImp implements EmployeeDAO{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String sql;
	
	@Override
	public int insert(Employee emp) {
		try {
			sql = "Insert into employee values(?,?,?);";
			return jdbcTemplate.update(sql,emp.getEmpId(),emp.getEmpName(), emp.getEmpSalary());	
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String empId) {
		try {
			sql = "delete from employee where empId = ?";
			return jdbcTemplate.update(sql, empId);
		}catch(Exception e) {
			return 0;
		}
	}

	public static final class EmployeeRow implements RowMapper<Employee>{
		@Override
		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			Employee e = new Employee();
			e.setEmpId(rs.getString(1));
			e.setEmpName(rs.getString(2));
			e.setEmpSalary(rs.getDouble(3));
			return e;
		}
	}
	
	@Override
	public Employee search(String empId) {
		sql = "select * from employee where empId = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{empId}, new EmployeeRow());
	}

	@Override
	public List<Employee> getAllEmployees() {
		sql = "select * from employee";
		return jdbcTemplate.query(sql, new EmployeeRow());
	}

	@Override
	public int employeeCount() {
		sql = "select count(*) from employee";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public String employeeName(String empId) {
		sql = "select empName from employee where empId = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {empId}, String.class);
	}

	@Override
	public int updateName(String empId, String newName) {
		sql = "update employee set empName = ? where empId= ?";
		return jdbcTemplate.update(sql, newName, empId);
	}

}
