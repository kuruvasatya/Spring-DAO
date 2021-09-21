package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pojo.Book;

@Repository
public class BookDAOImp implements BookDAO {

	@Autowired
	DataSource dataSource;
	Connection con = null;
	PreparedStatement stmnt;
	String SQL;

	public Connection createConnection() {
		try {
			con = dataSource.getConnection();
			System.out.println("Connection Successful.....");
		} catch (SQLException e) {
			System.out.println("Establishing connection failed");
		}
		return con;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("unable to close connection....");
		}
	}

	public boolean add(Book book) {
		con = createConnection();
		SQL = "insert into Library values(?,?,?)";
		try {
			stmnt = con.prepareStatement(SQL);
			stmnt.setString(1, book.getName());
			stmnt.setString(2, book.getEdition());
			stmnt.setInt(3, book.getIsbn());
			stmnt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Statement not executed....");
			return false;
		} finally {
			closeConnection();
		}
		return true;
	}

	public boolean remove(int isbn) {
		con = createConnection();
		SQL = "delete from Library where isbn = ?";
		try {
			stmnt = con.prepareStatement(SQL);
			stmnt.setInt(1, isbn);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Statement not executed....");
			return false;
		} finally {
			closeConnection();
		}
		return true;
	}

	public boolean search(int isbn) {
		con = createConnection();
		SQL = "select * from Library where isbn = ?";
		try {
			stmnt = con.prepareStatement(SQL);
			stmnt.setInt(1, isbn);
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Statement not executed....");
			return false;
		} finally {
			closeConnection();
		}
	}

}
