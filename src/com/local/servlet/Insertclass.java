package com.local.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insertclass extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookDAO bookDAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("iam in Inserted class");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		Book newBook = new Book(title, author, price);
		try {
			insertBook(newBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("rows inserted successfully");
		response.sendRedirect("index.jsp");
	}
	
	public static int deleteBookRow(int id) {
		Connection con = null;
		int status=0;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from book where book_id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
            ps.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
	public static Book getEmployeeById(int id){  
        Book e=new Book();  
          
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from book where book_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                e.setId(rs.getInt(1));  
                e.setTitle(rs.getString(2));  
                e.setAuthor(rs.getString(3));
                int p = Integer.parseInt(rs.getString(4));
                e.setPrice(p);  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return e;  
    }
	public static int update(Book b) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("update Book set book_id=?,title=?,author=?,price=? where book_id=?");
			
			ps.setInt(1, b.getId());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setFloat(4, b.getPrice());
			ps.setInt(5, b.getId());


			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static List<Book> getAllEmployees() {
		List<Book> list = new ArrayList<Book>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getFloat(4));
				list.add(b);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private boolean insertBook(com.local.servlet.Book book) throws SQLException {

		try {

			Connection connect = getConnection();
			String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";

			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setFloat(3, book.getPrice());

			boolean rowUpdated = statement.executeUpdate() > 0;
			statement.close();
			return rowUpdated;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	private static Connection getConnection() {
		java.sql.Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}