package com.jvanhouteghem.angularservletbeers.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvanhouteghem.angularservletbeers.Beer;

/**
 * Servlet implementation class BeerListWithParameters
 */
@WebServlet("/BeerDetail")
public class BeerDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeerDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BeerDetail");
       if(null==request.getParameter("id")|| ""==request.getParameter("id")){
           response.setStatus(418);
           response.getWriter().append(" Missing id parameter");
           return;
       }
       String id = request.getParameter("id");
       Beer beer = BeerMongoDAO.getBeerMongoDAOInstance().getBeer(id);
       
       ObjectMapper mapper = new ObjectMapper();
       String dataBeer = mapper.writeValueAsString(beer);
       response.getWriter().println(dataBeer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
