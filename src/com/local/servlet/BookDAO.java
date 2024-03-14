package com.local.servlet;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.local.servlet.*;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table book in the database.
 * 
 * @author www.codejava.net
 *
 */
public class BookDAO {
	private  String jdbcURL="com.mysql.jdbc.Driver";
	private  String jdbcUsername="root";
	private  String jdbcPassword="root";
	private static Connection jdbcConnection;

	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public static Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection("com.mysql.jdbc.Driver", "root", "root");
		}
		return jdbcConnection;
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertBook(com.local.servlet.Book book) throws SQLException {


		try {

			Connection connect = BookDAO.connect();
			String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";

			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setFloat(3, book.getPrice());

			boolean rowUpdated = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
			return rowUpdated;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}