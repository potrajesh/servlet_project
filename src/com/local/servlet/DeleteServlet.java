package com.local.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String parameter = req.getParameter("id");
		int p = Integer.parseInt(parameter);

		Insertclass.deleteBookRow(p);
		resp.sendRedirect("viewservlet");

	}

	private void deleteBookRow(int p) {
		// TODO Auto-generated method stub

	}

}
