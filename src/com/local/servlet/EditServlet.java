package com.local.servlet;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/edit")  
public class EditServlet extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)   
           throws ServletException, IOException {  
		System.out.println("iam in edit servlet");
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Update Book</h1>");  
        String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);  
          
        Book e=Insertclass.getEmployeeById(id);  
          
        out.print("<form action='update' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td></td><td><input type='hidden' name='book_id' value='"+e.getId()+"'/></td></tr>");  
        out.print("<tr><td>Title:</td><td><input type='text' name='title' value='"+e.getTitle()+"'/></td></tr>");  
        out.print("<tr><td>Author:</td><td><input type='text' name='author' value='"+e.getAuthor()+"'/></td></tr>");  
        out.print("<tr><td>Price:</td><td><input type='text' name='price' value='"+e.getPrice()+"'/></td></tr>");  
        out.print("</td></tr>");  
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
        out.print("</table>");  
        out.print("</form>");  
          
        out.close();  
    }  
}  