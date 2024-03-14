package com.local.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewservlet")  
public class ViewServlet extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<a href='index.jsp'>Add New Book</a>");  
        out.println("<h1>Books List</h1>");  
          
        List<Book> list = Insertclass.getAllEmployees();

        out.print("<table border='1' width='100%'>");
        out.print("<tr><th>BookId</th><th>Title</th><th>Author</th><th>Price</th><th>Edit</th><th>Delete</th></tr>");

        for (Book b : list) {
            out.print("<tr>");
            out.print("<td>" + b.getId() + "</td>");
            out.print("<td>" + b.getTitle() + "</td>");
            out.print("<td>" + b.getAuthor() + "</td>");
            out.print("<td>" + b.getPrice() + "</td>");
            out.print("<td><a href='editserve?id=" + b.getId() + "'>Edit</a></td>");
            out.print("<td><a href='deleteserve?id=" + b.getId() + "'>Delete</a></td>"); // Placeholder for Delete button
            out.print("</tr>");
        }

        out.print("</table>");

        out.close();  
    }  
}  