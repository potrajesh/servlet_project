package com.local.servlet;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/updateservlet")  
public class UpdateServlet extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)   
          throws ServletException, IOException {  
		System.out.println("iam in update servlet");
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String sid=request.getParameter("book_id");  
        int id=Integer.parseInt(sid);  
        String name=request.getParameter("title");  
        String password=request.getParameter("author");
        String p = request.getParameter("price");
        Float price = Float.parseFloat(p);
          
        Book e=new Book();  
        e.setId(id);  
        e.setTitle(name);  
        e.setAuthor(password);  
        e.setPrice(price);  
        
        int status=Insertclass.update(e);  
        if(status>0){  
            response.sendRedirect("viewServlet");  
        }else{  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close();  
    }  
  
}  